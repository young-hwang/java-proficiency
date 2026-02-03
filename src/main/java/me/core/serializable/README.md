# Serializable

java.io 패키지에 있는 인터페이스입니다.
선언된 변수나 메소드가 없습니다.

생성한 객체를 파일로 저장하거나 읽을 때 또는 다른 서버로 보내거나 받을 때 Srializable을 반드시 구현해야 합니다.
JVM에서 해당 객체는 저장하거나 다른 서버로 전송할 수 있도록 해줍니다.

인터페이스 구현 후 serialVersionUID 라는 값을 지정해 주는 것을 권장합니다.
별도 지정 없을 시 자바 소스 컴파일 시 자동 생성 됩니다.

```java
static final long serialVersionUID = 1L;
```

반드시 static final long 으로 선언해야 하며, 변수명도 serialVersionUID 여야 한다.
serialVersionUID는 아무런 값이나 지정해주면 됩니다.

이 값은 해당 객체의 버전을 명시하는 데 사용합니다.
각 서버가 쉽게 해당 객체가 같은지 다른지를 확인할 수 있도록 하기 위해서는 serialVersionUID로 관리해주어야 합니다.

ID가 다르면 다른 클래스로 인식하고 같은 UID라고 할지라도 변수의 개수나 타입등이 다르면 다른 클래스로 인식합니다.

Serializable을 구현해 놓은 상태에서 serialVersionUID를 명시적으로 지정하면 변수가 변경되더라도 예외는 발생하지 않습니다.
따라서 객체의 내용이 바뀌었는데도 에러가 발생하지 않으면 운영 상황에서 데이터가 꼬일 수 있기에 serialVersoinUID를 변경하는 습관이 필요합니다.(IDE의 자동 serialVersionUID 생성)

## transient

transient 예약어가 선언된 변수는 serializable의 대상에서 제외 됩니다.



