package com.github.revenuemonster.model;

import java.util.ArrayList;

public class Voucher {
    public class IssueVoucher{
        public String code;
        public String qrUrl;
    }

    public class IssueVoucherResult{
        public IssueVoucher item;
        public String code;
        public Error error;
    }

    public class VoidVoucherResult{
        public Voucher item;
        public String code;
        public Error error;
    }

    public class GetVoucherByCodeResult{
        public Voucher item;
        public String code;
        public Error error;
    }

    public class GetVoucherBatchesResult{
        public ArrayList<Voucher> items;
        public String code;
        public Error error;
    }

    public class GetVoucherBatchByKeyResult{
        public Voucher item;
        public String code;
        public Error error;
    }

    public class Expiry{
        public String type;
        public Integer day;
        public String expiredAt;
    }

    public class VoucherResult
    {
        public String id;
        public String key;
        public String label;
        public String redemptionRuleKey;
        public String voucherBatchKey;
        public String type;
        public Integer quantity;
        public Integer balanceQuantity;
        public Integer usedQuantity;
        public Integer amount;
        public Integer discountRate;
        public Integer minimumSpendAmount;
        public String origin;
        public String imageUrl;
        public String memberProfile;
        public String assignedAt;
        public String payload;
        public String qrUrl;
        public String code;
        public Boolean isShipping;
        public Address address;
        public Expiry expiry;
        public String usedAt;
        public String redeemedAt;
        public Boolean isDeviceRedeem;
        public String status;
        public String createdAt;
        public String updatedAt;
    }
}
