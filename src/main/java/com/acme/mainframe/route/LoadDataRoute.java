package com.acme.mainframe.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.String.format;

@Component
public class LoadDataRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        for (String cache : Arrays.asList("Employee", "Department", "Expense")) {

            DataFormat cacheDataFormat = new BeanIODataFormat("mappings.xml", format("%sFile", cache.toLowerCase()));

            from("file:{{mainframe.data.dir}}/" + cache)
                    .unmarshal(cacheDataFormat)
                    .split(body()).streaming()
                    .to("direct:" + cache);

            from("direct:" + cache)
                    .marshal().json(JsonLibrary.Jackson,
                        Class.forName(format("com.acme.mainframe.model.%s", cache)))
                    .setHeader(InfinispanConstants.VALUE, body())
                    .setHeader(InfinispanConstants.CACHE_NAME, constant(cache.toLowerCase()))
                    .setHeader(InfinispanConstants.OPERATION, constant(InfinispanConstants.PUT))
                    .setHeader(InfinispanConstants.KEY).jsonpath("key")
                    .log("${body}")
                    .to("infinispan:default?cacheContainer=#remoteCacheContainer");
        }

    }

}
