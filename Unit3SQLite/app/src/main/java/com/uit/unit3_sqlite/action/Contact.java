package com.uit.unit3_sqlite.action;

public class Contact {
    private int _id;
    private String name;
    private String phoneNumber;

    public Contact(int id, String name, String phoneNumber) {
        this._id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
