package com.github.revenuemonster;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.revenuemonster.constant.Url;
import com.github.revenuemonster.env.env;
import com.github.revenuemonster.model.TransactionQR;
import com.github.revenuemonster.model.TransactionQuickPay;
import com.github.revenuemonster.model.TransactionQRs;
import com.github.revenuemonster.model.Transactions;
import com.github.revenuemonster.util.RandomString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.TreeMap;

public class Payment {
    public TransactionQuickPay QuickPay(TreeMap<String,Object> data){
        TransactionQuickPay result = new TransactionQuickPay();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/quickpay";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/quickpay";
        }


        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer();
            String json = ow.writeValueAsString(data);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature(json,env.privateKey,targetUrl,randomString,"sha256","post",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] jsonByte = json.getBytes("UTF-8");
            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(jsonByte);
            os.close();

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQuickPay.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQuickPay Refund(TreeMap<String,Object> data){
        TransactionQuickPay result = new TransactionQuickPay();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/refund";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/refund";
        }


        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer();
            String json = ow.writeValueAsString(data);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature(json,env.privateKey,targetUrl,randomString,"sha256","post",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] jsonByte = json.getBytes("UTF-8");
            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(jsonByte);
            os.close();

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQuickPay.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQuickPay Reverse(TreeMap<String,Object> data, String privateKey){
        TransactionQuickPay result = new TransactionQuickPay();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/reverse";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/reverse";
        }


        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer();
            String json = ow.writeValueAsString(data);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature(json,privateKey,targetUrl,randomString,"sha256","post",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] jsonByte = json.getBytes("UTF-8");
            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(jsonByte);
            os.close();

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQuickPay.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQuickPay GetPaymentTransactionByID(String transactionId){
        TransactionQuickPay result = new TransactionQuickPay();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/transaction/"+transactionId;
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/transaction/"+transactionId;
        }


        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature("",env.privateKey,targetUrl,randomString,"sha256","get",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQuickPay.class);
            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQuickPay GetPaymentTransactionByOrderID(String orderId){
        TransactionQuickPay result = new TransactionQuickPay();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/transaction/order/"+orderId;
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/transaction/order/"+orderId;
        }


        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature("",env.privateKey,targetUrl,randomString,"sha256","get",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQuickPay.class);
            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQR CreateTransactionQRCodeURL(TreeMap<String,Object> data){
        TransactionQR result = new TransactionQR();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/transaction/qrcode";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/transaction/qrcode";
        }


        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer();
            String json = ow.writeValueAsString(data);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature(json,env.privateKey,targetUrl,randomString,"sha256","post",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] jsonByte = json.getBytes("UTF-8");
            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(jsonByte);
            os.close();

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQR.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQRs GetPaymentTransactionQRCodeURL(String limit, String type, String expiryType){
        TransactionQRs result = new TransactionQRs();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/transaction/qrcodes?order[]=-createdAt&limit="+limit+"&filter={%22type%22:%"+type+"%22,%20%22expiry.type%22:%20%22"+expiryType+"%22}";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/transaction/qrcodes?order[]=-createdAt&limit="+limit+"&filter={%22type%22:%"+type+"%22,%20%22expiry.type%22:%20%22"+expiryType+"%22}";
        }

        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature("",env.privateKey,targetUrl,randomString,"sha256","get",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQRs.class);
            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public TransactionQR GetPaymentTransactionQRCodeURLByCode(String qrcode){
        TransactionQR result = new TransactionQR();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/transaction/qrcode/"+qrcode;
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/transaction/qrcode/"+qrcode;
        }

        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature("",env.privateKey,targetUrl,randomString,"sha256","get",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, TransactionQR.class);
            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public Transactions GetTransactionsByCode(String qrcode){
        Transactions result = new Transactions();
        String targetUrl = "";
        final String randomString = RandomString.GenerateRandomString(32);
        long unixTimestamp = Instant.now().getEpochSecond();
        String authorization = "Bearer "+env.accessToken;

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOpen + "/v3/payment/transaction/qrcode/"+qrcode+"/transactions";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOpen + "/v3/payment/transaction/qrcode/"+qrcode+"/transactions";
        }

        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            Signature sign = new Signature();
            String signature = sign.GenerateSignature("",env.privateKey,targetUrl,randomString,"sha256","get",String.valueOf(unixTimestamp));
            signature = "sha256 "+signature;

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("X-Timestamp", String.valueOf(unixTimestamp));
            connection.setRequestProperty("X-Nonce-Str", randomString);
            connection.setDoInput(true);

            // read the response
            int code = connection.getResponseCode();
            InputStream in = null;
            if(code > 200 ) {
                in = connection.getErrorStream();
            }else {
                in = new BufferedInputStream(connection.getInputStream());
            }
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response, Transactions.class);
            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
