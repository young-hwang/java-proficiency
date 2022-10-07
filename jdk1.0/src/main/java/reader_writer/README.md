# READER & WRITER

Stream(InputStream, OutputStream)은 byte를 다루기 위한 것이며, Reader와 Writer는 char 기반의 문자열을 처리하기 위한 클래스이다.

## READER

```java
import java.io.Closeable;

public abstract class Reader extends Object implements Readable, Closeable
```

InputStream이나 OutputStream 처럼 Reader 클래스도 abstract로 선언되어 있다.
abstract 메소드는 close(), read() 메소드이다.

Reader를 확장한 주요 클래스는 다음과 같다.

BufferedReader, CharArrayReader, FilterReader, InputStreamReader, PipeReader, StringReader


## WRITER

```java
import java.io.Closeable;
import java.io.Flushable;

public abstract class Writer extends Object implements Appendable, Closeable, Flushable
```

Appendable 인터페이스는 Java 5 부터 추가 되었으며, 각종 문자열을 추가하기 위해서 선언되었다.
append() 메소드는 CharSequence 매개 변수를 가진다. CharSequence는 인터페이스이며 이를 구현한 대표적인 클래스는 String, StringBuilder, StringBuffer가 있다.
따라서 대부분의 문자열을 다 받아서 처리한다는 말이다.

Writer 클래스는 JDK 1.1 부터 제공 되었는데, 그 때는 write() 밖에 없었다. 
언뜻 보기에 append()와 write() 메소드가 비슷해 보일 것이다.
문자열이 String 타입이라면 write() 메소드를 사용해도 상관 없지만 StringBuilder나 StringBuffer로 문자열을 만든다면 append() 메소드를 사용하는 것이 편하다.



