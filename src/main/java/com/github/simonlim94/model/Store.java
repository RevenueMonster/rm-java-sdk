package com.github.simonlim94.model;

public class Store {
        private String id;
        private String name;
        private String imageUrl;
        private String addressLine1;
        private String addressLine2;
        private String postCode;
        private String city;
        private String state;
        private String country;
        private String countryCode;
        private String phoneNumber;
        GeoLocation GeoLocationObject;
        private String status;
        private String createdAt;
        private String updatedAt;


        // Getter Methods

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public String getPostCode() {
            return postCode;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getCountry() {
            return country;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public GeoLocation getGeoLocation() {
            return GeoLocationObject;
        }

        public String getStatus() {
            return status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        // Setter Methods

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setGeoLocation(GeoLocation geoLocationObject) {
            this.GeoLocationObject = geoLocationObject;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

}
