package com.acme.mainframe.processor;

import com.acme.mainframe.model.Employee;
import com.acme.mainframe.model.Role;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Arrays;
import java.util.List;

import static com.acme.mainframe.model.Role.*;

public class GenerateSampleEmployeeDataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Employee employee = new Employee();

        DataFactory df = new DataFactory();

        employee.setAddress(df.getAddress());
        employee.setFirstName(df.getFirstName());
        employee.setLastName(df.getLastName());
        employee.setBirthDate(df.getBirthDate());
        employee.setEmail(df.getEmailAddress());
        employee.setId(df.getNumberText(8));
        employee.setCenterOfCost(df.getNumberBetween(1, 999));
        employee.setGender(df.getItem(Arrays.asList("M", "F")));

        List<Role> roles = Arrays.asList(
                df.getItem(Arrays.asList(PRINCIPAL, MANAGER), 10, EMPLOYEE),
                df.getItem(Arrays.asList(NETWORK_SIMPLE, NETWORK_FULL), 10, NETWORK_RESTRICTED));

        System.out.println(roles.size());

        employee.setRoles(roles);

        exchange.getOut().setBody(employee);
    }

}
