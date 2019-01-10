package com.github.simonlim94.model;

public class Signature {

    public class GenerateSignatureResult
    {
        public String data;
        public String sequenceData;
        public String signature;
        public Error error;
    }

    public class VerifySignatureResult
    {
        public Boolean isValid;
        public Error error;
    }

    public class  GenerateSignatureRequestData
    {
        public Object data;
        public String method;
        public String nonceStr;
        public String privateKey;
        public String requestUrl;
        public String signType;
        public String timestamp;
    }

    public class VerifySignatureRequestData
    {
        public Object data;
        public String method;
        public String nonceStr;
        public String publicKey;
        public String requestUrl;
        public String signType;
        public String timestamp;
        public String signature;
    }
}
