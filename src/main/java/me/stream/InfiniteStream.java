package me.stream;

import java.util.stream.Stream;

public class InfiniteStream {
    public static void main(String[] args) {
        // iterate 메서드 사용
        java.util.stream.Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("=====================================");

        // fibonnaci
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);

        System.out.println("=====================================");

        // stream short circuit
        Stream.iterate(0, n -> n + 2)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        System.out.println("=====================================");

        // generate 메서드 사용
        java.util.stream.Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
