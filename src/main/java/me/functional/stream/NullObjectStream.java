package me.functional.stream;

import java.util.stream.Stream;

public class NullObjectStream {
    public static void main(String[] args) {
        // null 객체로 스트림 만들기
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        homeValueStream.forEach(System.out::println);

        System.out.println("=====================================");
        // ofNullable 메서드 사용
        Stream<String> homeValueStream1 = Stream.ofNullable(System.getProperty("home"));
        homeValueStream1.forEach(System.out::println);

        System.out.println("=====================================");
        // flatMap 과 사용
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);
    }
}
