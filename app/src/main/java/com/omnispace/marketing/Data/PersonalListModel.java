package com.omnispace.marketing.Data;

public class PersonalListModel {
    String type;
    String name;
    String phonenumber;
    String location;

    String date;


    //constructor
    public PersonalListModel(String type,String name, String phonenumber,String location,String date ) {
        this.type = type;
        this.name = name;
        this.phonenumber = phonenumber;
        this.location = location;
        this.date = date;


    }
    public String getType(){
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDate() {
        return this.date;
    }

}
