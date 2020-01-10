package com.sdk4.javaexample.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void info(String format, Object... args) {
        print("INFO", format, args);
    }

    public static void warn(String format, Object... args) {
        print("WARN", format, args);
    }

    public static void error(String format, Object... args) {
        print("ERROR", format, args);
    }

    private static void print(String level, String format, Object... args) {
        System.out.printf("[%s]%s %s\n", sdf.format(new Date()), level, String.format(format, args));
    }

}
