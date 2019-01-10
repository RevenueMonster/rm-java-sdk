package com.github.simonlim94.model;

import java.util.ArrayList;

public class Transactions {
    ArrayList< TransactionQuickPayItem > items = new ArrayList < TransactionQuickPayItem > ();
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

    public void setMeta(Meta metaObject) {
        this.MetaObject = metaObject;
    }
}
