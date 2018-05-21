package com.acme.mainframe.model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import java.math.BigDecimal;
import java.util.Date;

@Record
public class Expense {

    @Field(length = 12)
    private Long id;
    @Field(length = 50)
    private String description;
    @Field(length = 8)
    private Date date;
    @Field(length = 10)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
