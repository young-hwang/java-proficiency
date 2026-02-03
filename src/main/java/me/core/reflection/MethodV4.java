package me.core.reflection;

import me.core.reflection.data.Calculator;
import me.core.reflection.data.User;

import java.lang.reflect.InvocationTargetException;

public class MethodV4 {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("id1", null, null);
        Team team = new Team("team1", null);

        FieldUtil.nullFieldToDefault(user);
        FieldUtil.nullFieldToDefault(team);
        System.out.println("user = " + user.getName() + ", " + user.getAge());
        System.out.println("team = " + team.getName());
    }
}
