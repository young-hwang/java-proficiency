# CAS 락 구현1

CAS는 단순한 연산 뿐만 아니라, 락을 구현하는데 사용 가능

`synchronized`, `Lock(ReentrantLock)` 없이 CAS를 활용해서 락을 구현할 것

```java
public class SpinLockBad {
    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");
        while(true) {
            if (!lock) { // 1. 락 사용 여부 확인
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true;    // 2. 락의 값 변경
                break;
            } else {
                // 락을 획득할 때 까지 스핀 대기(바쁜 대기)
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }
    
    public void unlock() {
        lock = false;    // 락의 값 변경, 원자적 연산
        log("락 반납 완료");
    }
}
```

실행 결과 기대와는 다르게 `Thread-1`, `Thread-2` 둘다 동시에 락을 획득하고 동시에 수행

어디가 문제인가?

다음 두 부분이 원자적이 않음

- 락 사용 여부 확인(1번)
- 락의 값 변경(2번)

다른 해결 방안은 두 코드를 하나로 묶어서 원자적으로 처리하는 것

CAS 연산을 사용하면 두 연산을 하나로 묶어서 하나의 원자적인 연산으로 처리할 수 있음

락의 사용 여부를 확인하고, 그 값이 기대하는 값과 같다면 변경 = CAS 연산에 딱 들어 맞음

> 참고로 락을 반납하는 연산은 하나의 원자적 연산


