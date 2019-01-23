package com.github.revenuemonster;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.revenuemonster.model.ClientCredentials;
import com.github.revenuemonster.model.TransactionQR;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Environment environment = new Environment();
        environment.setEnvironment("7564301451650900045", "MbesOZSFVmyqwDqxlMdLKJSakMTEWnKY", "sandbox");
        Authentication authentication = new Authentication();
        ClientCredentials clientCredentials2 = authentication.GetClientCredentials();
        ClientCredentials clientCredentials = authentication.GetAuthorizationCode("kuNSYjCdZtCLjEQaDyEqZfqjVDwuFxNgKFHinXaGHRkzmgxulOBAqnqLqRmuekKcbeRzLyaYePqHJGHitvQKbliHAGhGHiMBRzohQDLrGuntXluDyTYojkRwOyRQUvdIKgwxaDtTMbMGEtqVaGeGMUHzyQGwHGkZRicAtjYiiRbiWixUUVsakUjFGWNLKUcOxPaEwhlGlViIsJrABVtPGSeoxQtAEtRpHeSmMRRoZUPTlEWqHvbmSdOpVuGczvY","https://wwww.google.com");
        environment.setRefreshToken(clientCredentials.refreshToken);
        environment.setAccessToken(clientCredentials.accessToken);


        TreeMap<String,Object> data = new TreeMap<String,Object>();
        data.put("amount",100);
        data.put("currencyType","MYR");
        String[] method = new String[1];
        method[0] = "WECHATPAY";
        data.put("method",method);
        TreeMap<String,Object> expiry = new TreeMap<String,Object>();
        expiry.put("type","PERMANENT");
        data.put("expiry",expiry);
        TreeMap<String,Object> order = new TreeMap<String,Object>();
        order.put("details","1 x Coffee");
        order.put("title","Sales");
        data.put("order",order);
        data.put("redirectUrl","https://www.google.com");
        data.put("type","DYNAMIC");
        data.put("storeId","6170506694335521334");
        data.put("isPreFillAmount",true);

        try {
            String currentPath = System.getProperty("user.dir") + "/src/privateKey.pem";
            String privateKey = getKey(currentPath);
            environment.setPrivateKey(privateKey);
            Payment payment = new Payment();
            TransactionQR result = payment.GetPaymentTransactionQRCodeURLByCode("524cc343e28b4081fd8820ad4e7d70f4");
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(result);
            System.out.println(json);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static String getKey(String filename) throws IOException {
        //Read key from file
        String strKeyPEM = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line = br.readLine()) != null){
            strKeyPEM += line + "\n";
        }
        br.close();
        return strKeyPEM;
    }
}

