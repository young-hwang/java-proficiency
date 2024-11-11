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
 
 
