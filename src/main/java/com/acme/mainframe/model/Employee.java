package com.acme.mainframe.model;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;
import org.beanio.builder.Align;

import java.util.Date;
import java.util.List;

@Record
public class Employee {

    @Field(length = 8)
    private String id;
    @Field(length = 1)
    private String gender;
    @Field(length = 20)
    private String email;
    @Field(length = 20)
    private String firstName;
    @Field(length = 30)
    private String lastName;
    @Field(length = 8, format = "MMddyyyy")
    private Date birthDate;
    @Field(length = 4, align = Align.RIGHT)
    private int centerOfCost;
    @Field(length = 40)
    private String address;

    @Segment(minOccurs = 2, maxOccurs = 2)
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getCenterOfCost() {
        return centerOfCost;
    }

    public void setCenterOfCost(int centerOfCost) {
        this.centerOfCost = centerOfCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return id;
    }

    public String getKey() {
        return id;
    }
}
