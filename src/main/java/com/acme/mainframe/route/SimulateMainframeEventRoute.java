package com.acme.mainframe.route;

import com.acme.mainframe.processor.GenerateSampleEmployeeDataProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class SimulateMainframeEventRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        DataFormat employeeDataFormat = new BeanIODataFormat("mappings.xml", format("%sFile", "employee"));

        from("timer://employeeEvent")
                .process(new GenerateSampleEmployeeDataProcessor())
                .marshal(employeeDataFormat)
                .log("${body}")
                .to("direct:generateEmployeeEvent");

        from("direct:generateEmployeeEvent")
                .to("netty4:tcp://localhost:10101?textline=true");

    }
}
