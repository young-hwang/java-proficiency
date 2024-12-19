package me.reflection;

import me.reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("========= methods() =========");
        Method[] methods = basicDataClass.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("========= declaredMethods() =========");
        Method[] declaredMethods = basicDataClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("declaredMethods = " + method);
        }
    }
}
