package me.functional.functionalinterface;

import java.util.function.Consumer;

public class ProvideConsumerInterface {
    public static void main(String[] args) {
        // Consumer는 return이 없음
        Consumer<Integer> consumerLambda = (i) -> System.out.println(i);
        consumerLambda.accept(1);
    }
}
