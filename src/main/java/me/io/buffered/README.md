# 파일 입출력과 성능 최적화 1 - 하나씩 쓰기

파일을 효과적으로 더 빨리 읽고 쓸수 있는 방법 알아보기

> buffered.BufferedConst 참조

- `FILE_NAME` : `temp/buffered.dat` 라는 파일을 만들 예정
- `FILE_SIZE` : 파일의 크기는 10MB
- `BUFFER_SIZE` 는 뒤에서 설명

## 예제1 - 쓰기

가장 단순한 `FileOutputStream` 의 `write()` 를 사용해서 1byte씩 파일을 저장

그리고 10MB 파일을 만드는데 걸리는 시간을 확인

> buffered.CreateFileV1 참조

`fos.write(1)` : 파일의 내용은 중요하지 않기 때문에 여기서는 단순히 1이라는 값을 반복하며 계속 저장, 한 번 호출에 1byte가 만들어짐

이 메서드를 약 1000만번(10 * 1024 * 1024) 호출하면 10MB의 파일이 만들어짐

**주의!**

실행시 시스템에 따라서 1분 이상 걸리는 경우도 있으니 결과가 나올 때 까지 참고 기다림

**실행 결과**

```shell
File created: temp/buffered.dat
File size: 10MB
Time taken: 14092ms
```
실행을 하면 결과를 보는데 상당히 오랜 시간이 걸림

## 예제1 - 읽기

> buffered.ReadFileV1 참조

- `fis.read()`를 사용하여 1byte씩 데이터 읽기
- 파일의 크기가 10MB 이므로 `fis.read()` 메소드는 약 1000만번 호출

**실행 결과**

```shell
File name: temp/buffered.dat
File size: 10MB
Time take: 5367ms
```

- 실행을 하면 결과를 보는데 상당히 오랜 시간이 걸림

**정리**

10MB 파일 하나를 쓰는데 14초, 읽는데 5초라는 매우 오랜 시간이 걸림

오래 걸린 이유는 자바에서 1byte씩 디스크에 더이터를 전달하기 때문

디스크는 1byte의 데이터를 받아서 1byte의 데이터를 씀

이 과정을 무려 1000만 번 반복하는 것으로 더 자세히 설명하면 다음 2가지 이유로 느려짐

- `write()` 나 `read()` 를 호출할 때마다 OS의 시스템 콜을 통해 파일을 읽거나 쓰는 명령어를 전달함 이러한 시스템 콜은 상대적으로 무거운 작업임
- HDD, SDD 같은 장치들도 하나의 데이터를 읽고 쓸 때마다 필요한 시간이 존재 HDD의 경우 더욱 느린데, 물리적으로 디스크의 회전이 필요
- 이러한 무거운 작업을 무려 1000만 번 반복

물론 반대로 데이터를 읽어 들일 때도 마찬가지

**참고**

이렇게 자바에서 운영 체제를 통해 디스크에 1byte씩 전달하면, 운영 체제나 하드웨어 레벨에서 여러가지 최적화가 발생

따라서 실제로 디스크에 1byte씩 계속 쓰는 것은 아님(그렇다면 훨씬 더 느렸을 것)

하지만, 자바에서 1바이트씩 write()나 read()를 호출할 때마다 운영 체제로의 시스템 콜이 발생하고, 이 시스템 콜 자체가 상당한 오버헤드를 유발

운영 체제와 하드웨어가 어느 정도 최적화를 제공하더라도, 자주 발생하는 시스템 콜로 인한 성능 저하는 피할 수 없음

결국 자바에서 read(), write() 호출 횟수를 줄여서 시스템 콜 횟수도 줄여야 함

# 파일 입출력과 성능 최적화2 - 버퍼 활용

1byte씩 데이터를 하나씩 전달하는 것이 아니라 byte[]을 통해 배열에 담아 한번에 여러 byte를 전달

## 예제2 - 쓰기

> buffered.CreateFileV2 참조

데이터를 먼저 `buffer` 라는 `byte[]` 에 담아둠

이렇게 데이터를 모아서 전달하거나 모아서 전달받는 용도로 사용하는 것을 버퍼라 함

여기서는 `BUFFER_SIZE` 만큼 데이터를 모아서 `write()` 를 호출

예를 들어 `BUFFER_SIZE` 가 10이라면 10만큼 모이면 `wirte()` 를 호출해서 `10byte` 를 한 번에 스트림에 전달

**실행 결과**

```shell
File created: temp/buffered.dat
File size: 10MB
Time taken: 14ms
```

실행 결과의 `BUFFER_SIZE` 는 8192(8KB)

실행 결과를 보면 이전 예제의 쓰기 결과인 14초 보다 약 1000배 정도 빠른 것을 확인할 수 있음

실행 시간은 시스템 환경에 따라 달라짐

### 버퍼의 크기에 따른 쓰기 성능

`BUFFER_SIZE` 에 따른 쓰기 성능

- **1**: 14368ms
- **2**: 7474ms
- **3**: 4829ms
- **10**: 1692ms
- **100**: 180ms
- **1000**: 28ms
- **2000**: 23ms
- **4000**: 16ms
- **8000**: 13ms
- **80000**: 12ms

많은 데이터를 한 번에 전달하면 성능을 최적화 할 수 있음

이렇게 되면 시스템 콜도 줄어들고, HDD, SDD 같은 장치들의 작동 횟수도 줄어듬

예를 들어 버퍼의 크기를 1 2로 변경하면 시스템 콜 횟수는 절반으로 줄어듬

그런데 버퍼의 크기가 커진다고 해서 속도가 계속 줄어들지는 않음

왜냐하면 디스크나 파일 시스템에서 데이터를 읽고 쓰는 기본 단위가 보통 4KB 또는 8KB이기 때문임

- 4KB (**4096** byte)
- 8KB (**8192** byte)

결국 버퍼에 많은 데이터를 담아서 보내도 디스크나 파일 시스템에서 해당 단위로 나누어 저장하기 때문에 효율에는 한계가 있음

따라서 버퍼의 크기는 보통 4KB, 8KB 정도로 잡는 것이 효율적

## 예제2 - 읽기

> buffered.ReadFileV2 참조

**실행 결과**

```
File name: temp/buffered.dat
File size: 10MB
Time taken: 5ms
```

- 실행 결과의 `BUFFER_SIZE` 는 8192(8KB)
- 읽기의 경우에도 버퍼를 사용하면 약 1000배 정도의 성능 향상을 확인할 수 있음
    - 실행 시간은 시스템 환경에 따라 달라짐

버퍼를 사용하면 큰 성능 향상이 있음

하지만 직접 버퍼를 만들고 관리해야 하는 번거로운 단점 존재

# 파일 입출력과 성능 최적화3 - Buffered 스트림 쓰기

`BufferedOutputStream`은 버퍼 기능을 내부에서 대신 처리해줌

따라서 단순한 코드를 유지하면서 버퍼를 사용하는 이점도 함께 누림

## 예제3 - 쓰기

> buffered.CreateFileV3 참조

- `BufferedOutputStream` 은 내부에서 단순히 버퍼 기능만 제공한다. 따라서 반드시 대상 `OutputStream` 이 있어야 함
- 여기서는 `FileOutputStream` 객체를 생성자에 전달함
- 추가로 사용할 버퍼의 크기도 함께 전달할 수 있음
- 코드를 보면 버퍼를 위한 `byte[]` 을 직접 다루지 않고, 마치 예제1과 같이 단순하게 코드를 작성할 수 있음
 
**실행 결과**

```
File created: temp/buffered.dat
File size: 10MB
Time taken: 144ms
```

- 성능도 예제1의 14초 보다 140배 빠른 0.1초에 처리됨
- 참고로 성능이 예제2보다는 다소 떨어지는데 그 이유는? 

**BufferedOutputStream 분석**

- `BufferOutputStream`은 `OutputStream` 상속 받음
- `OutputStream`과 같은 기능을 그대로 사용가능

**BufferedOutputStream 실행 순서**

- `BufferedOutputStream`은 내부에 `byte[] buf`라는 버퍼를 가짐
- 여기서 버퍼의 크기는 3이라고 가정
- `BufferedOutputStream`에 `write(byte)` 를 통해 `byte` 하나를 전달하면 `byte[] buf` 에 보관됨(참고로 실제로는 `write(int)` 타입)

- `write(byte)` 를 2번 호출했지만 아직 버퍼가 가득차지 않음

- `write(byte)` 를 3번 호출하면 버퍼가 가득 참
- 버퍼가 가득차면 `FileOutputStream` 에 있는 `write(byte[])` 메서드를 호출
  - 참고로 `BufferedOutputStream` 의 생성자에서 `FileOutputStream` , `fos` 를 전달해둠
- `FileOutputStream` 의 `write(byte[])` 을 호출하면, 전달된 모든 `byte[]` 을 시스템 콜로 OS에 전달함

- 버퍼의 데이터를 모두 전달했기 때문에 버퍼의 내용을 비움
- 이후에 `write(byte)` 가 호출되면 다시 버퍼를 채우는 식으로 반복

**flush()**

버퍼가 다 차지 않아도 버퍼에 남아있는 데이터를 전달하려면 `flush()` 라는 메서드를 호출

- 버퍼에 2개의 데이터가 남아있음

- flush()` 호출
- 버퍼에 남은 데이터를 전달함

- 데이터를 전달하고 버퍼를 비움

**close()**

만약 버퍼에 데이터가 남아있는 상태로 `close()` 를 호출하면 어떻게 될까?

- BufferedOutputStream` 을 `close()`로 닫으면 먼저 내부에서 `flush()` 를 호출, 따라서 버퍼에 남아 있는 데이터를 모두 전달하고 비움
- 따라서 `close()` 를 호출해도 남은 데이터를 안전하게 저장할 수 있음

- 버퍼가 비워지고 나면 `close()` 로 `BufferedOutputStream` 의 자원을 정리
- 그리고 나서 다음 연결된 스트림의 `close()` 를 호출,  여기서는 `FileOutputStream` 의 자원이 정리
- 여기서 핵심은 `close()` 를 호출하면 `close()` 가 연쇄적으로 호출된다는 점, 따라서 마지막에 연결한 `BufferedOutputStream` 만 닫아주면 됨

**주의! - 반드시 마지막에 연결한 스트림을 닫아야함**

- 만약 `BufferedOutputStream` 을 닫지 않고, `FileOutputStream` 만 직접 닫으면 어떻게 될까?
- 이 경우 `BufferdOutputStream` 의 `flush()` 도 호출되지 않고, 자원도 정리되지 않음, 따라서 남은 `byte` 가 버퍼에 남아있게 되고, 파일에 저장되지 않는 심각한 문제가 발생
- 따라서 지금과 같이 스트림을 연결해서 사용하는 경우에는 마지막에 연결한 스트림을 반드시 닫아주어야 함
  - 마지막에 연결한 스트림만 닫아주면 연쇄적으로 `close()` 가 호출

## 기본 스트림, 보조 스트림

- `FileOutputStream`과 같이 단독으로 사용할 수 있는 스트림을 기본 스트림이라함
- `BufferedOutputStream`과 같이 단독으로 사용할 수 없고, 보조 기능을 제공하는 스트림을 보조 스트림이라함

`BufferedOutputStream`은 `FileOutputStream`에 버퍼라는 보조기능을 제공
`BufferedOutputStream`의 생성자는 반드시 `FileOutputStream`같은 대상 `OutputStream`이 있어야함

```java
public BufferedOutputStream(OutputStream out) { ... }
public BufferedOutputStream(OutputStream out, int size) { ... }
```

- `BufferedOutputStream`은 버퍼라는 보조 기능을 제공하며 그것을 누구에게 제공할지 대상을 반드시 전달해야함

**정리**

- `BufferedOutputStream`은 버퍼 기능을 제공하는 보조 스트림
- `BufferedOutputStream`도 `OutputStream`의 자식이기 때문에 `OutputStream`의 기능을 그대로 사용할 수 있음
  - 대부분의 기능은 재정의 됨, `write()`의 경우 먼저 버퍼에 쌓이도록 재정의 됨
- 버퍼의 크기만큼 데이터를 모아서 전달하기 때문에 빠른 속도로 데이터를 처리

# 파일 입출력과 성능 최적화4 - Buffered 스트림 읽기

> buffered.ReadFileV3 참조

## 실행 결과

```shell
File name: temp/buffered.dat
File size: 10MB
Time take: 146ms
```

- 예제1 이 약 5초 정도 걸렸으나 약 50배 정도 빨라진걸 확인
- 예제2 보다 느림

## BufferedInputStream 분석**

- `BufferedInputStream`은 `InputStream` 상속 
- `InputStream`과 같은 기능을 그대로 사용

**BufferedInputStream 실행 순서**

- `read()` 호출
- 버퍼의 크기는 3이라고 가정

- `read()`는 1byte 만 조회
- `BufferedInputStream`은 먼저 버퍼를 확인, 버퍼에 데이터가 없으므로 데이터를 불러옴
- `BufferedInputStream`은 `FileInputStream`에 `read(byte[])`를 사용해서 버퍼의 크기인 3byte의 데이터를 불러옴
- 불러온 데이터를 버퍼에 보관

- 버퍼에 있는 데이터를 하나 반환

**정리**

- `BufferedInputStream`은 버퍼의 크기만큼 데이터를 미리 읽어서 버퍼에 보관
- 따라서 `read()`를 통해 1byte 데이터를 조회해서 성능이 최적화 됨

## 버퍼를 직접 다루는 것보다 BufferedXxx의 성능이 떨어지는 이유

- 예제1 쓰기: 14000ms(14초)
- 예제2 쓰기: 14ms(버퍼 직접 다룸)
- 예제3 쓰기: 102ms(BufferedXxx)

예제2는 버퍼를 직접 다루는 것이고, 예제3은 `BufferedXxx`라는 클래스가 대신 버퍼를 처리함

예제2와 예제3은 비슷한 성능이 나와야하는데 왜 예제2가 더 빠른가?

**BufferedOutputStream.write()**

```java
@Override
public void write(int b) throws IOException {
    if (lock != null) {
        lock.lock();
        try {
            implWrite(b);
        } finally {
            lock.unlock();
        }
    } else {
        synchronized (this) {
            implWrite(b);
        }
    }
}
```

- `BufferedOutputStream`을 포함한 `BufferedXxx`클래스는 모두 동기화 처리가 되어 있음
- 예제는 1byte 씩 저장해서 총 10MB를 저장해야 하는데 이럴 경우 `write()`를 약 1000만번 호출 해야함
- 결과적으로 락을 걸고 푸는 코드도 1000만번 호출된다는 의미

**BufferedXxx 클래서의 특징**

`BufferedXxx` 클래스는 자바 초창기에 만들어진 클래스로 처음부터 멀티 스레드를 고려해서 만든 클래스임

따라서 멀티 스레드에 안전하지만 락을 푸는 동기화 코드로 인해 약간 성능저하가 될 수 있음

하지만 싱글 스레드 상황에서는 동기화 락이 필요하지 않기 때문에 직접 버퍼와 다룰 때와 비교해서 성능이 떨어짐

일반적인 상황이라면 이 정도 성능은 크게 문제가 되지는 않기 때문에 싱글 스레드여도 `BufferedXxx`를 사용하면 충분

매우 큰 데이터를 다루어야 하고, 성능 최적화가 중요하다면 예제2와 같이 직접 버퍼를 다루는 방법도 고려

동기화 락이 없는 `BufferedXxx` 클래스는 없음

꼭 필요한 상황이라면 `BufferedXxx`를 참고해서 동기화 락 코드를 제거한 클래스를 직접 만들어 사용

# 파일 입출력과 성능 최적화5 - 한번에 쓰기

## 예제4 - 쓰기

파일의 크기가 크지 않다면 간단하게 한번에 쓰고 읽는 것도 좋은 방법임

성능은 가장 빠르지만 결과적으로 메모리를 한번에 많이 사용하기 때문에 파일의 크기가 작아야함

> buffered/CreateFileV4 참조

**실행 결과**

```java
File created: temp/buffered.dat
File size: 10MB
Time take: 8ms
```

- 실행 시간은 8kb의 버퍼를 직접 사용한 예제2와 오차범위로 거의 비슷함
- 디스크나 파일 시스템에서 데이터를 읽고 쓰는 기본 단위가 보통 4kb 또는 8kb이기 때문, 한번에 쓴다고 무작정 빠른건 아님

## 예제4 - 읽기

> buffered/ReadFileV4 참조

```java
File name: temp/buffered.dat
File size: 10MB
Time take: 10ms
```

- 실행 시간은 8kb의 버퍼를 사용한 예제2와 오차 범위 정도로 비슷함
- `readAllBytes()`는 자바 구현에 따라 다르지만 보통 4kb, 8kb, 16kb 단위로 데이터를 읽어들임





