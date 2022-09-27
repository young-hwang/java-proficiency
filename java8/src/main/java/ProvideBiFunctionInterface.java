import java.util.function.BiFunction;

public class ProvideBiFunctionInterface {
    public static void main(String[] args) {
        // 두개의 값을 받아서 R 타입을 return
        BiFunction<Integer, Integer, Integer> biFunctionLambda = (i, j) -> i + j;
        System.out.println(biFunctionLambda.apply(1, 2));
    }
}
