package me.core.reflection;

import me.core.reflection.data.BasicData;

import java.lang.reflect.Field;

public class FieldV1 {
    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("======== fields =======");
        Field[] fields = basicDataClass.getFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        System.out.println("======== declaredFields =======");
        Field[] declaredFields = basicDataClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("field = " + field);
        }
    }
}
