## 임계 영역(Critical Section)

여러 프로세스나 스레드가 공유 자원에 접근하는 코드 영역을 의미한다.
공유 자원의 일관성을 유지하기 위해 동시에 여러 프로세나 스레드가 접근하지 못하도록 동기화 메카니즘이 필요하다.

### 임계 영역의 문제

1. 데이터 불일치: 여러 스레드가 동시에 공유 자원에 접근하면 데이터 일관성이 깨진 상태
2. 교착 상태(Deadlock): 두 개 이상의 스레드가 서로가 가지고 있는 자원을 기다리면서 무한히 대기하는 상태
3. 기아 상태(Starvation): 한 스레드가 계속해서 자원에 접근하지 못하고 대기하고 있는 상태

### 임계 영역 보호 방법

임계 영역을 보호하기 위해 여러 동기화 메커니즘을 사용할 수 있다.
가장 일반적인 방법으로 뮤텍스(Mutex), 세마포어(Semaphore), 모니터(Monitor)등이 있다.

#### 뮤텍스(Mutex)

상호 배제를 보장하는 객체
한 번에 하나의 스레드만이 임계 영역에 접근할 수 있도록 한다.
뮤텍스를 획득한 스레드는 임계 영역을 벗어날 때 뮤텍스를 해지 한다.

```java
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private static int counter = 0;
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementCounter();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Counter: " + counter);
    }

    private static void incrementCounter() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
```

```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

var counter = 0
val mutex = Mutex()

fun main() = runBlocking {
    val jobs = List(100) {
        launch {
            repeat(1000) {
                mutex.withLock {
                    counter++
                }
            }
        }
    }
    jobs.forEach { it.join() }
    println("Counter: $counter")
}
```

#### 세마포어(Semaphore)

Semaphore는 한정된 리소스에 대한 접근을 제어하는 동기화 도구로, 지정된 개수의 허용을 관리합니다. 
주로 리소스 풀이 있을 때, 동시 접근 가능한 스레드 수를 제한하는 데 사용됩니다.

장점:
- 특정 리소스에 대한 접근 수를 제어 가능.
- 복수의 허용값을 관리함으로써 동시성을 조절 가능.
 
단점:
- 다른 동기화 메커니즘보다 사용이 복잡할 수 있음.

```java
import java.util.concurrent.Semaphore;

public class Example {
    private final Semaphore semaphore = new Semaphore(3);

    public void accessResource() {
        try {
            semaphore.acquire();
            // 리소스 접근
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}
```

```kotlin
import java.util.concurrent.Semaphore
import kotlin.concurrent.thread

fun main() {
    // 동시에 3개의 스레드만 접근할 수 있는 Semaphore 생성
    val semaphore = Semaphore(3)

    // 5개의 스레드가 동시에 실행되지만, Semaphore로 인해 3개씩 접근 가능
    repeat(5) { i ->
        thread {
            try {
                println("Thread $i: 대기 중")
                semaphore.acquire() // 세마포어 획득, 허용이 남아있지 않으면 대기
                println("Thread $i: 접근 시작")

                // 임계 영역
                Thread.sleep(1000) // 리소스 사용을 시뮬레이션

                println("Thread $i: 접근 종료")
            } finally {
                semaphore.release() // 세마포어 해제
            }
        }
    }
}
```

## 정리

Java는 처음부터 멀티스레드를 고려하고 나온 언어
Java 1.0 부터 'synchronized' 같은 동기화 방법을 프로그래밍 언어의 문법에 포함해서 제공

### synchronized 장점

- 프로그래밍 언어에 문법으로 제공
- 아주 편리한 사용
- 자동 잠금 해제: synchronized 메소드나 블록이 완료되면 자동으로 락을 대기중인 다른 스레드의 잠금이 해제된다. 개발자가 직접 특정 스레드를 깨우도록 관리해야 한다면, 매우 어렵고 번거로울 것이다.

synchronized는 매우 편리하지만, 제공하는 기능이 너무 단순하다는 단점이 있다.
시간이 점점 지나면서 멀티스레드가 더 중요해 지고 점점 더 복잡한 동시성 개발 방법들이 필요해졌다.

### synchronized 단점

- 무한 대기: 'BLOCKED' 상태의 스레드는 락이 풀릴 때 까지 무한 대기한다.
  - 특정 시간 까지만 대기하는 타임 아웃 X
  - 중간에 인터럽트 X
- 공정성: 락이 돌아왔을 때 'BLOCKED' 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 할 수 없다. 최악의 경우 특정 스레드가 너무 오랜 기간 락을 획득하지 못할 수 있다.

'synchronized'의 가장 치명적인 단점은 락을 얻기 위해 BLOCKED 상태가 되면 락을 얻을 때까지 무한 대기한다는 점이다.
e.g. 웹 애플리케이션에 요청을 했을 때 화면에 요청 중만 뜨고 응답을 못 받는 것이 아니라 차라리 너무 오랜 시간이 지나면 다음에 다시 시도해 달라는 식의 응답을 하는 것이 더 나은 선택일 수 있다.

결국 더 유연하고, 더 세밀한 제어가 가능한 방법들이 필요하게 되었다. 이런 문제를 해결하기 위해 자바 1.5부터 'java.util.concurrent' 라는 동시성 문제 해결을 위한 패키지가 추가 되었다.

참고로 단순하고 편리하게 사용하기에는 'synchronized' 가 좋으므로, 목적에 부합한다면 'synchronized'를 사용하면 된다.

