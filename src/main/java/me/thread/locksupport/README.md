## LockSupport 기능

LockSupport 는 스레드를 WAITING 상태로 변경한다.
WAITING 상태는 누가 깨워주기 전까지는 계속 대기한다.
그리고 CPU 실행 스케줄링에 들어가지 않는다.

LockSupport의 대표적인 기능은 다음과 같다.
- park(): 스레드를 WAITING 상태로 변경한다.
  - 스레드를 대기 상태로 둔다. 참고로 park의 뜻이 "주차하다", "두다"라는 뜻이다.
- parkNanos(nanos) : 스레드를 나노초 동안만 TIMED_WAITING 상태로 변경한다.
  - 지정한 나노초가 지나면 TIME_WAITING 상태에서 빠져나오고 RUNNABLE 상태로 변경된다.
- unpark(thread) : WAITING 상태의 대상 스레드를 RUNNABLE 상태로 변경한다.

LockSupport는 특정 스레드를 WAITING 상태로, 또 RUNNABLE 상태로 변경할 수 있다.
그런데 대기 상태로 바꾸는 LockSupport.park() 는 매개변수가 없는데 실행 가능 상태로 바꾸는 LockSupport.unpark(thread1) 는 왜 특정 스레드를 지정하는 매개변수가 있을까?
왜냐하면 실행 중인 스레드는 LockSupport.park() 를 호출해서 스스로 대기 상태에 빠질수 있지만, 대기 상태의 스레드는 자신의 코드를 실행할 수 없기 때문이다.
따라서 외부 스레드의 도움을 받아야 깨어날 수 있다.

### 인터럽트 사용

WAITING 상태에서 INTERRUPT 를 사용하게 되면 RUNNABLE 상태로 깨어나게 된다.


## LockSupport2

스레드를 특정 시간 동안만 대기하는 parkNanos(nanos)를 호출한다.
- parkNanos(nanos): 스레드를 나노초 동안만 TIME_WAITING 상태로 변경한다. 지정한 나노초가 지나면 TIMED_WAITING 상태에서 빠져나와서 RUNNABLE 상태로 변경된다.
- 참고로 밀리초 동안만 대기하는 메소드는 없다. parkUtil(밀리초) 라는 메소드가 있는데, 이 메소드는 특정 에포크(Epoch) 시간에 맞추어 깨어나는 메소드이다. 정확한 미래의 에프코 시점을 지정해야 한다.

## BLOCKED vs WAITING

- WAITING 상태에 특정 시간까지만 대기하는 기능이 포함된 것이 TIMED_WAITING
- 둘을 묶어 WAITING 상태라 표현

### 인터럽트

- BLOCKED 상태는 인터럽트가 걸려도 대기 상태를 빠져나오지 못함, 여전히 BLOCKED 상태
- WAITING, TIMED_WAITING 상태는 인터럽트가 걸리면 대기 상태를 빠져나옴, RUNNABLE 상태

### 용도

- BLOCKED 상태는 자바의 synchronized에서 락을 획득하기 위해 대기할 때 사용
- WAITING, TIMED_WAITING 상태는 스레드가 특정 조건이나 시간 동안 대기할 때 사용
- WAITING 상태는 다양한 상황에서 사용, e.g. Thread.join(), LockSupport.park(), Object.wait()와 같은 메소드 호출 시 WAITING
- TIMED_WAITING  상태는 Thread.sleep(ms), Object.wait(long timeout), Thread.join(long mills), LockSupport.parkNanos(ns) 등과 같은 시간 제한이 있는 대기 메소드를 호출할 때 발생

대기(WAITING) 상태와 시간 대기 상태(TIMED_WAITING) 는 서로 짝이 있다.
- Thread.join(), Thread.join(long millis)
- Thread.park(), Thread.parkNanos(ns)
- Object.wait(), Object.wait(long timeout)

BLOCKED, WAITING, TIMED_WAITING  상태 모두 스레드가 대기하며, 실행 스케줄링에 들어가지 않기 때문에 CPU 입장에서 보면 실행하지 않는 비슷한 상태
- BLOCKED 상태는 synchronized 에서만 사용하는 특별한 대기 상태라고 이해
- WAITING, TIMED_WAITING 상태는 범용적으로 활용할 수 있는 대기 상태



