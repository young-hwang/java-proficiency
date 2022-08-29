package io.younghwang.java8;

public interface DefaultChildInterface extends DefaultInterface {
    // 부모의 default method 를 다시 추상 메소드로 변경 가능
    void printNameUpper();
}
