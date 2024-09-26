# 동시성 컬렉션이 필요한 이유 - 시작

여러 스레드가 동시에 접근해도 괜찮은 경우를 스레드 세이프(Thread Safe)하다고 함

`ArrayList`는 스레드 세이프 한가?

```java
public class SimpleListMainV0 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 스레드1, 스레드2가 동시에 실행 가정
        list.add("A"); // 스레드 1 실행 가정
        list.add("B"); // 스레드 2 실행 가정
        System.out.println(list);
    }
}
```

# 동시성 컬렉션이 필요한 이유2 - 동시성 문제

멀티 스레드 문제 확인

add() - 원자적이지 않은 연산

```java
public void add(Object o) {
    elements[size] = o;
    sleep(100);
    size++;
}
```

원자적이지 않은 연산을 멀티스레드 상황에 안전하게 사용하려면 `synchronized`, `Lock` 등을 사용해야 함

**실행 결과**

컬렉션에 데이터를 추가하는 `add()` 메소드는 단순히 컬렉션에 데이터를 하나 추가함

마치 연산이 하나만 있는 연산처럼 느껴짐(원자적 연산은 멀티스레드 상황에 문제가 되지 않음)

스레드 실행 순서에 의해 [A, B] 혹은 [B, A]로 데이터가 저장될 수 있으나 모두 안전하게 저장됨

하지만 컬렉션 프레임워크가 제공하는 대부분의 연산은 원자적 연산이 아님

`ArrayList`, `LinkedList`, `HashSet`, `HashMap`등 수많은 자료 구조들은 단순한 연산을 제공하는 것 처럼 보임

하지만 내부적으로 수많은 연산들이 함께 사용

따라서 일반적인 컬렉션들은 절대로 스레드 세이프하지 않음

여러 스레드가 동시에 컬렉션에 접근하는 경우라면 `java.util` 패키지가 제공하는 일반적인 컬렉션은 사용하면 안됨


# 동시성 컬렉션이 필요한 이유3 - 동기화