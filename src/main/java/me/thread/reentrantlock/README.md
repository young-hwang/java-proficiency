# ReentrantLock

자바는 1.0부터 존재한 synchronized와 BLOCKED 상태를 통한 임계 영역 관리의 한계를 극복하기 위해 자바 1.5부터 Lock 인터페이스와 ReentrantLock 구현제를 제공한다.

**synchronized 단점**
- 무한 대기: 'BLOCKED' 상태의 스레드는 락이 풀릴 때 까지 무한 대기한다.
    - 특정 시간 까지만 대기하는 타임 아웃 X
    - 중간에 인터럽트 X
- 공정성: 락이 돌아왔을 때 'BLOCKED' 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 할 수 없다. 최악의 경우 특정 스레드가 너무 오랜 기간 락을 획득하지 못할 수 있다.

**Lock 인터페이스**

```java
package java.util.concurrent.locks;

public interface Lock {
    void lock();
    void lockInterruptibly() throws InterruptedException;
    boolean tryLock();
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
    void unlock();
    Condition newCondition();
}
```

Lock 인터페이스는 동시성 프로그래밍에서 쓰이는 안전한 임계 영역을 위한 락을 구현하는데 사용

Lock 인터페이스는 다음과 같은 메소드를 제공한다. 

void lock()
- 락을 획득, 다른 스레드가 이미 락을 획득했다면 락이 풀릴 때까지 현재 스레드는 대기(WAITING)
- 메소드는 인터럽트에 응답하지 않음

**주의**

여기서 사용하는 락은 객체 내부에 있는 모니터 락이 아님
Lock 인터페이스와 ReentrantLock이 제공하는 기능
모니터 락과 BLOCKED 상태는 synchronized 에서만 사용됨

void lockInterruptibly() 
- 락 획득을 시도하되 다른 스레드가 인터럽트 할 수 있도록 함
- 다른 스레드가 이미 락을 획득했다면, 현재 스레드는 락을 획득할 때까지 대기, 대기 중에 InterruptedException 이 발생하며 락 획득을 포기

boolean tryLock()
- 락 획득을 시도하고, 즉시 성공 여부를 반환
- 다른 스레드가 이미 락을 획득했다면 false 를 반환, 그렇지 않으면 락을 획득하고 true 반환

boolean tryLock(long time, TimeUnit unit)
- 주어진 시간 동안 락 획득을 시도, 주어진 시간 안에 락을 획득하면 true 반환
- 주어진 시간이 지나도 락을 획득하지 못한 경우 false 반환, 대기 중 인터럽트가 발생하면 InterruptedException 이 발생하며 락 획득을 포기

void unlock()
- 락을 해제, 락을 해제하면 락 획득을 대기 중인 스레드 중 하나가 락을 획득할 수 있게 됨
- 락을 획득한 스레드가 호출해야 하며 그렇지 않으면 IllegalMonitorStateException 이 발생

Condition newCondition()
- Condition 객체를 생성하여 반환, Condition 객체는 락과 결합되어 사용되며, 스레드가 특정 조건을 기다리거나 신호를 받을수 있도록 함
- Object 클래스의 wait, noifty, notifyAll 메서드와 유사한 역할을 함

이 메소드를을 사용하면 고수준의 동기화 기법을 구현할 수 있다.
Lock 인터페이스는 synchronized를 더 많은 유연성을 제공하며, 특히 락을 특정 시간 만큼만 시더하거나 인터럽트 가능한 락을 구현할 때 유용하다.

synchronized의 단점인 무한 대기 문제도 깔끔하게 해결할 수 있다.

## 공정성

Lock 인터페이스가 제공하는 다양하 기능 덕분에 synchronized의 단점인 무한 대기 문제가 해결됨
그러나 공정성 문제가 남아있음

synchronized 단점
- 공정성: 락이 돌아왔을 때 BLOCKED  상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 알 수 없다. 최악의 경우 특정 스레드가 너무 오랜기간 락을 획득하지 못 할수 있음
 
Lock 인터페이스의 대표적인 구현체로 ReentrantLock 존재
해당 클래스는 공정하게 락을 획득할 수 있는 모드 제공

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {
    // 비공정 모드 락
    private final Lock nonFairLock = new ReentrantLock();

    // 공정 모드 락
    private final Lock fairLock = new ReentrantLock(true);

    public void nonFairLockTest() {
        nonFairLock.lock();
        try {
            // 임계 영역
        } finally {
            nonFairLock.unlock();
        }
    }

    public void fairLockTest() {
        fairLock.lock();
        try {
            // 임계 영역
        } finally {
            fairLock.unlock();
        }
    }
}
```

## 비공정 모드(Non-fair mode)

ReentrantLock 의 기본 모드
락을 먼저 요청한 스레드가 락을 먼저 획득한다는 보장이 없음
락을 풀었을 때 대기 중인 스레드 중 아무나 락을 획득
락을 빨리 획득할 수 있지만 특정 스레드가 장기간 락을 획득하지 못할 가능성도 존재

**비공정 모드 특징**
- 성능 우선: 락을 획득하는 속도가 빠름
- 선점 가능: 새로운 스레드가 기존 대기 스레드보다 먼저 락을 획득 할 수 있음
- 기아 현상 가능성: 특정 스레드가 계속해서 락을 획득하지 못하는 경우

## 공정 모드(Fair mode)

생성자에서 true 전달하면 됨
락을 요청한 순서대로 스레드가 락을 획득, 먼저 대기한 스레드가 먼저 락을 획득하게 되어 스레드간 공정성 보장
그로인한 성능 저하될 수 있음

# ReentrantLock - 대기 중단

락을 무한 대기하지 않고, 중간에 빠져나오는 것이 가능

boolean tryLock()
- 락 획득을 시도하고 즉시 성공 여부 반환
- 다른 스레드가 이미 락을 획득했다면 false, 그렇지 않으면 true

boolean tryLock(long time, TimeUnit unit)
- 주어진 시간 동안 락 획득을 시도, 주어진 시간 안에 락 획득 시 true, 락 획득 못할 시 false
- 대기 중 인터럽트가 발생하면 InterruptedException 이 발생하며 락 획득을 포기

tryLock() 예시

