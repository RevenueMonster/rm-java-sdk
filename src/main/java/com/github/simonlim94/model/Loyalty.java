package com.github.simonlim94.model;

import java.util.ArrayList;

public class Loyalty {

    public class LoyaltyPoint
    {
        public String code;
        public Error error;
    }

    public class LoyaltyMemberTier
    {
        public String label;
        public Integer minimumPoint;
        public Integer totalMember;
        public String[] description;
    }

    public class LoyaltyMember
    {
        public String id;
        public String name;
        public String email;
        public String nric;
        public Address address;
        public String gender;
        public String state;
        public String birthDate;
        public Integer loyaltyPoint;
        public Integer credit;
        public String countryCode;
        public String phoneNumber;
        public String profileImageUrl;
        public Boolean hasPinCode;
        public LoyaltyMemberTier memberTier;
        public String status;
        public String createdAt;
        public Error error;
    }

    public class GetLoyaltyMembersResult
    {
        public ArrayList<LoyaltyMember> item;
        public Error error;
    }

    public class LoyaltyMemberPointHistory
    {
        public String key;
        public String type;
        public String description;
        public Integer point;
        public Integer credit;
        public Integer creditBalance;
        public String createdAt;
        public String updatedAt;
    }

    public class LoyaltyMemberPointHistories
    {
        public ArrayList<LoyaltyMemberPointHistory> items;
        public Error error;
    }
}
