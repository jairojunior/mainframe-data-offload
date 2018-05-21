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
public class MainframeListenerRoute extends RouteBuilder {

    private static final int BASE_PORT = 10100;

    @Override
    public void configure() throws Exception {

        int relative_port = 0;

        for (String cache : Arrays.asList("Employee", "Department", "Expense")) {

            relative_port++;

            DataFormat cacheDataFormat = new BeanIODataFormat("mappings.xml", format("%sFile", cache.toLowerCase()));

            from(String.format("netty4:tcp://localhost:%s?textline=true", BASE_PORT + relative_port))
                    .unmarshal(cacheDataFormat)
                    .split(body()).streaming()
                    .to("direct:listener" + cache);

            from("direct:listener" + cache)
                    .marshal().json(JsonLibrary.Jackson,
                        Class.forName(format("com.acme.mainframe.model.%s", cache)))
                    .setHeader(InfinispanConstants.VALUE, body())
                    .setHeader(InfinispanConstants.CACHE_NAME, constant(cache.toLowerCase()))
                    .setHeader(InfinispanConstants.OPERATION, constant(InfinispanConstants.PUT))
                    .setHeader(InfinispanConstants.KEY).jsonpath("key")
                    .log("Received a change request for: ${body}")
                    .to("infinispan:default?cacheContainer=#remoteCacheContainer");
        }
    }
}
