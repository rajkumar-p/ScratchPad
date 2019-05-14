package com.diskodev;

public class Utils {
    static final String errorPrefix = "Error ->";

    public static void printExceptionMessage(Exception e) {
        System.out.println(String.format("%s %s", errorPrefix, e.toString()));
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
