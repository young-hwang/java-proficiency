# Java Proficiency

Java 언어의 핵심 개념부터 고급 기능까지 학습하기 위한 예제 프로젝트입니다.

## 프로젝트 구조

```
java-proficiency/
├── src/main/java/me/           # 개념 학습 코드
│   ├── core/                   # Java 핵심 (annotation, reflection, generic 등)
│   ├── functional/             # 함수형 프로그래밍 (lambda, stream, optional 등)
│   ├── concurrency/            # 동시성 (thread, executor, forkjoin 등)
│   ├── io/                     # I/O & 네트워킹
│   ├── datetime/               # 날짜/시간
│   └── util/                   # 유틸리티
├── src/test/java/              # 테스트 코드
└── projects/                   # 실전 프로젝트
    ├── web-server/             # HTTP 서버 구현 (v1-v9)
    ├── chat/                   # 멀티스레드 채팅 애플리케이션
    ├── annotation-processing-create-builder/  # 애노테이션 프로세싱
    └── rx-java/                # RxJava 반응형 프로그래밍
```

## 학습 주제

### Core (Java 핵심)

| 패키지 | 설명 |
|--------|------|
| `annotation` | 커스텀 애노테이션, 검증자 패턴 |
| `reflection` | 리플렉션 API, 필드/메소드 분석 |
| `generic` | 제네릭 타입 |
| `record` | Record 타입 (Java 14+) |
| `serializable` | 직렬화 (Serializable, Externalizable) |
| `charset` | 문자 인코딩/디코딩 |
| `primitive` | 기본 타입 |

### Functional (함수형 프로그래밍)

| 패키지 | 설명 |
|--------|------|
| `lambda` | 람다 표현식, 메소드 참조 |
| `stream` | Stream API |
| `optional` | Optional 클래스 |
| `functionalinterface` | Function, Consumer, Predicate 등 |
| `defaultmethod` | 인터페이스 기본 메소드 |

### Concurrency (동시성)

| 패키지 | 설명 |
|--------|------|
| `thread` | 멀티스레딩 (22개 세부 주제) |
| `executor` | ExecutorService |
| `forkjoin` | ForkJoin 프레임워크 |
| `future` | CompletableFuture, Callable |

#### Thread 세부 주제
- `begin` - 스레드 기초
- `sync` - 동기화
- `volatile1` - volatile 키워드
- `reentrantlock` - ReentrantLock
- `concurrencycollection` - 동시성 컬렉션
- `cas` - Compare-And-Swap
- `threadlocal` - ThreadLocal
- `deadlock` - 데드락
- 등 22개 패키지

### I/O & Networking

| 패키지 | 설명 |
|--------|------|
| `io` | I/O 스트림 (50+ 파일) |
| `io.file` | 파일 처리 |
| `io.nio` | NIO (Channels, Buffers) |
| `io.nio2` | NIO.2 (Paths, Files, WatchService) |
| `net` | 소켓 프로그래밍 |
| `reader` | Reader 클래스 계층 |
| `trywithresources` | try-with-resources |

### Date & Time

| 패키지 | 설명 |
|--------|------|
| `datetime` | Java 8 Time API |

### Utility

| 패키지 | 설명 |
|--------|------|
| `object` | 객체 참조, WeakHashMap |
| `datastructure` | 자료구조 |
| `regular` | 정규표현식 |
| `runtime` | Runtime 클래스 |
| `util` | 유틸리티 |

## 실전 프로젝트 (projects/)

### chat
실제 동작하는 멀티스레드 채팅 애플리케이션
- Client/Server 구조
- Command 패턴 적용
- Session 관리

### web-server
점진적으로 발전하는 HTTP 서버 구현 (v1 ~ v9)
- 리팩토링 과정 학습
- 서블릿 패턴 구현
- 회원 관리 서비스

### annotation-processing-create-builder
애노테이션 프로세싱으로 빌더 패턴 자동 생성
- SPI 구현
- 컴파일 타임 코드 생성

### rx-java
RxJava 반응형 프로그래밍
- Observable, Flowable
- Backpressure 처리

## 빌드 및 실행

### 요구사항
- Java 25+
- Gradle 8.x

### 빌드
```bash
./gradlew build
```

### 테스트
```bash
./gradlew test
```

## 학습 가이드

권장 학습 순서는 [docs/LEARNING_PATH.md](docs/LEARNING_PATH.md)를 참조하세요.

## 통계

| 항목 | 수치 |
|------|------|
| Java 파일 | 447개 |
| 메인 모듈 패키지 | 28개 |
| 서브 모듈 | 6개 |
| 테스트 파일 | 10개 |
