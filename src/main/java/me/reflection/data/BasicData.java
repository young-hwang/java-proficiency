package me.reflection.data;

public class BasicData {
    public String publicField;
    private int privateField;

    public BasicData() {
        System.out.println("BasicData.BasicData()");
    }

    public BasicData(String publicField) {
        this.publicField = publicField;
        System.out.println("publicField = " + publicField);
    }

    public void call() {
        System.out.println("BasicData.call()");
    }

    public String hello(String name) {
        System.out.println("BasicData.hello()");
        return "Hello " + name;
    }

    private void privateMethod() {
        System.out.println("BasicData.privateMethod()");
    }

    void defaultMethod() {
        System.out.println("BasicData.defaultMethod()");
    }

    protected void protectedMethod() {
        System.out.println("BasicData.protectedMethod()");
    }

    public void publicMethod() {
        System.out.println("BasicData.publicMethod()");
    }
}
