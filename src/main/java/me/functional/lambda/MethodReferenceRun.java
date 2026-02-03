package me.functional.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferenceRun {
    public static void main(String[] args) {
        // static method reference
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("young"));

        // instance method reference
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("young"));

        // constructor method reference
        Supplier<Greeting> constructor = Greeting::new;
        Greeting newGreeting = constructor.get();
        System.out.println(newGreeting.getName());

        // constructor method reference
        // Functional Interface 형에 맞게 method reference 가 이루어짐
        Function<String, Greeting> constructorWithParam = Greeting::new;
        Greeting newGreeting1 = constructorWithParam.apply("young");
        System.out.println(newGreeting1.getName());

        // 임의 객체의 instance method reference
        String[] names = {"young", "hwang", "park"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        Arrays.sort(names, String::compareToIgnoreCase);
    }
}
