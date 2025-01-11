package me.object.reference;

public class StrongReferenceMain {
    public static void main(String[] args) throws InterruptedException {
        String s = new String("Hello, World!");

        System.out.println("Before release variable: " + s);
        s = null;
        System.out.println("After release variable: " + s);

        // Call GC
        System.gc();
        Thread.sleep(1000);

        System.out.println("After GC: " + s);
    }
}
