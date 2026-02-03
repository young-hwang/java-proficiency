package me.functional.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaExpressionTest {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.run();
    }

    private static class TestClass {
        private void run() {
            // effective final
            // 암묵적으로 baseNumber는 final
            // 로컬 클래스, 익명 클래스에서 baseNumber 를 참조하므로 final 로 지정된 것과 같다.
            int baseNumber = 10;

            // 로컬 클래스
            class LocalClass {
                void printBaseNumber(Integer i) {
                    System.out.println(i + baseNumber);
                }
            }

            // 익명 클래스
            Consumer integerConsumer = new Consumer<Integer>() {
                @Override
                public void accept(Integer i) {
                    System.out.println(i + baseNumber);
                }
            };

            // 람다
            // run 메소드와 동일한 스코프를 가짐
            // lambda 선언 시 baseNumber 변수명을 가질 수 없음
            IntConsumer lambdaConsumer = (i) -> System.out.println(i + baseNumber);
            lambdaConsumer.accept(1);
        }
    }
}
