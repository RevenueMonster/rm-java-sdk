package com.github.simonlim94.model;

import java.util.ArrayList;

public class TransactionQR {
    TransactionQRItem ItemObject;
    Error ErrorObject;
    private String code;


    // Getter Methods

    public TransactionQRItem getItem() {
        return ItemObject;
    }

    public Error getError() {
        return ErrorObject;
    }

    public String getCode() {
        return code;
    }

    // Setter Methods

    public void setItem(TransactionQRItem itemObject) {
        this.ItemObject = itemObject;
    }

    public void setError(Error errorObject) {
        this.ErrorObject = errorObject;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

