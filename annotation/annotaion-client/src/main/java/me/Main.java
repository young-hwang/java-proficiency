package me;

public class Main {
    public static void main(String[] args) {
        Person person = new PersonBuilder().setAge(25).setName("John").build();
        System.out.println("Hello world!, " + person.getName());
    }
}