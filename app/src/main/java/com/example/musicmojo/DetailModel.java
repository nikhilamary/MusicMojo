package com.example.musicmojo;

class DetailModel {
    private String time;
    private String date;
    private String location;

    public DetailModel(String time, String date, String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public DetailModel() {

    }

    public String getTime(){
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
