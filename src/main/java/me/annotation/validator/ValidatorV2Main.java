package me.annotation.validator;

import java.lang.reflect.Field;

import static me.util.MyLogger.log;

public class ValidatorV2Main {
    public static void main(String[] args) {
        User user = new User("user", 0);
        Team team = new Team("", 1);

        try {
            log("== user 검증 ===");
            validate(user);
        } catch (Exception e) {
            log(e);
        }

        try {
            log("== team 검증 ===");
            validate(team);
        } catch (Exception e) {
            log(e);
        }
    }

    public static void validate(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotEmpty.class)) {
                String value = (String)field.get(object);
                NotEmpty annotation = field.getAnnotation(NotEmpty.class);
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException(annotation.message());
                }
            }

            if (field.isAnnotationPresent(Range.class)) {
                int value = (int)field.get(object);
                Range annotation = field.getAnnotation(Range.class);
                if (value < annotation.min() || value > annotation.max()) {
                    throw new RuntimeException(annotation.message());
                }
            }
        }
    }
}
