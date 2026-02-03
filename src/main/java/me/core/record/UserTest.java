package me.core.record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTest {

    public static void main(String[] args) {
        String[] data = {"a", "b"};
        User user = new User("young", "password", new ArrayList<>(List.of("1", "2")), data);
        List<String> roles = user.roles();
        roles.add("3");
        System.out.println("roles: " + roles);
        System.out.println("roles: " + user.roles());

        String[] data1 = user.data();
        data1[0] = "c";
        System.out.println("data1: " + Arrays.toString(data1));
        System.out.println("data1: " + Arrays.toString(user.data()));
    }
}
