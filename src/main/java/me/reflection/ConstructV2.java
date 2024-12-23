package me.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("me.reflection.data.BasicData");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object instance = declaredConstructor.newInstance("hi");
        System.out.println("instance = " + instance);

        Method declaredMethod = aClass.getDeclaredMethod("call");
        declaredMethod.invoke(instance);
    }
}
