package me.stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
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
                .takeWhile(n -> n < 100)    // java 9
                .forEach(System.out::println);

        System.out.println("=====================================");

        // generate 메서드 사용
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("=====================================");

        // generate fibonacci

        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);
    }
}
