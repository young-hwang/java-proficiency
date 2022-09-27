import java.util.function.Function;

public class ProvideFunctionalInterface {
    public static void main(String[] args) {
        FunctionImpl function = new FunctionImpl();
        System.out.println(function.apply(1));

        Function<Integer, Integer> functionLambda = (i) -> i + 10;
        System.out.println(functionLambda.apply(1));
        Function<Integer, Integer> functionLambda1 = (i) -> i * 2;
        System.out.println(functionLambda.compose(functionLambda1).apply(1));
        System.out.println(functionLambda.andThen(functionLambda1).apply(1));
    }
}
