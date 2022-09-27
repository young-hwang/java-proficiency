import java.util.function.UnaryOperator;

public class ProvideUnaryOperatorInterface {
    public static void main(String[] args) {
        // function 함수 인터페이스의 특별한 형태
        // 입력값 하나를 받아서 동일한 형태로 리턴
        UnaryOperator<Integer> unaryOperatorLambda = (i) -> i + 10;
        System.out.println(unaryOperatorLambda.apply(1));
    }
}
