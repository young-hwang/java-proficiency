# 자원 정리 1

> net.autoclosable.ResourceCloseMainV1 참조

- 서로 관련된 자원은 나중에 생성한 자원을 먼저 정리해야함
- 예를들어 `resource1`을 생성하고, `resource1`의 정보를 활용해서 `resource2`를 생성한다면, 닫을 때는 그 반대인 `resource2`를 먼저 닫고, 그 다음에 `resource1`을 닫아야함. 왜냐면 `resource2`의 입장에서는 `resource1`의 정보를 참고하기 때문
- 해당 예에서는 두 자원이 서로 관련이 없기 때문에 생성과 종료 순서가 크게 상관이 없음. `resource1`의 정보를 기반으로 `resource2`를 생성한다고 가정함

**실행 결과**

```java
Exception in thread "main" resource1 call
java.lang.RuntimeException: me.net.autoclosable.CallException: resource2 ex
resource2 callEx
	at me.net.autoclosable.ResourceCloseMainV1.main(ResourceCloseMainV1.java:9)
CallException
Caused by: me.net.autoclosable.CallException: resource2 ex
	at me.net.autoclosable.ResourceV1.callEx(ResourceV1.java:16)
Caused by: me.net.autoclosable.CallException: resource2 ex
```

`callEx()` 에러로 인하여 자원이 정리 되지 않는 문제 발생 

# 자원 정리 2

예외가 발생해도 자원을 정리하도록 수정

> net.autoclosable.ResourceCloseMainV2 참조
 
**null 체크**

`finally` 코드 블록을 사용해서 자원을 닫는 코드가 항상 호출되도록 함

`resource2` 객체를 생성하기 전에 예외가 발생하면 `resource2`는 `null`이 됨. 따라서 `null` 체크를 해야함

`resource1`의 경우에도 `resource1`을 생성하는 중에 예외가 발생한다면 `null` 체크가 필요함

**자원 정리중에 예외가 발생하는 문제**

`finally` 코드 블록은 항상 호출되기 때문에 자원이 정리될 거 같지만, 자원을 정리하는 중에 `finally` 코드 블록 안에서 `resource2.closeEx()`를 호출하면서 예외가 발생

결과적으로 `resource1.closeEx()`는 호출되지 않음

**핵심 예외가 바뀌는 문제**

이 코드에서 발생한 핵심적인 예외는 `CallException` 임

그런데 `finally` 코드 블록에서 자원을 정리하면서 `CloseException` 예외가 추가로 발생하였음

이 경우 `logic()`을 호출한 쪽에서는 핵심 예외인 `CallException`이 아니라 `finally` 블록에서 새로 생성된 `CloseException`을 받게 됨. **핵심 예외가 사라진 것**

**정리하면 다음과 같은 문제가 있음**

- `close()` 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 발생
- `finally` 블럭 안에서 자원을 닫을 때 예외가 발생하면, 핵심 예외가 `finally`에서 발생한 부가 예외로 바뀌어 핵심 예외가 사라짐

# 자원 정리 3

> net.autoclosable.ResourceCloseMainV3 참조

- `finally` 블럭에서 각각의 자원을 닫을 때도, 예외가 발생하면 예외를 잡아서 처리하도록 하였음. 이렇게 하면 자원 정리 시점에 예외가 발생해도, 다음 자원을 닫을 수 있음
- 자원 정리 시점에 발생한 예외를 잡아서 처리했기 때문에, 자원 정리 시점에 발생한 부가 예외가 핵심 예외를 가리지 않음
- 자원 정리 시점에 발생한 예외는 당장 처리할 수 있는 부분이 없음. 이 경우 로그를 남겨서 개발자가 인지할 수 있도록 하면 충분

다음 2가지 문제를 해결

- `close()` 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 발생
- `finally` 블럭 안에서 자원을 닫을 때 예외가 발생하면, 핵심 예외가 `finally`에서 발생한 부가 예외로 바뀌어 버림. 핵심 예외가 사라짐

핵심적인 문제들은 해결되었지만 코드 부분에서 아쉬움 존재

- `resource` 변수를 선언하면서 동시에 할당할 수 없음(`try`, `finally` 코드 블럭과 변수 스코프가 다른 문제)
- `catch` 이후에 `finally` 호출, 자원 정리가 조금 늦어짐
- 개발자가 실수로 `close()`를 호출하지 않을 가능성
- 개발자가 `close()` 호출 순서를 실수, 보통 자원을 생성한 순서와 반대로 닫아야함

이러한 문제를 한번에 해결하는 것이 바로 `try-with-resources` 구문임






