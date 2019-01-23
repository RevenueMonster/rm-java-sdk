package com.github.revenuemonster.model;

public class Order {
    private String id;
    private String title;
    private String detail;
    private String additionalData;
    private float amount;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public float getAmount() {
        return amount;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
