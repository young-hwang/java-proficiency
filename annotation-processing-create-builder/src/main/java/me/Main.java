package me;

import me.user.Person;
import me.user.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PersonBuilder personBuilder = new PersonBuilder();
        Person young = personBuilder.setAge(10).setName("Young").build();
        System.out.println(young.getAge() + " " + young.getName());
    }
}