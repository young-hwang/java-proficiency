# Java 학습 경로 가이드

이 문서는 Java Proficiency 프로젝트의 권장 학습 순서를 제공합니다.

## Level 1: 기초 (Beginner)

### 1.1 기본 타입과 문자열
```
me/core/primitive/     # 기본 타입
me/core/charset/       # 문자 인코딩
```

### 1.2 날짜와 시간
```
me/datetime/           # LocalDate, LocalDateTime, ZonedDateTime
```

### 1.3 예외 처리
```
me/io/trywithresources/  # try-with-resources 패턴
```

## Level 2: 중급 (Intermediate)

### 2.1 함수형 프로그래밍 입문
```
me/functional/lambda/            # 람다 표현식
me/functional/functionalinterface/  # Function, Consumer, Predicate, Supplier
me/functional/defaultmethod/     # 인터페이스 기본 메소드
```

### 2.2 Stream API
```
me/functional/stream/  # Stream 생성, 중간/최종 연산
me/functional/optional/  # Optional 활용
```

### 2.3 I/O 기초
```
me/io/stream/          # InputStream, OutputStream
me/io/text/            # Reader, Writer
me/io/buffered/        # BufferedReader, BufferedWriter
me/io/file/            # File 클래스
me/io/reader/          # Reader 계층 구조
```

## Level 3: 고급 (Advanced)

### 3.1 제네릭과 리플렉션
```
me/core/generic/       # 제네릭 타입, 와일드카드
me/core/reflection/    # 클래스/메소드/필드 분석
```

### 3.2 애노테이션
```
me/core/annotation/    # 커스텀 애노테이션, 검증자 패턴
annotation-processing-create-builder/  # 애노테이션 프로세싱
```

### 3.3 직렬화
```
me/core/serializable/  # Serializable, Externalizable
```

### 3.4 NIO
```
me/io/file/nio/        # Channel, Buffer
me/io/file/nio2/       # Path, Files, WatchService
```

### 3.5 네트워킹
```
me/io/net/             # Socket, ServerSocket
me/io/net/autoclosable/  # AutoCloseable 패턴
```

## Level 4: 동시성 (Concurrency)

### 4.1 스레드 기초
```
me/concurrency/thread/begin/     # Thread 생성, 실행
me/concurrency/thread/runnable/  # Runnable 인터페이스
me/concurrency/thread/daemon/    # 데몬 스레드
me/concurrency/thread/join/      # join() 메소드
me/concurrency/thread/interrupt/ # 인터럽트 처리
me/concurrency/thread/yield/     # yield()
```

### 4.2 동기화
```
me/concurrency/thread/sync/          # synchronized 키워드
me/concurrency/thread/volatile1/     # volatile 키워드
me/concurrency/thread/reentrantlock/ # ReentrantLock
me/concurrency/thread/locksupport/   # LockSupport
```

### 4.3 스레드 협력
```
me/concurrency/thread/objectclassmethods/  # wait(), notify(), notifyAll()
me/concurrency/thread/bounded/             # Bounded Buffer
me/concurrency/thread/control/             # 스레드 제어
```

### 4.4 동시성 문제
```
me/concurrency/thread/deadlock/    # 데드락
me/concurrency/thread/cas/         # Compare-And-Swap
me/concurrency/thread/threadlocal/ # ThreadLocal
```

### 4.5 동시성 컬렉션
```
me/concurrency/thread/concurrencycollection/  # ConcurrentHashMap, CopyOnWriteArrayList
```

### 4.6 Executor Framework
```
me/concurrency/executor/         # ExecutorService, ThreadPoolExecutor
me/concurrency/thread/executor/  # Executor 패턴
me/concurrency/future/           # Future, CompletableFuture
```

### 4.7 ForkJoin
```
me/concurrency/forkjoin/           # ForkJoinPool, RecursiveTask
me/concurrency/parallelarraysorting/  # 병렬 배열 정렬
```

## Level 5: 실전 프로젝트

### 5.1 채팅 애플리케이션
```
chat/                  # 멀티스레드 채팅 서버/클라이언트
```
학습 포인트:
- 멀티스레드 서버 구현
- Command 패턴
- 소켓 통신
- Session 관리

### 5.2 HTTP 서버
```
web-server/v1/         # 기본 HTTP 요청 파싱
web-server/v2/         # 응답 처리
web-server/v3/         # 리팩토링
...
web-server/v9/         # 최종 서블릿 패턴
```
학습 포인트:
- HTTP 프로토콜 이해
- 점진적 리팩토링
- 서블릿 패턴 구현

### 5.3 반응형 프로그래밍
```
rx-java/               # RxJava 예제
```
학습 포인트:
- Observable/Flowable
- Operators
- Backpressure

## 권장 학습 기간

| Level | 주제 | 권장 기간 |
|-------|------|----------|
| 1 | 기초 | 1주 |
| 2 | 중급 | 2주 |
| 3 | 고급 | 2주 |
| 4 | 동시성 | 3주 |
| 5 | 실전 | 2주 |

## 각 패키지 학습 방법

1. **README.md 또는 package-info.java 확인**
   - 대부분의 패키지에 설명 문서 포함

2. **Main 클래스 실행**
   - 각 패키지의 `*Main.java` 파일 실행
   - 콘솔 출력 확인

3. **코드 분석**
   - 예제 코드 라인별 분석
   - 주석 참고

4. **변형 실습**
   - 코드 수정 후 결과 확인
   - 테스트 코드 작성

## 참고 자료

- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Baeldung](https://www.baeldung.com/)
- [Java Concurrency in Practice](https://jcip.net/)
