package io.younghwang.java8;

import java.util.function.Supplier;

public class ProvideSupplierInterface {
    public static void main(String[] args) {
        // T 타입의 값을 제공 받는 함수 인터페이스
        Supplier<Integer> supplierLambda = () -> 10;
        System.out.println(supplierLambda.get());
    }
}
