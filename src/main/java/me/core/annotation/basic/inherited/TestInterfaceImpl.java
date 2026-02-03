package me.core.annotation.basic.inherited;

public class TestInterfaceImpl implements TestInterface {
    public static void main(String[] args) {
        InheritedAnnotation annotation = TestInterfaceImpl.class.getAnnotation(InheritedAnnotation.class);
        System.out.println("annotation = " + annotation);

        NoInheritedAnnotation annotation1 = TestInterfaceImpl.class.getAnnotation(NoInheritedAnnotation.class);
        System.out.println("annotation1 = " + annotation1);
    }
}
