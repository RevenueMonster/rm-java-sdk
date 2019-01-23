package com.github.revenuemonster.model;

public class Expiry {
    private String type;
    private float day;
    private String expiredAt;


    // Getter Methods

    public String getType() {
        return type;
    }

    public float getDay() {
        return day;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }
}