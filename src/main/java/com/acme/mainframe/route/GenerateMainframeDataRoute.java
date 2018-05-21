package com.acme.mainframe.route;

import com.acme.mainframe.processor.GenerateSampleEmployeeDataProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class GenerateMainframeDataRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        DataFormat employeeDataFormat = new BeanIODataFormat("mappings.xml", format("%sFile", "employee"));

        from("timer://employeeData").to("direct:generateEmployeeData");

        from("direct:generateEmployeeData")
                .process(new GenerateSampleEmployeeDataProcessor())
                .log("${body}")
                .marshal(employeeDataFormat)
                .to("file:{{mainframe.data.dir}}/Employee");

    }
}
