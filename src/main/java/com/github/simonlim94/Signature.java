package com.github.simonlim94;

import com.github.simonlim94.util.Encode;
import org.apache.commons.codec.binary.Base64;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import java.security.GeneralSecurityException;
import java.math.BigInteger;
import java.security.spec.RSAPrivateCrtKeySpec;


public class Signature {
    public String GenerateSignature(String compactData,String privateKey, String requestUrl, String nonceStr, String signType, String method, String timestamp){
        String result = "";
        try {
            String encodedData = Encode.Base64Encode(compactData);
            String plainText;
            if(compactData != ""){
                 plainText = "data="+encodedData+"&method="+method+"&nonceStr="
                        +nonceStr+"&requestUrl="+requestUrl+"&signType="+signType
                        +"&timestamp="+timestamp;
            }else {
                plainText = "method=" + method + "&nonceStr="
                        + nonceStr + "&requestUrl=" + requestUrl + "&signType=" + signType
                        + "&timestamp=" + timestamp;
            }
            byte[] plainTextByte = plainText.getBytes("UTF8");

            // PKCS#8 format
            final String PEM_PRIVATE_START = "-----BEGIN PRIVATE KEY-----";
            // PKCS#1 format
            final String PEM_RSA_PRIVATE_START = "-----BEGIN RSA PRIVATE KEY-----";

            PrivateKey privKey = null;
            if (privateKey.indexOf(PEM_PRIVATE_START) != -1) { // PKCS#8 format
                privKey = readPKCS8Key(privateKey);
            }else if (privateKey.indexOf(PEM_RSA_PRIVATE_START) != -1) {  // PKCS#1 format
                privKey = readPCKS1Key(privateKey);
            }

            java.security.Signature sig = java.security.Signature.getInstance("SHA256WithRSA");
            sig.initSign(privKey);
            sig.update(plainTextByte);
            byte[] signatureBytes = sig.sign();
            result = new String(Base64.encodeBase64(signatureBytes));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return result;
    }



    private static PrivateKey readPCKS1Key(String privateKeyPem){
        PrivateKey result = null;
        try {
            privateKeyPem = privateKeyPem.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            DerInputStream derReader = new DerInputStream(Base64.decodeBase64(privateKeyPem));

            DerValue[] seq = derReader.getSequence(0);

            if (seq.length < 9) {
                throw new GeneralSecurityException("Could not parse a PKCS1 private key.");
            }

            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger prime1 = seq[4].getBigInteger();
            BigInteger prime2 = seq[5].getBigInteger();
            BigInteger exp1 = seq[6].getBigInteger();
            BigInteger exp2 = seq[7].getBigInteger();
            BigInteger crtCoef = seq[8].getBigInteger();

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);

            KeyFactory factory = KeyFactory.getInstance("RSA");

            result = factory.generatePrivate(keySpec);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    private static PrivateKey readPKCS8Key(String privateKeyPem){
        PrivateKey result = null;
        try{
            privateKeyPem = privateKeyPem.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            byte[] pkcs8EncodedKey = Base64.decodeBase64(privateKeyPem);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            result =  factory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
