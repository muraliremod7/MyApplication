package com.omnispace.marketing.Data;

public class HostelListModel {
    String name;
    String phonenumber;
    String location;
    Integer icon;
    public HostelListModel() {

    }

    public HostelListModel(String name, String phonenumber, String location, int icon) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.location = location;
        this.icon = icon;
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
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getIcon() {
        return this.icon;
    }
    public void setIcon(Integer icon) {
        this.icon = icon;
    }



}
