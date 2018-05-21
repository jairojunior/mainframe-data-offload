package com.acme.mainframe.model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class Department {

    @Field(length = 12)
    private Long id;
    @Field(length = 40)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
