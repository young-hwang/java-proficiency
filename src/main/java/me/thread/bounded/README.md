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
public class BoundedQueueV1 implements BoundedQueue{
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




