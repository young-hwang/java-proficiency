package me.functional.stream;

import java.util.stream.IntStream;

public class CollectorInterface {
    public static void main(String[] args) {

    }

    public boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }
}
