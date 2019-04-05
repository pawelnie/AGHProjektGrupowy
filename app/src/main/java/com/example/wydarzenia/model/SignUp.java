package com.example.wydarzenia.model;

public class SignUp {

    private Integer signupid;
    private int userid;
    private int eventid;

    public SignUp(int userid, int eventid) {

        this.userid = userid;
        this.eventid = eventid;
    }

    public Integer getSignupid() {
        return signupid;
    }
}
