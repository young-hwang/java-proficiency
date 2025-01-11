package me.object.reference.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StrongReferenceStack implements Stack {
    private Object[] stack;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 16;

    public StrongReferenceStack() {
        stack = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void push(Object o) {
        ensureCapacity();
        stack[size++] = o;
    }

    private void ensureCapacity() {
        if (size == stack.length) {
            stack = Arrays.copyOf(stack, size * 2 - 1);
        }
    }

    @Override
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = stack[--size];
        stack[size] = null; // 다른 참조 해제 - 명시적임
        return result;
    }
}
