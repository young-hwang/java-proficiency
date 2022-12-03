package me.functionalinterface;

import java.util.function.BinaryOperator;

public class ProvideBinaryOperatorInterface {
    public static void main(String[] args) {
        // BiFunction 함수 인터페이스의 특수한 형태, 상속
        // 동일한 타입의 값 두개를 받아서 값을 return
        BinaryOperator<Integer> binaryOperatorLambda = (i, j) -> i + j;
        System.out.println(binaryOperatorLambda.apply(1, 2));
    }
}
