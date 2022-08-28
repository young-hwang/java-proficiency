package io.younghwang.java8;

public class RunFunctionalInterface {
    public static void main(String[] args) {
        // Anonymous Class
        MakeFunctionalInterface anonymousClass = new MakeFunctionalInterface() {
            @Override
            public void doIt() {
                System.out.println("Hello World!");
            }
        };
        anonymousClass.doIt();

        // lambda
        MakeFunctionalInterface lambdaExpression = () -> System.out.println("Hello World!");
        lambdaExpression.doIt();
    }
}
