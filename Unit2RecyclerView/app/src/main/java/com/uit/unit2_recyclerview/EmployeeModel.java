package com.uit.unit2_recyclerview;

class EmployeePartTime extends Employee {

    @Override
    public double tinhLuong(){
        return 150;
    }

    @Override
    String type() {
        return "PartTime";
    }

    @Override
    public String toString() {
        return "EmployeePartTime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmployeeFullTime extends Employee{
    @Override
    public double tinhLuong(){
        return 500;
    }

    @Override
    String type() {
        return "FullTime";
    }

    @Override
    public String toString() {
        return "EmployeeFullTime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class Employee{
    String id;
    String name;

    String type(){
        return "";
    }

    double tinhLuong() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
