# Fork/Join

Fork/Join 이란 어떤 계산 작업을 할 때 "여러 개로 나누어 계산한 후 결과를 모으는 작업"을 의미 합니다.
Fork 는 여러 개로 나누는 것을 말하고, Join은 쓰레드에 대해서 살펴볼 때 여러 쓰레드에서 수행한 작업을 합칠때 사용한 Join() 메소드를 떠올리면 됩니다.

Java 7에 추가된 Fork/Join은 단순하게 작업을 쪼개고 그 결과를 받는 단순한 작업만을 포함하지 않습니다.
여기에는 Work stealing이라는 개념이 포함되어 있습니다.

어떤 작업이 대기하고 있는 큐가 있다고 할 때, 한쪽만 끝이 아니라 양쪽이 끝으로 인식되는 큐(Dequeue)를 활용합니다.
여러 개의 Dequeue 에 작업이 나뉘어져 어떤 일이 진행될 때 만약 하나의 Dequeue가 매우 바쁘고 다른 Dequeue 는 바쁘지 않을 때가 있을 것입니다.
이와 같은 상황에서 할 일이 없는 Dequeue는 바쁜 Dequeue 에 대기하고 있는 일을 가져가서 대신 해주는 것 입니다.

이러한 Work steal 이라는 개념이 Fork/Join 이라는 것에는 기본적으로 포함되어 있습니다.
따라서 CPU가 많이 있는 장비에서 계산 위주의 작업을 매우 빠르게 해야 할 필요가 있을 때 매우 유용합니다.

Fork/Join 작업의 기본 수행 개념은 다음과 같습니다.

```java
if (작업의 단위가 충분히 작은 경우) {
    해당 작업을 수행    
} else {
    작업을 반으로 쪼개어 두 개의 작업으로 나눔
    두 작업을 동시에 실행시키고 두 작업이 끝 날때까지 결과를 기다림    
}
```

이러한 식으로 Fork/Join 이 실행되기 때문에 보통 이 연산은 회귀적으로(Recursive 하게) 수행 될 때 많이 사용합니다.
Fork/Join 기능은 java.util.concurrent 패키지의 RecursiveAction 과 RecursiveTask 라는 abstract class를 사용합니다.

| RecursiveAction                                            | RecursiveTask                                                  |
|:-----------------------------------------------------------|:---------------------------------------------------------------|
| public abstract class RecursiveAction extends ForkJoinTask | public abstract class RecursiveTask<V> extends ForkJoinTask<V> |

| 구분      | RecursiveTask | RecursiveAction |
|:--------|:--|:--|
| Generic | O | X |
| 결과 리턴   | O | X |

RecursiveTask 클래스는 V 라는 타입으로 결과를 리턴합니다.
두 클래스 모드 ForkJoinTask 라는 abstract class를 확장합니다.

```java
import java.io.Serializable;

public abstract class ForkJoinTask<V> extends Object implements Future<V>, Serializable
```

ForkJoinTask 클래스는 Future 라는 인터페이스를 구현 하였습니다.
Future라는 인터페이스는 Java 5 부터 추가된 인터페이스로 비동기적인(asynchronous) 요청을 하고 응답을 기다릴 때 사용됩니다.

RecursiveTask 나 RecursiveAction 확장하여 개발 시 두 클래스 모두 compute() 라는 메소드가 있고 이 메소드가 재귀 호출되며 연산을 수행합니다.

|                    | Fork/Join 클라이언트 밖에서 호출 | Fork/Join 클라이언트 내에서 호출 |
|:-------------------|:-----------------------|:-----------------------|
| 비 동기적 호출 수행시       | execute(ForkJoinTask)  | ForkJoinTask.fork()    | 
| 호출 후 결과 대기         | invoke(ForkJoinTask)   | ForkJoinTask.invoke()  | 
| 호출 후 Future 객체 수신  | submit(ForkJoinTask)   | ForkJoinTask.fork()    | 

## Fork/Join 구현 간단 예제

```java
long total = 0;

for(long loop = from; loop <= to; loop++) { 
    total += loop;
}
```
전체의 합계를 구하는 간단한 로직을 Fork/Join으로 구현합니다.

쓰레드 객체를 만들지도, 쓰레드 작업을 할당하지도 않았습니다. 
JVM 에서 알아서 수행하며, 결과만 확인하면 됩니다.
worker의 개수는 CPU 개수만큼 증가합니다.

## Parallel Array Sorting

배열을 정렬하는 가장 간편한 방법은 java.util 패키지의 Arrays 클래스를 사용하는 것입니다.

Java 8에서는 `parallelSort()` 메소드가 추가되었으며, 내부적으로 Fork-Join 프레임워크를 사용합니다.

| 메소드 | 설명 |
|:------|:-----|
| Arrays.sort() | 단일 스레드 정렬 (TimSort) |
| Arrays.parallelSort() | Fork-Join 기반 병렬 정렬 |

**특징:**
- 약 5,000개 이상의 요소부터 parallelSort()가 더 빠름
- CPU 코어 수에 비례하여 성능 향상
- 내부적으로 배열을 분할하여 병렬 처리 후 병합

