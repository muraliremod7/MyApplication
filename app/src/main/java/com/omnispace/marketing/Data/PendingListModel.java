package com.omnispace.marketing.Data;

public class PendingListModel {
    String name;
    String phonenumber;
    String location;
    String reason;
    String date;
    int pendingicon;


    //constructor
    public PendingListModel(String name, String phonenumber,String location,String reason, String date,int pendingicon ) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.location = location;
        this.reason = reason;
        this.date = date;
        this.pendingicon = pendingicon;


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
    public String getReason() {
        return this.reason;
    }
    public String getDate() {
        return this.date;
    }
    public int getPendingIcon() {
        return this.pendingicon;
    }
}
