package com.omnispace.marketing.Data;

public class RejectedListModel  {
    String name;
    String phonenumber;
    String location;
    String reason;
    String date;


    //constructor
    public RejectedListModel(String name, String phonenumber,String location, String reason,String date ) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.location = location;
        this.reason = reason;
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
    public String getReason(){
        return this.reason;
    }
    public String getDate() {
        return this.date;
    }
}
