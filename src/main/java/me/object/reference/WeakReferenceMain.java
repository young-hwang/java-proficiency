package me.object.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceMain {
    public static void main(String[] args) throws InterruptedException {
        String s = new String("Hello, World!");
        WeakReference<String> weak = new WeakReference<>(s);

        System.out.println("Before release variable: " + weak.get());
        s = null;
        System.out.println("After release variable: " + weak.get());

        // Call GC
        System.gc();
        Thread.sleep(1000);

        System.out.println("After GC: " + weak.get());
    }
}
