package com.github.simonlim94.model;

public class TransactionQuickPayItem {
    Store StoreObject;
    private String referenceId;
    private String transactionId;
    Order OrderObject;
    private String terminalId;
    Payee PayeeObject;
    private String currencyType;
    private float balanceAmount;
    private String voucher = null;
    private String platform;
    private String method;
    private String transactionAt;
    private String type;
    private String status;
    private String region;
    private String createdAt;
    private String updatedAt;


    // Getter Methods

    public Store getStore() {
        return StoreObject;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Order getOrder() {
        return OrderObject;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public Payee getPayee() {
        return PayeeObject;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public float getBalanceAmount() {
        return balanceAmount;
    }

    public String getVoucher() {
        return voucher;
    }

    public String getPlatform() {
        return platform;
    }

    public String getMethod() {
        return method;
    }

    public String getTransactionAt() {
        return transactionAt;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getRegion() {
        return region;
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

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setOrder(Order orderObject) {
        this.OrderObject = orderObject;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setPayee(Payee payeeObject) {
        this.PayeeObject = payeeObject;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setBalanceAmount(float balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setTransactionAt(String transactionAt) {
        this.transactionAt = transactionAt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
