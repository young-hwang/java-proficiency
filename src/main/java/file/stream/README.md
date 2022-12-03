# InputStream & OutputStream

## InputStream

abstract class로 선언 되어 있다. 따라서 InputStream을 제대로 사용하려면, 이 클래스를 확장한 자식 클래스를 살펴봐야한다.

```java
import java.io.Closeable;

public abstract class InputStream extends Object implements Closeable
```

### Closeable 

어떤 리소스를 열었던 간에 이 인터페이스를 구현하면 해당 리소스는 close() 메소드를 이용하여 닫으라는 의미이다.
"리소스"는 파일 or 네트워크 연결 등 스트림을 통해 작업할 수 있는 모든 것이다.

close() 메소드는 반드시 호출해야 한다.

### InputStream 확장 클래스

AudioInputStream, ByteArrayInputStream, FileInputStream, FilterInputStream, InputStream,
ObjectInputStream, PipedInputStream, SequenceInputStream, StringBufferInputStream

| 클래스               | 설명                                                   |
|:------------------|:-----------------------------------------------------|
| FileInputStream   | 파일을 읽는 데 사용한다. 이미지와 같이 바이트 코드로 된 데이터를 읽을 때 사용한다.     |
| FilterInputStream | 다른 입력 스트림을 포괄하며, 단순히 InputStream 클래스가 Override되어 있다. |
| ObjectInputStream | ObjectOutputStream으로 저장한 데이터를 읽는데 사용한다.              |

FilterInputStream 클래스의 생성자는 protected로 선언되어 있기에 상속받은 클래스에서만 객체를 생성할 수 있다.
FilterInputStream 클래스를 확장한 클래스는 다음과 같다.

BufferedInputStream, CheckedInputStream, CipherInputStream, DataInputStream, DeflaterInputStream, 
DigestInputStream, InflaterInputStream, LineNumberInputStream, ProgressMonitorInputStream, PushbackInputStream


## OutputStream

InputStream과 마찬가지로 abstract 클래스로 선언되어 있다.
Closeable과 Flushable이라는 두 개의 인터페이스로 구현하였다.

```java
import java.io.Closeable;

public abstract class OutputStream extends Object implements Closeable, Fushable
```

### Flushable

flush() 라는 하나의 메소드만 선언 되어있다.
일반적으로 어떤 리소스에 데이터를 쓸 때, 매번 쓰기 작업을 "요청할 때마다 저장"하면 효율이 안좋아 진다.
따라서, 대부분 저장을 할 때 버퍼(buffer)를 갖고 데이터를 차곡차곡 쌓아 두었다가, 어느 정도 차게 되면 한번에 쓰는 것이 좋다.
그러한 버퍼를 사용할 때 flush() 메소드는 "현재 버퍼에 있는 내용을 기다리지 말고 모조건 저장해"라고 시키는 것이다.

InputStream과 마찬가지로 close() 메소드를 꼭 호출하여 열었던 리소스를 닫아 주어야 한다.

