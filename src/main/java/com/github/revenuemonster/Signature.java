package com.github.revenuemonster;

import com.github.revenuemonster.util.Encode;
import org.apache.commons.codec.binary.Base64;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
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

            byte[] encodedPrivateKey = Base64.decodeBase64(privateKeyPem);
            ByteBuffer input = ByteBuffer.wrap(encodedPrivateKey);
            if (der(input, 0x30) != input.remaining()) throw new IllegalArgumentException("Excess data");
            if (!BigInteger.ZERO.equals(derint(input))) throw new IllegalArgumentException("Unsupported version");
            BigInteger modulus = derint(input);
            BigInteger publicExponent = derint(input);
            BigInteger privateExponent = derint(input);
            BigInteger prime1 = derint(input);
            BigInteger prime2 = derint(input);
            BigInteger exponent1 = derint(input);
            BigInteger exponent2 = derint(input);
            BigInteger coefficient = derint(input);

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, prime1, prime2, exponent1, exponent2, coefficient);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            result = factory.generatePrivate(keySpec);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    private static BigInteger derint(ByteBuffer input) {
        int len = der(input, 0x02);
        byte[] value = new byte[len];
        input.get(value);
        return new BigInteger(+1, value);
    }

    private static int der(ByteBuffer input, int exp) {
        int tag = input.get() & 0xFF;
        if (tag != exp) throw new IllegalArgumentException("Unexpected tag");
        int n = input.get() & 0xFF;
        if (n < 128) return n;
        n &= 0x7F;
        if ((n < 1) || (n > 2)) throw new IllegalArgumentException("Invalid length");
        int len = 0;
        while (n-- > 0) {
            len <<= 8;
            len |= input.get() & 0xFF;
        }
        return len;
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
