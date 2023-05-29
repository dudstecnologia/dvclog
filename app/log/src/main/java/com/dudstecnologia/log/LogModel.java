package com.dudstecnologia.log;

public class LogModel {
    private int id;
    private String event;
    private String batery;
    private String charge;
    private String network;
    private String isWifi;
    private String date;

    public LogModel() {}

    public LogModel(int id, String event, String batery, String charge, String network, String isWifi, String date) {
        this.setId(id);
        this.setEvent(event);
        this.setBatery(batery);
        this.setCharge(charge);
        this.setNetwork(network);
        this.setIsWifi(isWifi);
        this.setDate(date);
    }

    public LogModel(String event, String batery, String charge, String network, String isWifi, String date) {
        this.setId(id);
        this.setEvent(event);
        this.setBatery(batery);
        this.setCharge(charge);
        this.setNetwork(network);
        this.setIsWifi(isWifi);
        this.setDate(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getBatery() {
        return batery;
    }

    public void setBatery(String batery) {
        this.batery = batery;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getIsWifi() {
        return isWifi;
    }

    public void setIsWifi(String isWifi) {
        this.isWifi = isWifi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
