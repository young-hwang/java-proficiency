package me.unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeRun {
    public static void main(String[] args) {
        InitializationOrdering initializationOrdering = new InitializationOrdering();
        initializationOrdering.getA();
    }
}
