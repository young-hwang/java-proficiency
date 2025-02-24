package reflection;

import java.lang.reflect.Field;

public class ReflectionTestMain {


    public class ClassA {
        public static String name = "AA";
    }

    public static void main(String[] args) throws IllegalAccessException {
        showFields(ClassA.class);
    }

    private static void showFields(Class clazz) throws IllegalAccessException {
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field.getName());
        }
        System.out.println("========");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = (String)field.get(null);
            System.out.println(name);
        }
    }

}
