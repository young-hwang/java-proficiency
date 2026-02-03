package me.util.object.reference.stack;

public class StackExampleMain {
    public static void main(String[] args) {
        Stack stack = new StrongReferenceStack();
        for(String arg : args) {
            stack.push(arg);
        }

        while(true) {
            System.out.println(stack.pop());
        }
    }
}
