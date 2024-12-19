package me.reflection;

import me.reflection.data.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Scanner;

public class MethodV3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 동적 메서드 호출 예제
        Scanner scanner = new Scanner(System.in);
        System.out.println("호출 메서드: ");
        String method = scanner.nextLine();
        System.out.println("숫자1: ");
        int num1 = scanner.nextInt();
        System.out.println("숫자2: ");
        int num2 = scanner.nextInt();

        Calculator calculator = new Calculator();
//        Class<? extends Calculator> calculatorClass = calculator.getClass();
        Class<? extends Calculator> calculatorClass = Calculator.class;
        Method declaredMethod = calculatorClass.getDeclaredMethod(method, int.class, int.class);
        Object result = declaredMethod.invoke(calculator, num1, num2);
        System.out.println("result = " + result);
    }
}
