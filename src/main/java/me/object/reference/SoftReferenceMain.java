package me.object.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceMain {
    public static void main(String[] args) throws InterruptedException {
        String s = new String("Hello, World!");
        SoftReference<String> soft = new SoftReference<>(s);

        System.out.println("Before release variable: " + soft.get());
        s = null;
        System.out.println("After release variable: " + soft.get());

        // Call GC
        System.gc();
        Thread.sleep(1000);

        System.out.println("After GC: " + soft.get());
    }
}
