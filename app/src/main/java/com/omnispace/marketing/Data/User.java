package com.omnispace.marketing.Data;

public class User {
    private String mobilenumber,password;

    public User(String mobilenumber, String password) {

        this.mobilenumber= mobilenumber;
        this.password = password;

    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
