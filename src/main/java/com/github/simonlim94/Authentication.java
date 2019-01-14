package com.github.simonlim94;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.simonlim94.constant.Url;
import com.github.simonlim94.env.env;
import com.github.simonlim94.model.ClientCredentials;
import com.github.simonlim94.util.Encode;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Authentication {
    public ClientCredentials GetClientCredentials(){
        ClientCredentials result = new ClientCredentials();
        String parameter = env.clientId+":"+env.clientSecret;
        String encodeParameter = Encode.Base64Encode(parameter);
        String targetUrl = "";

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOAuth + "/v1/token";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOAuth + "/v1/token";
        }

        String basicAuth  ="Basic "+encodeParameter;
        try {
            Object values = new Object() {
                String grantType = "client_credentials";
            };
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(values);

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response,ClientCredentials.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public ClientCredentials GetAuthorizationCode(final String Code, final String RedirectUri){
        ClientCredentials result = new ClientCredentials();
        String parameter = env.clientId+":"+env.clientSecret;
        String encodeParameter = Encode.Base64Encode(parameter);
        String targetUrl = "";

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOAuth + "/v1/token";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOAuth + "/v1/token";
        }

        String basicAuth  ="Basic "+encodeParameter;
        try {
            Object values = new Object() {
                String grantType = "authorization_code";
                String code = Code;
                String redirectUri = RedirectUri;
            };
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(values);

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
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
            System.out.println(response);
            result = myObjectMapper.readValue(response,ClientCredentials.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }


    public ClientCredentials RefreshToken(){
        ClientCredentials result = new ClientCredentials();
        String parameter = env.clientId+":"+env.clientSecret;
        String encodeParameter = Encode.Base64Encode(parameter);
        final String refreshtoken = env.refreshToken;
        String targetUrl = "";

        if(env.environment == "sandbox"){
            targetUrl = Url.SandBoxOAuth + "/v1/token";
        }else if(env.environment == "production"){
            targetUrl = Url.ProductionOAuth + "/v1/token";
        }

        String basicAuth  ="Basic "+encodeParameter;
        try {
            Object values = new Object() {
                String grantType = "refresh_token";
                String refreshToken = refreshtoken;
            };
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = myObjectMapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(values);

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // write json into body
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            result = myObjectMapper.readValue(response,ClientCredentials.class);

            in.close();
            connection.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
