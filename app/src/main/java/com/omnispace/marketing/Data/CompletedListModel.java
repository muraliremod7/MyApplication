package com.omnispace.marketing.Data;

public class CompletedListModel {
    String name;
    String phonenumber;
    String location;
    String date;


    //constructor
    public CompletedListModel(String name, String phonenumber,String location, String date ) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.location = location;
        this.date = date;


    }

    public String getName() {
        return this.name;
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
