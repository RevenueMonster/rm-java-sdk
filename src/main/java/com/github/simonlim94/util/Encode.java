package com.github.simonlim94.util;

import org.apache.commons.codec.binary.Base64;

public class Encode {
    public static String Base64Encode(String plainText) {
        byte[] encoded = Base64.encodeBase64(plainText.getBytes());
        return new String(encoded);
    }
}
