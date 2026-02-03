package me.core.reflection;

import me.core.reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "user A", 20);
        System.out.println("user name = " + user.getName());

        Class<? extends User> aClass = user.getClass();
        Field nameField = aClass.getDeclaredField("name");

        nameField.setAccessible(true);
        nameField.set(user, "user B");
        System.out.println("user name = " + user.getName());
    }
}
