# 생산자 소비자 문제(producer-consumer problem)

- 생산자(Producer): 데이터를 생성하는 역할
- 소비자(Consumer): 생성된 데이터를 사용하는 역할
- 버퍼(Buffer): 생산자가 생성한 데이터를 일시적으로 저장하는 공간. 한정된 크기를 가지며 생산자와 소비자 사이 버퍼를 통해 데이터를 주고 받음

## 문제 상황

- 생성자가 너무 빠를 때: 버퍼가 가득차서 더 이상 데이터를 넣을 수 없을 때 까지 생산자가 데이터를 생성. 버퍼가 가득찬 경우 생산자는 버퍼에 빈 공간이 생길때 까지 기다려야 함
- 소비자가 너무 빠를 때: 버퍼가 비어서 더 이상 소비할 데이터가 없을 때까지 소비자가 데이터를 처리. 버퍼가 비었을 때 소비자는 버퍼에 새로운 데이터가 들어올 때까지 기다려야 함

생산자 소비자 문제(producer-consumer problem): 생산자 스레드와 소비자 스레드가 특정 자원을 함께 생산하고 소비하면서 발생하는 문제
한정된 버퍼 문제(bounded-buffer problem): 중간에 버퍼의 크기가 한정되어 있기 때문에 발생.

## Sample

생산자 소비자 문제 예제 만들기

```java
public interface BoundedQueue {
    void put(String data);

    String take();
}
```

- BoundedQueue: 버퍼 역할을 하는 큐의 인터페이스
- put(data): 버퍼에 데이터를 보관(생성자 스레드가 호출, 데이터 생성)
- take(): 버퍼에 보관된 데이터 가져감(소비자 스레드가 호출, 데이터 소비)

```java
public class BoundedQueueV1 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() == this.max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.add(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
```

- BoundedQueueV1: 한정된 버퍼 역할을 하는 가장 단순한 구현체
- Queue, ArrayDeque: 데이터를 중간에 보관하는 버퍼 큐(Queue), 구현체로 ArrayDequeue 사용
- int max: 한정된 버퍼으므로 버퍼에 저장할 수 있는 최대 크기 지정
- put(): 큐에 데이터 저장. 큐가 가득찬 경우 데이터 버림
- take(): 큐의 데이터 가져가기. 큐에 데이터 없는 경우 null
- toString(): 버퍼 역할을 하는 queue 정보를 출력

**임계 영역**

핵심 공유 자원은 queue(ArrayDequeue) 임
여러 스레드가 접근할 예정이므로 synchronized를 사용해서 한번에 하나의 스레드만 put() 또는 take()를 실행할 수 있도록 안전한 임계 영역 생성

## Object - wait, notify

synchronized 를 사용한 임계 영역 안에서 락을 가지고 무한 대기하는 문제는 Object 클래스에 해결 방안 존재
Object 클래스는 wait(), notify()라는 메소드를 제공
Object 는 모든 자바 객체의 부모이기 때문에 자바의 기본 기능이라 생각하면 됨

**wait(), notify()**

- Object.notify()
    - 현재 스레드가 가진 락을 반납하고 대기(WAITING)
    - 현재 스레드를 대기(WAITING) 상태로 전환. 현재 스레드가 synchronized 블록이나 메서드에서 락을 소유하고 있을 때만 호출할 수 있음
    - 호출한 스레드는 락을 반납하고 다른 스레드가 해당 락을 획득할 수 있도록 함
    - 대기 상태로 전환된 스레드는 다른 스레드가 notify() 또는 notifyAll() 을 호출할 때까지 대기 상태를 유지
- Object.notify()
    - 대기 중인 스레드 중 하나를 깨움
    - synchronized 블록이나 메소드에서 호출되어야 함
    - 깨운 스레드는 락을 다시 획득할 기회를 얻게 되며 만약 대기 중인 스레드가 여러 개라면 그 중 하나만 깨움
- Object.notifyAll()
    - 대기 중인 모든 스레드를 깨움
    - synchronized 블록이나 메소드에서 호출되어야 함
    - 모든 대기중인 스레드가 락을 획득할 수 있는 기획를 얻게 됨
    - 모든 스레드를 깨워야 할 필요가 있을 경우 유용

**스레드 대기 집합(wait set)**

synchronized 임계 영역 안에서 Object.wait() 호출하면 스레드는 대기(WAITING) 상태에 들어감
대기 상태에 들어간 스레드를 관리하는 것을 대기 집합(wait set) 이라함
모든 객체는 각자의 대기 집함을 가지고 있음

모든 객체는 락(모니터 락)과 대기 집합을 가짐
둘은 한쌍으로 사용
락을 획득한 객체의 대기 집합을 사용해야 함

- synchronized 메소드를 적용하면 해당 인스턴스의 락을 사용

## Object - wait, notify 한계

Object.wait(), Object.notify() 방식은 스레드 대기 집합 하나에 생산자, 소비자 스레드를 모두 관리
notify()를 호출 시 임의의 시레드가 선택

| 예제처럼 큐에 데이터가 없는 상황에 소비자가 같은 소비자를 깨우는 비효율이 발생

**같은 종류의 스레드를 깨울 때 비효율이 발생한다**

생상자가 같은 생산자를 깨우거나, 소비자가 같은 소비자를 깨울 때 비효율이 발생할 수 있음
생산자가 소비자를 깨우고, 반대로 소비자가 생산자를 깨운다면 비효율이 발생하지 않음

### 스레드 기아(Thread starvation)

notify() 의 또 다른 문제점으로 어떤 스레드가 깨어날지 알 수 없기 때문에 발생하는 스레드 기아 문제
대기 상태의 스레드가 실행 순서를 얻지 못해 실행되지 않는 상황을 Thread starvation 이라 함

## notifyAll()

대기 집합(Wait Set)의 모든 스레드를 깨움
락을 획득하지 못하면 BLOCKED 상태가 됨
결과적으로 notifyAll() 을 사용해서 스레드 기아 문제는 막을 수 있지만, 비효율을 막지 못함

## Lock Condition

생산자가 생산자를 깨우고, 소비자가 소비자를 깨우는 비효율 문제 어떻게 해결하나?

**해결 방안**

핵심은 생산자 스레드는 데이터를 생성하고 대기중인 소비자 스레드에게 알려주어야 함
반대로 소비자 스레드는 데이터를 소비하고 대기중인 생산자 스레드에게 알려주어야 함
결국 생산자 스레드가 대기하는 대기 집합과 소비자 스레드가 대기하는 대기 집합을 둘로 나누면 됨
생산자 스레드가 데이터를 생산하면 소비자 스레드가 대기하는 대기 집합에만 알려줌
반대로 소비자 스레드가 데이터를 소비하면 생산자 스레드가 대기하는 대기 집합에만 알려줌

그럼 대기 집합을 어떻게 분리할 것인가?
Lock, ReentrantLock 을 사용하면 됨

`synchronized`  대신에 `Lock lock = new ReentrantLock`을 사용

**Condition**

`Condition condition = lock.newCondition()`
`Condition`은 `ReentrantLock`을 사용하는 스레드가 대기하는 스레드 대기 공간
`lock.newCondition()` 메소드를 호출하면 스레드 대기 공간이 만들어짐

참고로 `Object.wait`에서 사용한 대기 공간은 모든 객체 인스턴스가 내부에 기본으로 가지고 있음
반면 `Lock(ReentrantLock)`을 사용하는 경우 스레드 대기 공간을 직접 만들어서 사용 필요

**condition.await()**

`Object.wait()`와 유사한 기능
지정한 `condition`에 현재 스레드를 대기(WAITING) 상태로 보관
이때 `ReentrantLock`에서 획득한 락을 반납하고 대기 상태로 `condition`에 보관

**condition.signal()**

`Object.notify()`와 유사한 기능
지정한 `condition`에 대기중인 스레드를 하나 깨움
깨어난 스레드는 `condition`에서 빠져나옴

### Object.notify() vs Condition.signal()

**Object.notify()**

대기 중인 스레드 중 임의의 하나를 선택하여 깨움
스레드가 깨어나는 순서가 정의되지 않음(JVM 구현에 따라 다름) - 보통 먼저 들어온 스레드가 먼저 수행
`synchronized` 블록 내에서 모니터 락을 가지고 있는 스레가 호출하여야 함

**Condition.signal()**

대기 중인 스레드 중 하나를 깨움
일반적으로 FIFO 순서로 깨우지만 자바 버전 구현에 따라 달라질 수 있음
`Condition`의 구현은 `Queue`구조를 사용하므로 FIFO로 깨움
`ReentrantLock`을 가지는 스레드가 호출해야 함

### 스레드 대기

**synchronized 대기**

- 대기1: 락 획득 대기
    - `BLOCKED` 상태로 락 획득 대기
    - `synchronized`를 시작할 때 락이 없으면 대기
    - 다른 스레드가 `synchronized`를 빠져 나갈 때 대기가 풀리며 락 획득 시도
- 대기2: wait() 대기
    - `WAITING` 상태로 대기
    - `wait()`를 호출 했을 때 스레드 대기 집합에서 대기
    - 다른 스레드가 `notify()`를 호출 했을 때 빠져나감

자바의 모든 객체 인스턴스는 멀티스레드와 임계 영역을 다루기 위해 내부에 3가지 기본 요소를 가짐

- 모니터 락
- 락 대기 집합(모니터 락 대기 집합)
- 스레드 대기 집합

락 대기 집합이 1차 대기소, 스레드 대기 집합이 2차 대기소
2차 대기소에 들어간 스레드는 2차, 1차 대기소를 모두 빠나와야 임계 영역을 수행할 수 있음

이 3가지 요소는 맞물려 작동

- `synchronized`를 사용한 임계 영역에 들어가려면 모니터 락이 필요
- 모니터 락이 없으면 락 대기 상태로 들어가서 `BLOCKED` 상태로 락을 기다림
- 모니터 락을 반납하면 락 대기 집합에 있는 스레드 중 하나가 락을 획득하고 `BLOCKED -> RUNNABLE` 상태가 됨
- `wait()`를 호출해서 스레드 대기 집합에 들어가기 위해서는 모니터 락이 필요
- 스레드 대기 집합에 들어가면 모니터 락 반납 필요
- 스레드가 `notify()`를 호출하면 스레드 대기 집합에 있는 스레드 중 하나가 스레드 대기 집합을 빠져 나와 모니터 락 획득 시도
    - 모니터 락 획득 시 임계 영역 수행
    - 모니터 락 획득 못 할시 락 대기 집합에 들어가 `BLOCKED` 상태로 락을 대기

### synchronized vs ReentrantLock 대기

`synchronized`와 마찬가지로 `Lock(ReentrantLock)`도 2가지 단계의 대기 상태 존재
같은 개념을 구현한 것으로 비슷

### ReentrantLock 대기

- 대기1: `ReentrantLock` 락 획득 대기
    - `ReentrantLock`의 대기 큐에서 관리
    - `WAITING` 상태로 락 획득 대기
    - `lock.lock()`을 호출 했을 때 락이 없으면 대기
    - 다른 스레드가 `lock.unlock()`을 호출 했을 때 대기가 풀리며 락 획득 시도, 락을 획득하면 대기 큐에서 빠져나감
- 대기2: `await()` 대기
    - `condition.await()`를 호출 했을 때, `condition`객체의 스레드 대기 공간에서 관리
    - `WAITING` 상태로 대기
    - 다른 스레드가 `condition.signal()`을 호출 했을 때 `condition` 객체의 스레드 대기 공간에서 빠져나감

## BlockingQueue

`BoundedQueue`를 스레드 관점에서 보면 큐가 특정 조건을 만족할 때까지 스레드의 작업을 차단(BLOCKING) 함

- **데이터 추가 차단**: 큐가 가득차면 데이터 추가 작업(put())을 시도하는 스레드는 공각이 생길때 까지 차단
- **데이터 획득 차단**: 큐가 비어 있으면 획득 작업(take())을 시도하는 스레드는 큐에 데이터가 들어올 때까지 차단

그래서 스레드 관점에서 이 큐에 이름을 지어보면 `BlockingQueue`라는 이름이 적절

자바는 생산 소비자 문제, 또는 한정된 버퍼라고 불리는 문제를 해결하기 위해 `java.util.concurrent.BlockingQueue`라는 인터페이스와 구현체들을 제공함

```java
package java.util.concurrent;

public interface BlockingQueue<E> extends Queue<E> {
    boolean add(E e);

    boolean offer(E e);

    void put(E e) throws InterruptedException;

    boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    E take() throws InterruptedException;

    E poll(long timeout, TimeUnit unit) throws InterruptedException;

    boolean remove(Object o);

    // ...
}
```

- **데이터 추가 메소드**: `add()`, `offer()`, `put()`, `offer(timeout)`
- **데이터 획득 메소드**: `take()`, `poll(timeout)`, `remove()`
- `Queue` 상속을 받으므로 큐의 기능도 사용 가능

### BlockingQueue의 대표 구현체

- `ArrayBlockingQueue`: 배열 기반으로 구현되어 있고, 버퍼의 크기가 고정되어 있음
- `LinkedBlockingQueue`: 링크 기반으로 구현되어 있고, 버퍼의 크를 고정할 수도, 무한하게 사용할 수도 있음

| 참고: `Deque`용 동시성 자료 구조인 `BlockingDeque`도 존재

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue {
    final Object[] items;
    int count;
    ReentrantLock lock;
    Condition notEmpty; // 소비자 스레드가 대기하는 condition
    Condition notFull; // 생산자 스레드가 대기하는 condition

    public void put(E e) throws InterruptedException {
        Objects.requireNonNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.await();
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E e) {
        items[putIndex] = e;
        count++;
        notEmpty.signal();
    }
}
```

우리가 구현한 기능과 차이점은 `lock.lock()`대신 `lock.lockInterruptibly()`를 사용한 점과 내부 자료 구조의 차이 정도임

## BlockingQueue - 기능 설명

실무에서 멀티스레드를 사용할 때는 응답성이 중요
대기 상태에 있어도 고객이 중지 요청을 하거나 너무 오래 대기한 경우 포기하고 빠져나갈 수 있는 방법이 필요
e.g. 생산자가 무언가 데이터를 생산하는 과정에 버퍼가 빠지지 않아서 너무 오래 대기해야 한다면 무한정 기다리는 것 보다는 작업을 포기하고 "시스템에 문제가 있습니다" 라고 하는 것이 더 나은 선택

큐가 가득 찼을 때 4가지 선택지

- 예외를 던짐, 예외를 받아서 처리
- 대기하지 않음, 즉시 `false` 반환
- 대기
- 특정 시간 만큼 대기

**BlockingQueue의 다양한 기능 - 공식 API 문서**

| Operation  | Throws Exception | Special Value | Blocks | Times Out             |
|:----------|:----------------|:-------------|:------|:----------------------|
| Insert(추가) |      add(e)      |   offer(e)    | put(e) | offer(e, time, unit)  |
| Remove(제거)    |   remove()    | poll() |        take()        | poll(time, unit)      |
| Examine(관찰)    |   element()   | peek() |    not applicable    | not applicable        |

**Throws Exception - 대기 시 예외**

- add(e): 지정된 요소를 큐에 추가하며 큐가 가득차면 `IllegalStateException` 예외를 던짐
- remove(): 큐에서 요소를 제거하며 반환, 큐가 비어 있으면 `NoSuchElementException` 예외를 던짐
- element(): 큐의 머리 요소를 반환하지만, 요소를 큐에서 제거하지 않음. 큐가 비어 있으면 `NoSuchElementException` 예외를 던짐
 
**Special Value - 대기 시 즉시 반환**

- offer(e): 지정된 요소를 큐에 추가하려고 시도, 큐가 가득하면 `false`를 반환
- poll(): 큐에서 요소를 제거하고 반환, 큐가 비어 있으면 `null`를 반환
- peek(): 큐의 머리 요소를 반환, 요소를 큐에서 제거하지 않음, 큐가 비었으면 `null` 반환
 
**Blocks - 대기**

- put(e): 지정된 요소를 큐에 추가할 때까지 대기, 큐가 가득차면 공간이 생길 때까지 대기
- take(): 큐에서 요소를 제거하고 반환, 큐가 비어 있으면 요소가 준비될 때까지 대기
- Examine(조회): 해당 사항 없음

**Times Out - 시간 대기**

- offer(e, time, unit): 지정된 요소를 큐에 추가, 지정된 시간 동안 큐가 비워지기를 기다리나 시간 초과 시 `false` 반환
- poll(time, unit): 큐에서 요소를 제거하고 반환, 큐에 요소가 없으면 지정된 시간 동안 요소가 준비되지를 기다리다가 시간 초과 시 `null` 반환
- Examine(조회): 해당 사항 없음

참고로 `BlockingQueue`의 모든 대기, 시간 대기 메소드는 인터럽트를 제공

