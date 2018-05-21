package com.acme.mainframe.model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class Role {

    public static final Role PRINCIPAL = new Role("PRINCIPAL");
    public static final Role MANAGER = new Role("MANAGER");
    public static final Role EMPLOYEE = new Role("EMPLOYEE");

    public static final Role NETWORK_RESTRICTED = new Role("NETWORK_RESTRICTED");
    public static final Role NETWORK_SIMPLE = new Role("NETWORK_SIMPLE");
    public static final Role NETWORK_FULL = new Role("NETWORK_FULL");

    @Field(length = 20)
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
