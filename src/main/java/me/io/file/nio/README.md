# nio

자바 jdk 1.4에서부터 NIO(new IO)가 추가 되었습니다.
NIO 생긴 이유는 속도 때문 입니다.
NIO는 지금까지 사용한 스트림을 사용하지 않고 대신 Channel과 Buffer를 사용합니다.

## NIO의 Buffer 클래스

Buffer는 java.nio.Buffer 클래스를 확장하여 사용합니다.

ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer, ShortBuffer 등이 존재합니다.

| 리턴 타입 | 메소드        | 설명                      |
|:------|:-----------|:------------------------|
| int   | capacity() | 버퍼에 담을 수 있는 크리 리턴       |
| int   | limit()    | 버퍼에서 읽거나 쓸 수 없는 첫 위치 리턴 |
| int   | position() | 현재 버퍼의 위치 리턴            |

버퍼는 CD 처럼 위치가 있습니다. 버퍼에 데이터를 담거나, 읽는 작업을 수행하면 현재의 위치가 이동합니다.

- 현재의 위치를 나타내는 메소드 position()
- 읽거나 쓸 수 없는 위치를 나타내는 메소드가 limit()
- 버퍼의 크기(capacity)를 나타내는 것이 capacity()

0 <= position <= limit <= 크기(capacity)

### 위치를 변경하는 메소드

| 리턴 타입   | 메소드            | 설명                                                           |
|:--------|:---------------|:-------------------------------------------------------------|
| Buffer  | flip()         | limit 값을 현재 position 으로 지정한 후, position 을 0(가장 앞으로)으로 이동     |
| Buffer  | mark()         | 현재 position 을 mark                                           |
| Buffer  | reset()        | buffer의 position을 mark 한 곳으로 이동                              |
| Buffer  | rewind()       | 현재 buffer 의 position 을 0으로 이동                                |
| int     | remaining()    | limit-position 계산 결과를 리턴                                     |
| boolean | hasRemaining() | position 와 limit 값에 차이가 있을 경우 true 리턴                        |
| Buffer  | clear()        | buffer 를 지우고 현재 position 을 0으로 이동하며, limit 값을 buffer 크기로 변경  |
