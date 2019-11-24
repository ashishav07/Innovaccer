package com.example.innovacer;

public class Visitor {
    private String vName,vMobile,vEmail,hName,hEmail,hMobile,hAddress,id,checkIn,checkOut,checkInDate;

    public Visitor(String vName, String vMobile, String vEmail, String hName, String hEmail, String hMobile, String hAddress, String id, String checkIn, String checkOut,String checkInDate) {
        this.vName = vName;
        this.vMobile = vMobile;
        this.vEmail = vEmail;
        this.hName = hName;
        this.hEmail = hEmail;
        this.hMobile = hMobile;
        this.hAddress = hAddress;
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkInDate = checkInDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvMobile() {
        return vMobile;
    }

    public void setvMobile(String vMobile) {
        this.vMobile = vMobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getvEmail() {
        return vEmail;
    }

    public void setvEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethEmail() {
        return hEmail;
    }

    public void sethEmail(String hEmail) {
        this.hEmail = hEmail;
    }

    public String gethMobile() {
        return hMobile;
    }

    public void sethMobile(String hMobile) {
        this.hMobile = hMobile;
    }

    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }
    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

}
