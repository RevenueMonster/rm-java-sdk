package com.github.simonlim94.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TransactionQRs implements Serializable {
    ArrayList< TransactionQRItem > items = new ArrayList < TransactionQRItem > ();
    Error ErrorObject;
    Meta MetaObject;
    private String code;


    // Getter Methods

    public Error getError() {
        return ErrorObject;
    }

    public String getCode() {
        return code;
    }

    public ArrayList< TransactionQRItem > getItems() {
        return items;
    }

    public Meta getMeta() {
        return MetaObject;
    }

    // Setter Methods

    public void setError(Error errorObject) {
        this.ErrorObject = errorObject;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setItems(ArrayList<TransactionQRItem> items) {
        this.items = items;
    }

    public void setMeta(Meta metaObject) {
        this.MetaObject = metaObject;
    }

}
