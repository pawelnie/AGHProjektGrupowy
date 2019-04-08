package com.example.wydarzenia.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Comparable<Event>{
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("location")
    private String location;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("date")
    private String date;
    @SerializedName("photoid")
    private String photoid;
    @SerializedName("category1")
    private String category1;
    @SerializedName("category2")
    private String category2;
    @SerializedName("category3")
    private String category3;
    @SerializedName("signupid")
    private String signupid;
    @SerializedName("userid")
    private String userid;
    @SerializedName("eventid")
    private String eventid;



    public Event(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Event(String title, String description, String location, String latitude, String longitude,
                 String date, String photoid, String category1, String category2, String category3, String signupid, String userid, String eventid) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.photoid = photoid;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.signupid = signupid;
        this.userid = userid;
        this.eventid = eventid;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getSignupid() {
        return signupid;
    }

    public void setSignupid(String signupid) {
        this.signupid = signupid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    @Override
    public int compareTo(Event o) {
        try{
            Date comparedEventDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o.getDate());
            Date thisEvent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.date);
            int compared = (int) ((thisEvent.getTime()/1000) - (comparedEventDate.getTime()/1000));
            return compared;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return 0;

    }
}
