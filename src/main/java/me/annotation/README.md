## 메타 애노테이션

### @Retention

애노테이션의 생존 기간을 지정

- RetentionPolicy.SOURCE: 소스 코드에만 남김, 컴파일 시점에 제거
- RetentionPolicy.CLASS: 컴파일 후 class 파일까지는 남아있지만 자비 실행 시점에 제거(기본 값)
- RetentionPolicy.RUNTIME: 자바 실행 중에도 남아 있음. 대부분 이설정을 사용

### @Documented

자바 API 문서를 만들 때 해당 애노테이션이 함께 포함되는지 지점

### @Inherited

자식 클래스가 애노테이션을 상속 받을 수 있음

## 애노테이션과 상속

모든 애노테이션은 `java.lang.annotation.Annotation` 인터페이스를 상속 받는다.

```java
package java.lang.annotation;

public interface Annotation {
    boolean equals(Object obj);
    int hashCode();
    String toString();
    Class<? extends Annotation> annotationType();
}
```

`java.lang.annotation.Annotation` 인터페이스는 개발자가 직접 구현하거나 확장할 수 있는 것이 아니라, 자바 언어 자체에서 애노테이션을 위한 기반으로 사용

다음과 같은 메서드를 제공함

- `boolean equals(Object obj)`: 두 애노테이션의 동일성을 비교
- `int hashCode()`: 애노테이션의 해시코드를 반환
- `String toString()`: 애노테이션의 문자열 표현을 반환
- `Class<? extends Annotation> annotationType()`: 애노테이션 타입을 반환

모든 애노테이션은 기본적으로 `Annotation` 인터페이스를 확장하며 이로 인해 애노테이션은 특별한 형태의 인터페이스로 간주됨

하지만 자바에서 애너테이션을 정의할 때, 개발자가 명시적으로 `Annotation` 인터페이스를 상속하거나 구현할 필요는 없음

애노테이션을 정의할 때, 개발자가 명시적으로 `Annotation` 인터페이스를 상속하거나 구현할 필요는 없음

애노테이션을 `@inteface` 키워드를 통해 정의하면 자바 컴파일거러가 자동으로 `Annotation` 인터페이스를 확장하도록 처리해줌

**애노테이션 정의**

```java
public @interface MyCustomAnnotation {}
```

**자바가 자동으로 처리**

```java
public @interface MyCustomAnnotation extends java.lang.annotation.Annotation {}
```

### @Inherited

애노테이션을 정의할 때 `@Inherited` 메타 애노테이션을 붙이면, 애노테이션을 적용한 클래스의 자식도 해당 애노테이션을 부여 받을 수 있음

**주의할 점은 이 기능은 클래스 상속에서만 작동하고, 인터페이스의 구현체에는 작동하지 않음**

> inherited.InheritedMain 참조

### @Inherited가 클래스 상속에만 적용된 이유

1. 클래스 상속과 인터페이스 구현의 차이

- 클래스 상속은 자식 클래스가 부모 클래스의 속성과 메서드를 상속 받는 개념
- 자식 클래스는 부목 클래스의 특성을 이어 받으므로 부모 클래스에 정의된 애노테이션을 자식 크래스가 자동으로 상속받을 수 있는 논리적 기반이 존재
- 인터페이스는 메서드의 시그니처만을 정의할 뿐, 상태나 행위를 가지지 않기 때문에, 인터페이스의 구현체가 애노테이션을 상속한다는 개념이 잘 맞지 않음

2. 인터페이스와 다중 구현, 다이아몬드 문제

인터페이스는 다중 구현이 가능.

만약 인터페이스의 애노테이션을 구현 클래서에서 상속하게 되며 여러 인터페이스의 애노테이션간의 충돌이나 모호한 상황이 발생할 수 있음



