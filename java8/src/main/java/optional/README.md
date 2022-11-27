# Optional 

Optional은 java.util 패키지에 속하며 Functional 언어인 Haskell과 Scala에서 제공하는 기능을 따 온 것이다.
즉, 객체를 편리하게 처리하기 위해서 만든 클래스라고 보면 된다.

```java
public final class Optional<T> extends Object
```

Optional 클래스는 하나의 깡통이라고 생각하면 된다. 깡통에 물건을 넣을 수도 있고, 아무 물건이 없을 수도 있다.

```java
new Optional(); // Optional은 갠체를 생성하지 않음
```

Optional 클래스를 리턴하는 empty(), of(), ofNullable() 메소드가 존재한다.
이 메소드들을 이용하여 객체를 생성하는 방법을 보자.

```java
private void craeteOptionalObjects() {
    Optional<String> emptyString = Optional.empty();    
    String common = null;
    Optional<String> nullableString = Optional.ofNullable(common); // null이 추가될 수 있는 상황이라면 ofNullable 사용
    common = "common";
    Optional<String> commonString = Optional.of(common); // 반드시 데이터가 들어갈 수 있는 상황에는 of 사용
}
```

값을 꺼내는 방법은 get(), orElse(), orElseGet(), orElseThrow() 가 데이터를 리턴하는 메소드들 이다.

```java
private void getOptionalDate(Optional<String> Data) throws Exception {
    
}
```

