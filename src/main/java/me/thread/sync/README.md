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