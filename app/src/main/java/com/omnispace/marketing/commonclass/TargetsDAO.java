package com.omnispace.marketing.commonclass;

public class TargetsDAO {
    public TargetsDAO(){

    }

    public TargetsDAO(String targetId, String targetName, String phoneNum, String targetLocation, String type) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.phoneNum = phoneNum;
        this.targetLocation = targetLocation;
        this.type = type;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String targetId,targetName,phoneNum,targetLocation,type;
}
