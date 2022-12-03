package me.defaultmethod;

public class DefaultInterfaceImpl implements DefaultInterface {
    private String name;

    public DefaultInterfaceImpl(String name) {
        this.name = name;
    }

    // default method overriding
    @Override
    public void printNameUpper() {
        System.out.println(this.getName().toLowerCase());
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
