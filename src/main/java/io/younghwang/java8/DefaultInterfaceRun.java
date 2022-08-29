package io.younghwang.java8;

public class DefaultInterfaceRun {
    public static void main(String[] args) {
        DefaultInterface obj = new DefaultInterfaceImpl("young");
        obj.printName();
        obj.printNameUpper();
        DefaultInterface.printStatic();
    }
}
