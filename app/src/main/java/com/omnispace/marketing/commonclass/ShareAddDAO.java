package com.omnispace.marketing.commonclass;

public class ShareAddDAO {
    public ShareAddDAO(){

    }

    public ShareAddDAO(String sharingtype, String roomcount, String osprice, String roomtype) {
        this.sharingtype = sharingtype;
        this.roomcount = roomcount;
        this.osprice = osprice;
        this.roomtype = roomtype;
    }

    public String getSharingtype() {
        return sharingtype;
    }

    public void setSharingtype(String sharingtype) {
        this.sharingtype = sharingtype;
    }

    public String getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(String roomcount) {
        this.roomcount = roomcount;
    }


    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    private String sharingtype;
    private String roomcount;

    public String getOsprice() {
        return osprice;
    }

    public void setOsprice(String osprice) {
        this.osprice = osprice;
    }

    public String getPriceperday() {
        return priceperday;
    }

    public void setPriceperday(String priceperday) {
        this.priceperday = priceperday;
    }

    public String getPricepermonth() {
        return pricepermonth;
    }

    public void setPricepermonth(String pricepermonth) {
        this.pricepermonth = pricepermonth;
    }

    private String osprice;
    private String roomtype;
    private String priceperday;
    private String pricepermonth;
}
