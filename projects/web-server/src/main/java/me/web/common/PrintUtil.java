package me.web.common;

public abstract class PrintUtil {
    public static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }

}
