package com.github.simonlim94.util;

import org.apache.commons.codec.binary.Base64;

public class Decode {
    public static String Base64Decode(String base64EncodedData) {
        byte[] decoded = Base64.decodeBase64(base64EncodedData.getBytes());
        return new String(decoded);
    }
}
