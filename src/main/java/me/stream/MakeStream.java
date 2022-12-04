package me.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        Stream<double[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                        .filter(i -> i[2] % 1 == 0);

        stream.forEach(i -> System.out.println(i[0] + " " + i[1] + " " + i[2]));
    }
}
