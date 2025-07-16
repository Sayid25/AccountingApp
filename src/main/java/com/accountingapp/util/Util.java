package com.accountingapp.util;

import java.util.Random;

public class Util {
    public static String generateAccountNumber() {
        StringBuilder number = new StringBuilder(20);
        Random random = new Random();
        number.append(random.nextInt(9) + 1);
        for (int i = 1; i < 20; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
