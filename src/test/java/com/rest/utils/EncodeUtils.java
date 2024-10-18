package com.rest.utils;

import java.util.Base64;

public class EncodeUtils {

    public static String ToBase64(String stringToEncode){
        return Base64.getEncoder()
                .encodeToString(stringToEncode.getBytes());
    }
}
