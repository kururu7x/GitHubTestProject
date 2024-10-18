package com.rest.utils;

import java.util.Random;

public class RandomUtils {
    private static int defaultLength = 10;

    public static String generateString() {
        return createRandomString(defaultLength);
    }

    public static String generateString(int length){
        return createRandomString(length);
    }

    public static String generateString(String prefix){
        return prefix + "_" + createRandomString(10);
    }

    private static String createRandomString(int length){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
