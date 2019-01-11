package com.github.simonlim94.model;

public class Error {
    private String code;
    private String message;
    Description DescriptionObject;


    // Getter Methods

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Description getDescription() {
        return DescriptionObject;
    }

    // Setter Methods

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(Description descriptionObject) {
        this.DescriptionObject = descriptionObject;
    }
}

class Description {
    Refund RefundObject;
    private String type;


    // Getter Methods

    public Refund getRefund() {
        return RefundObject;
    }
    public String getType() {
        return type;
    }

    // Setter Methods

    public void setRefund(Refund refundObject) {
        this.RefundObject = refundObject;
    }
    public void setType(String type) {
        this.type = type;
    }
}

class Refund {
    private String amount;
    private String currencyType;
    private String type;


    // Getter Methods

    public String getAmount() {
        return amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setType(String type) {
        this.type = type;
    }
}
