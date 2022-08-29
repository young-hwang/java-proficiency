package io.younghwang.java8;

public interface DefaultInterface {
    void printName();

    // interface 구현 후 추상 메소드 추가 시 구현 클래스 모두 수정이 필요
    // 이를 피하기 위하여 default 메소드를 구현
    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 변경하여 출력
     */
    default void printNameUpper() {
        System.out.println(getName().toUpperCase());
    };

    // Object 에서 기본 제공하는 메소드는 재정의 할 수 없음
    // 구현체에서 재정의 가능
    // deafult String toString() {
    // }

    String getName();

    // static 으로 유틸 메소드 생성
    static void printStatic() {
        System.out.println("Static");
    }
}
