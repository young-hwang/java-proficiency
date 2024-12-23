package me.web.annotation.mapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TestController testController = new TestController();
        Class<? extends TestController> aClass = testController.getClass();
        for (Method method : aClass.getDeclaredMethods()) {
            System.out.println("method: " + method);
            SimpleMapping annotation = method.getAnnotation(SimpleMapping.class);
            if (annotation != null) {
                String value = annotation.value();
                System.out.println("value: " + value + ", method: " + method);
                method.invoke(testController);
            }
        }
    }
}
