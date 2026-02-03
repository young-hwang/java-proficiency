package me.core.reflection;

import me.core.reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 정적 메서드 호출
        BasicData basicData = new BasicData();
        basicData.call();

        // 동적 메서드 호출 - 리플렉션 사용
        Class<? extends BasicData> basicDataClass = basicData.getClass();
        String methodName = "hello";
        Method declaredMethod = basicDataClass.getDeclaredMethod(methodName, String.class);
        System.out.println("declaredMethod = " + declaredMethod);
        Object result = declaredMethod.invoke(basicData, "young");
        System.out.println("result = " + result);
    }
}
