package functionalinterface;

import java.util.function.Predicate;

public class ProvidePredicateInterface {
    public static void main(String[] args) {
        // T 타입을 받아서 boolean을 return 하는 함수 인터페이스
        // 함수 조합용 메서드 : And, Or, Negate
        Predicate<Integer> predicateLambda = (i) -> i % 2 == 0;
        System.out.println(predicateLambda.test(2));
    }
}
