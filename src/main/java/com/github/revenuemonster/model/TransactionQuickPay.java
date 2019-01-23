package com.github.revenuemonster.model;

import java.io.Serializable;

public class TransactionQuickPay implements Serializable {
    TransactionQuickPayItem ItemObject;
    Error ErrorObject;
    private String code;


    // Getter Methods

    public TransactionQuickPayItem getItem() {
        return ItemObject;
    }

    public Error getError() {
        return ErrorObject;
    }

    public String getCode() {
        return code;
    }

    // Setter Methods

    public void setItem(TransactionQuickPayItem itemObject) {
        this.ItemObject = itemObject;
    }

    public void setError(Error errorObject) {
        this.ErrorObject = errorObject;
    }

    public void setCode(String code) {
        this.code = code;
    }


}



