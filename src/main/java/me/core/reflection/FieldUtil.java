package me.core.reflection;

import java.lang.reflect.Field;

public abstract class FieldUtil {
    public static void nullFieldToDefault(Object target) throws IllegalAccessException {
        Field[] declaredFields = target.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.get(target) != null) {
                continue;
            }
            if (field.getType() == String.class) {
                field.set(target, "");
            } else if (field.getType() == Integer.class) {
                field.set(target, 0);
            }
        }
    }
}
