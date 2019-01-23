package com.github.revenuemonster.util;

import java.time.Instant;
import java.util.Random;

public class RandomString {
    private static Random random = new Random(Instant.now().getEpochSecond());
    public static String GenerateRandomString(int size){
        StringBuilder builder = new StringBuilder();
        char ch;
        for (int i = 0; i < size; i++)
        {
            ch = (char)((int)Math.floor(26 * random.nextDouble() + 65));
            builder.append(ch);
        }
        return builder.toString();
    }
}
