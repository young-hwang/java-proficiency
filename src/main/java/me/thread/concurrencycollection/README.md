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

```java
public class SyncList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size = 0;

    public SyncList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public synchronized int size() {
        return this.size;
    }

    @Override
    public synchronized void add(Object o) {
        elements[size] = o;
        sleep(100);
        size++;
    }

    @Override
    public synchronized Object get(int index) {
        return elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) + ", size = " + size + ", capacity = " + elements.length;
    }
}
```

`add()` 메소드에 `synchronized`를 통해 안전한 임계 영역을 만들었기 때문에, 한번에 하나의 스레드만 `add()` 메소드를 수행

# 동시성 컬렉션이 필요한 이유4 - 프록시 도입

`ArrayList`, `LinkedList`, `HashSet`, `HashMap`등의 코드도 모두 복사해서 `synchronized` 기능을 추가한 코드를 만들어야 하나?

- `ArrayList` -> `SyncArrayList`
- `LinkedList` -> `SyncLinkedList`

하지만 이렇게 코드를 복사해서 만들면 이후에 구현이 변경될 때 같은 모양의 코드를 2군대이서 변경해야 함

기존 코드를 그대로 사용면서 `synchronized` 기능만 살짝 추가하고 싶다면?

이럴 때 사용가능한 것이 프록시

## Proxy

우리말로 대리자

프록시가 대신 동기화(synchronized) 기능을 대신 처리하는 코드를 작성

**프록시 패턴(Proxy Pattern)**은 객체지향 디자인 패턴 중 하나

어떤 객체에 대한 접근을 제어하기 위해 그 객체의 대리인 또는 인터페이스 역할을 하는 객체를 제공하는 패턴

프록시 객체는 실제 객체에 대한 참조를 유지하면서, 그 객체에 접근하거나 행동을 수행하기 전에 추가적인 처리

**프록시 패턴의 주요 목적**

**접근 제어**: 실제 객체에 대한 접근을 제한하거나 통제할 수 있음

**성능 향상**: 실제 객체의 생성을 지연시키거나 캐싱하여 성능을 최적화할 수 있음

**부가 기능 제공**: 실제 객체에 추가적인 기능(로깅, 인증, 동기화 등)을 투명하게 제공

**참고**: 실무에서 프록시 패턴은 자주 사용, 스프링의 AOP 기능은 사실 이런 프록시 패턴을 극한으로 적용

# 자바 동시성 컬렉션 - synchronized

자바가 제공하는 `java.util` 패키지에 있는 컬렉션 프레임워크들은 대부분 스레드 안전(Thread Safe) 하지 않음

일반적으로 사용하는 `ArrayList` , `LinkedList` , `HashSet` , `HashMap` 등 수 많은 기본 자료 구조들은 내부에서 수 많은 연산들이 함께 사용

배열에 데이터를 추가하고 사이즈를 변경하고, 배열을 새로 만들어서 배열의 크기도 늘리고, 노드를 만들어서 링크에 연결하는 등 수 많은 복잡한 연산이 함께 사용

그렇다면 처음부터 모든 자료 구조에 `synchronized` 를 사용해서 동기화를 해두면 어떨까?

`synchronized` , `Lock` , `CAS` 등 모든 방식은 정도의 차이는 있지만 성능과 트레이드 오프존재

결국 **동기화를 사용하지 않는 것이 가장 빠름**

그리고 컬렉션이 항상 멀티스레드에서 사용되는 것도 아님

미리 동기화를 해둔다면 단일 스레드에서 사용할 때 동기화로 인해 성능이 저하

따라서 동기화의 필요성을 정확히 판단하고 꼭 필요한 경우에만 동기화를 적용하는 것이 필요

> **참고**: 과거에 자바는 이런 실수를 한번 했다. 
> 그것이 바로 `java.util.Vector` 클래스이다. 
> 이 클래스는 지금의 `ArrayList` 와 같은 기능을 제공하는데, 메서드에 `synchronized` 를 통한 동기화가 되어 있다. 
> 쉽게 이야기해서 동기화된 `ArrayList` 이다. 
> 그러나 이에 따라 단일 스레드 환경에서도 불필요한 동기화로 성능이 저하되었고, 결과적으로 `Vector` 는 널리 사용되지 않게 되었다. 
> 지금은 하위 호환을 위해서 남겨져 있고 다른 대안이 많기 때문에 사용을 권장하지 않는다`

좋은 대안으로 `synchronized`를 이용한 프록시를 만드는 방법

`List`, `Set`, `Map` 등 주요 인터페이를 구현해서 `synchronized`를 적용할 수 있는 프록를 만듬

이방법을 이용시 기존 코드를 그대로 유지 하면서 필요한 경우에만 동기화

자바는 콜렉션을 위한 프록시 기능 제공

**Collections.synchronizedList(target)**

```java
public static <T> List<T> synchronizedList(List<T> list) {
return new SynchronizedRandomAccessList<>(list);
}
```

이 코드는 결과적으로 다음과 같은 코드임

```java
new SynchronizedRandomAccessList<>(new ArrayList())
```

`SynchronizedRandomAccessList` 는 `synchronized` 를 추가하는 프록시 역할

- 클라이언트 ->`ArrayList` 
- 클라이언트 -> `SynchronizedRandomAccessList` -> (프록시) `ArrayList`
 
예를 들어서 이 클래스의 `add()` 메서드를 보면 `synchronized` 코드 블을 적용하고, 그 다음에 원본 대상의 `add()` 를 호출하는 것을 확인할 수 있음

```java
public boolean add(E e) {
    synchronized (mutex) {
        return c.add(e);
    }
}
```

코드 블럭을 적용하고, 그 다음에 원본 대상의 `Collections` 는 다음과 같이 다양한 `synchronized` 동기화 메서드를 지원

이 메서드를 사용하면 `List`, `Collection`, `Map`, `Set` 등 다양한 동기화 프록시를 만들어낼 수 있음

- `synchronizedList()`
- `synchronizedCollection()`
- `synchronizedMap()`
- `synchronizedSet()`
- `synchronizedNavigableMap()`
- `synchronizedNavigableSet()`
- `synchronizedSortedMap()`
- `synchronizedSortedSet()`
 
`Collections` 가 제공하는 동기화 프록시 기능 덕분에 스레드 안전하지 않은 컬렉션들을 매우 편리하게 스레드 안전한 컬렉션으로 변경해서 사용할 수 있음

**synchronized 프록시 방식의 단점**

하지만 `synchronized` 프록시를 사용하는 방식은 다음과 같은 단점이 존재

첫째, 동기화 오버헤드가 발생 

비록 `synchronized` 키워드가 멀티스레드 환경에서 안전한 접근을 보장하지만, 각 메서드 호출 시마다 동기화 비용이 추가됨

이로 인해 성능 저하가 발생할 수 있음

둘째, 전체 컬렉션에 대해 동기화가 이루어지기 때문에, 잠금 범위가 넓어질 수 있음

이는 잠금 경합(lock contention)을 증가시키고, 병렬 처리의 효율성을 저하시키는 요인이 됨 

모든 메서드에 대해 동기화를 적용하다 보면, 특정 스레드가 컬렉션을 사용하고 있을 때 다른 스레드들이 대기해야 하는 상황이 빈번해질 수 있음

셋째, 정교한 동기화가 불가능

`synchronized` 프록시를 사용하면 컬렉션 전체에 대한 동기화가 이루어지지만, 특정 부분이나 메서드에 대해 선택적으로 동기화를 적용하는 것은 어려움

이는 과도한 동기화로 이어질 수 있음

쉽게 이야기해서 이 방식은 단순 무식하게 모든 메서드에 `synchronized` 를 걸어버리는 것임

따라서 동기화에 대한 최적화가 이루어지지 않음 

자바는 이런 단점을 보완하기 위해 `java.util.concurrent` 패키지에 동시성 컬 렉션(concurrent collection)을 제공
