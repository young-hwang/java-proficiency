package me.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        // 피타고라스 수
        // rangeClosed : 시작값과 끝값을 포함하는 IntStream을 반환
        Stream<double[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                        .filter(i -> i[2] % 1 == 0);

        stream.forEach(i -> System.out.println(i[0] + " " + i[1] + " " + i[2]));

        System.out.println("=====================================");

        // make stream
        Stream<String> stream1 = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream1.map(String::toUpperCase).forEach(System.out::println);
    }
}
