package com.github.simonlim94.model;

import java.util.ArrayList;

public class TransactionQRItem {
    Store StoreObject;
    private String type;
    private boolean isPreFillAmount;
    private String currencyType;
    private float amount;
    private String platform;
    ArrayList< Object > method = new ArrayList < Object > ();
    Expiry ExpiryObject;
    private String code;
    private String status;
    private String qrCodeUrl;
    private String redirectUrl;
    Order OrderObject;
    private String createdAt;
    private String updatedAt;


    // Getter Methods

    public Store getStore() {
        return StoreObject;
    }

    public String getType() {
        return type;
    }

    public boolean getIsPreFillAmount() {
        return isPreFillAmount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public float getAmount() {
        return amount;
    }

    public String getPlatform() {
        return platform;
    }

    public Expiry getExpiry() {
        return ExpiryObject;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public Order getOrder() {
        return OrderObject;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    // Setter Methods

    public void setStore(Store storeObject) {
        this.StoreObject = storeObject;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsPreFillAmount(boolean isPreFillAmount) {
        this.isPreFillAmount = isPreFillAmount;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setExpiry(Expiry expiryObject) {
        this.ExpiryObject = expiryObject;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setOrder(Order orderObject) {
        this.OrderObject = orderObject;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
