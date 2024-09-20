# 원자적 연산(atomic operation)

원자적 연산(atomic operation)의 의미는 해당 연산이 더 이상 나눌 수 없는 단위로 수행된다는 의미
즉, 중단되지 않고 다른 연산과 간섭 없이 완전히 실행되거나 전혀 실행되지 않는 성질을 가짐
멀티 스레드 상황에서 다른 스레드의 간섭 없이 안전하게 처리되는 연산이라는 뜻

> 참고: 과거에 원자는 더 이상 나눌 수 없는 가장 작은 단위로 여겨짐.
> 현대 물리학에서는 원자가 더 작은 입자들로 구성되어 있다는 것이 밝혀 졌음. 하지만 원자적 연산이라는 단어를 그대로 사용

```java
volatile int i = 0;
```

위의 연산에서 `i = 0`은 둘로 쪼갤수 없는 원자적 연산
왜냐면 이 연산은 단 하나의 써로 실행되기 때문
- 오른쪽에 있는 `1`의 값은 왼쪽의 `i` 변수에 대입

하지만 다음 연산은 원자적 연산이 아님

```java
i = i + 1;
```

왜냐면 다음 순서로 나누어 실행되기 때문
- 오른쪽에 있는 `i`의 값을 읽음, `i`의 값은 10
- 읽은 10에 1을 더해서 11을 만듬
- 더한 11을 왼쪽의 `i` 변수에 대입

원자적 연산은 멀티스레드 상황에서 아무런 문제가 발생하지 않음
원자적 연산이 아닌 경우 `synchronized` 블럭이나 `lock` 등을 사용해서 안전한 임계 영역을 만들어야함

## 원자적 연산 - 시작

원자적이지 않은 연산을 멀티스레드 환경에서 실행하면 문제가 발생
`IncrementInteger`는 숫자 값을 하나씩 증가시키는 기능 제공

```java
public interface IncrementInteger {
    void increment();
    int get();
}
```

- `IncrementInteger`는 값을 증가하는 기능을 가진 숫자 기능을 제공하는 인터페이스
- `increment()` 값을 하나 증가
- `get()` 값을 조회

```java
public class BasicInteger implements IncrementInteger {
    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
```


```java
public class VolatileInteger implements IncrementInteger {
    private volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
```

`VolatileInteger`도 여전히 1000이 아닌 더 작은 숫자가 나옴
`volatile`은 여러 cpu 사이에 발생하는 캐시 메모리와 메인 메모리가 동기화 되지 않는 문제를 해결
`volatile`을 사용하면 cpu의 캐시 메모리를 사용하지 않고, 메인 메모리를 직접 사용하지만 여전히 문제 존재
즉 `volatile`은 연산 자체를 원자적으로 묶어주는 기능이 아님

연산 자체가 나누어진 경우 `synchronized` 블럭이나 `Lock` 등을 사용해서 안전한 임계 영역을 만들필요가 있음

## Synchronized

```java
public class SynchronizedInteger implements IncrementInteger {
    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
```

`synchronized`를 통해 안전한 임계 영역을 만들고 `value++` 연산을 수행 시 정확히 1000 결과 도출

## 원자적 연산 - AtomicInteger

`SynchronizedInteger`와 같이 멀티스레드 상황에서 안전하게 증가 연산을 수행하는 `AtomicInteger` 클래스 제공

```java

public class MyAtomicInteger implements IncrementInteger {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.intValue();
    }
}
```

## 성능 테스트

1억번의 count 증가 연산 수행 시간 측정

```java
public class IncrementThreadPerformanceMain {
    public static final Long COUNT = 100_000_000L;

    public static void main(String[] args) throws InterruptedException {
        IncrementInteger basicInteger = new BasicInteger();
        IncrementInteger volatileInteger = new VolatileInteger();
        IncrementInteger synchronizedInteger = new SynchronizedInteger();
        IncrementInteger atomicInteger = new MyAtomicInteger();

        test(basicInteger);
        test(volatileInteger);
        test(synchronizedInteger);
        test(atomicInteger);
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        long startMs = System.currentTimeMillis();

        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();
        log(incrementInteger.getClass().getName() + " : " + (endMs - startMs) + "ms");
    }
}
```
**수행 결과**

```bash
20:22:15.761 [     main] me.thread.atomicoperation.BasicInteger : 50ms
20:22:16.481 [     main] me.thread.atomicoperation.VolatileInteger : 716ms
20:22:17.611 [     main] me.thread.atomicoperation.SynchronizedInteger : 1129ms
20:22:18.438 [     main] me.thread.atomicoperation.MyAtomicInteger : 827ms
```

**BasicInteger**
- 가장 빠름
- cpu 캐시를 적극 활용
- 안전한 임계 영역이 없고, `volatile`을 사용하지 않아 멀티스레드에서 사용 불가
- 단일 스레드 사용 시 효율적

**VolatileInteger**
- `volatile`을 사용해 cpu 캐시를 사용하지 않고 메인 메모리 사용
- 안전한 임계 영역 없어 멀티스레드 사용 불가
- 단일 스레드 사용 시 `BasicInteger` 보다 느리며 멀티스레드에 안전하지도 않음

**SynchronizedInteger**
- `synchronized`를 이용한 안전한 임계 영역이 있기에 멀티스레드에서 안전
- `MyAtomicInteger` 보다 성능이 느림

**MyAtomicInteger**
- 자바가 제공하는 `AtomicInteger`사용, 멀티스레드에서 안전
- 성능도 `syncronized`, `Lock(ReentrantLock)`을 사용하는 것보다 1.5~2배 빠름

