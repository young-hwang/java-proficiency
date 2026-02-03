package me.functional.functionalinterface;

// 추상 메소드가 하나만 존재하는 경우 함수형 인터페이스
@FunctionalInterface
public interface MakeFunctionalInterface {
    // 추상 메소드가 하나
    void doIt();

    // 추상 메소드 아님 - 함수형 인터페이스에 영향 없음
    static void printName() {
        System.out.println("young");
    }

    // 추상 메소드 아님 - 함수형 인터페이스에 영향 없음
    default void printAge() {
        System.out.println("38");
    }
}
