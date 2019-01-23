package com.github.revenuemonster.model;

public class Payee {
    private String userId;
    private String subUserId;


    // Getter Methods

    public String getUserId() {
        return userId;
    }

    public String getSubUserId() {
        return subUserId;
    }

    // Setter Methods

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSubUserId(String subUserId) {
        this.subUserId = subUserId;
    }
}
