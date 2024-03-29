package me.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPIRun {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .forEach(onlineClass -> {
                    System.out.println(onlineClass.getTitle());
                });

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(onlineClass -> !onlineClass.getClosed())
                .forEach(onlineClass -> {
                    System.out.println(onlineClass.getTitle());
                });

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Testspring", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> youngEvents = new ArrayList<>();
        youngEvents.add(springClasses);
        youngEvents.add(javaClasses);

        System.out.println("두 수업  목록에 모두 들어가 있는 모든 수업 아이디 출력");
        youngEvents.stream()
                .flatMap(Collection::stream)
                .forEach(onlineClass -> System.out.println(onlineClass.getTitle()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        IntStream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("Java 수업 중에 Test 가 들어있는 수업이 있는지 확인");
        boolean resultsJava = javaClasses.stream()
                .anyMatch(javaClass -> javaClass.getTitle().contains("Test"));
        System.out.println(resultsJava);

        System.out.println("Spring 수업 중에 제목에 spring 이 들어간 것만 제목 모아서 List 만들기");
        List<String> resultSpring = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        resultSpring.stream()
                .forEach(System.out::println);

        Integer[] values = { 1, 3, 5};
        List<Integer> list = Arrays.stream(values).collect(Collectors.toList());

    }
}
