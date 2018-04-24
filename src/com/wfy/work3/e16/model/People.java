package com.wfy.work3.e16.model;

public class People {
    private int id;
    private String name;
    private String sex;
    private String number;
    private String email;
    private String address;

    public People() {
    }

    public People(int id, String name, String sex, String number, String email, String address) {
        this.email = email;
        this.sex = sex;
        this.name = name;
        this.number = number;
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
