# File

Java 7 부터는 NIO2가 등장하면서 java.nio.file 패키지에 있는 Files라는 클래스에서 File클래스에 있는 메소드를 대체하여 제공한다.
File 클래스는 객체를 생성하여 데이터를 처리하는 데 반하여, Files 클래스는 모든 메소드가 static으로 선언되어 있기에 별도의 객체를 생성할 필요가 없다.

File 클래스는 파일 및 경로 정보를 통제하기 위한 클래스이다.

- 존재여부
- 파일인지 경로인진
- 읽거나 쓰거나 실행할 수 있는지
- 언제 수정되었는지
- 이름 바꾸기
- 삭제하기
- 생성하기
- 전체 경로 확인하기

경로일 경우
- 파일의 목록 가져오기
- 경로를 생성하기
- 경로를 삭제하기

## 생성자 

| 생성자                               | 설명                                                              |
|:----------------------------------|:----------------------------------------------------------------|
| File(File parent, String child)   | 이미 생성되어 있는 File 객체(parent)와 그 경로의 하위 경로 이름으로 새로운 File 객체를 생성한다. |
| File(String pathname)             | 지정한 경로 이름으로 File 객체를 생성한다.                                      |
| File(String parent, String child) | 상위 경로(parent)와 하위 경로(child)로 File 객체를 생성한다.                     |
| File(URI uri)                     | URI에 따른 File 객체를 생성한다.                                          |

## mkdir() vs mkdirs()

/Users/java 폴더만 존재 할 경우에 /Users/java/path1/path2라는 경로를 만들려고 하면, mkdirs() 메소드를 사용하면 path1과 path2 디렉토리를 만든다.
mkdir() 메소드는 디렉토리를 하위 디렉토리 하나만 만든다.

## List 메소드

| 리턴 타입         | 메소드 이름 및 매개 변수                   | 설명                                                       |
|:--------------|:---------------------------------|:---------------------------------------------------------|
| static File[] | listRoots()                      | OS에서 사용중인 파일 시스템의 루트 디렉토리 목록을 File 배열로 리턴 한다.            |
| String[]      | list()                           | 현재 디렉토리의 하위에 있는 목록을 String 배열로 리턴 한다.                    |
| String[]      | list(FilenameFilter filter)      | 현재 디렉토리의 하위에 있는 목록 중 filter 조건에 맞는 목록을 String 배열로 리턴 한다. |
| File[]        | listFiles()                      | 현재 디렉토리의 하위에 있는 목록을 File 배열로 리턴 한다.                      |
| File[]        | listFiles(FileFilter filter)     | 현재 디렉토리의 하위에 있는 목록 중 filter 조건에 맞는 목록을 File 배열로 리턴 한다.   |
| File[]        | listFiles(FilenameFilter filter) | 현재 디렉토리의 하위에 있는 목록 중 filter 조건에 맞는 목록을 File 배열로 리턴 한다. |

## FileFilter & FileNameFilter

인터페이스로 조건을 주려면 이 인터페이스를 구현하여야 한다.

### FileFilter
| 리턴 타입   | 메소드 이름 및 매개 변수        | 설명                                |
|:--------|:----------------------|:----------------------------------|
| boolean | accept(File pathname) | 매개 변수로 넘어온 File 객체가 조건에 맞는지 확인한다. |

| 리턴 타입   | 메소드 이름 및 매개 변수                     | 설명                                                      |
|:--------|:-----------------------------------|:--------------------------------------------------------|
| boolean | accept(File pathname, String name) | 매개 변수로 넘어온 디렉토리(dir)에 있는 경로나 파일 이름(name)이 조건에 맞는지 확인한다. |


# Files

## File, Files의 역사

자바 1.0에서 `File` 클래스가 등장, 이후 자바 1.7에서 `File`클래스를 대체할 `Files`와 `Path`가 등장

## Files의 특징

- 성능과 편의성이 모두 개선
- `File`은 과거의 호환을 유지하기 위해 남겨둔 기능
- 이제는 `Files` 사용을 먼저 고려
- File과 관련된 수 많은 유틸리티 클래스 존재, `File` 클래스와 `File`과 관련된 스트림(`FileInputStream`, `FileWriter`)의 사용을 고려하기 전 `Files` 기능을 먼저 확인
- 기능이 너무 많으므로 필요할 때 검색하여 사용

> io.NewFilesMain 참조

# 경로 표시

## File 경로 표시

> io.OldFilePathMain 참조

**절대 경로(Absolute path)**: 절대 경로는 경로의 처음부터 내가 입력한 모든 경로를 다 표현

**정규 경로(Canonical path)**: 경로의 계산이 모두 끝난 경로이다. 정규 경로는 하나만 존재

예제에서 `..` 은 바로 위의 상위 디렉토리를 뜻함, 이런 경로의 계산을 모두 처리하면 하나의 경로만 남음

예를 들어 절대 경로는 다음 2가지 경로가 모두 가능하지만

`/Users/yh/study/inflearn/java/java-adv2`

`/Users/yh/study/inflearn/java/java-adv2/temp/..`

정규 경로는 다음 하나만 가능

`/Users/yh/study/inflearn/java/java-adv2`

**file.listFiles()**

현재 경로에 있는 모든 파일 또는 디렉토리를 반환
파일이면 F, 디렉토리면 D로 표현

> io.NewFilePathMain 참조

# Files로 문자 파일 읽기

문자로된 파일을 읽고 쓸 때 과거에는 `FileReader`, `FileWriter` 같은 복잡한 스트림 클래스를 사용했음

거기에 모든 문자를 읽으려면 반복문을 사용하여 파일의 끝까지 읽어야하는 과정 추가 필요

한 줄 단위로 파일을 읽으려면 `BufferedReader`와 같은 스트림 클래스를 추가해야 함

`Files`는 이런 문제를 깔끔하게 해결

## Files - 모든 문자 읽기

> io.file.ReadTextFileV1 참조

- `Files.writeString()` : 파일에 쓰기
- `Files.readString()` : 파일에서 모든 문자 읽기

`Files` 를 사용하면 아주 쉽게 파일에 문자를 쓰고 읽을 수 있음

## Files - 라인 단위 읽기

> io.file.ReadTextFileV2 참조
 
### Files.readAllLines(path)

- 파일을 한 번에 다 읽고, 라인 단위로 `List` 에 나누어 저장하고 반환

### Files.lines(path)

- 파일을 한 줄 단위로 나누어 읽고, 메모리 사용량을 줄이고 싶다면 이 기능을 사용
- 다만 이 기능을 제대로 이해하려면 람다와 스트림을 알아야 함
- 파일이 아주 크다면 한 번에 모든 파일을 다 메모리에 올리는 것 보다, 파일을 부분 부분 나누어 메모리에 올리는 것이 더 나은 선택일 수 있음

# 파일 복사 최적화

## 파일 복사 예제1

> io.file.FileCopyMainV1 참조


### 실행 결과
```
Time taken: 109ms
```

- `FileInputStream` 에서 `readAllBytes` 를 통해 한 번에 모든 데이터를 읽고 `write(bytes)` 를 통해 한번에 모든 데이터를 저장
- 파일(copy.dat) 자바(byte) 파일(copy_new.dat)의 과정을 거침
- 자바가 `copy.dat` 파일의 데이터를 자바 프로세스가 사용하는 메모리에 불러온 후 메모리에 있는 데이터를 `copy_new.dat` 에 전달

## 파일 복사 예제2

> io.file.FileCopyMainV2 참조
 
### 실행 결과

```
Time taken: 70ms
```

- `InputStream` 에는 `transferTo()` 라는 특별한 메서드가 존재(자바 9) 해당 메서드는 `InputStream` 에서 읽은 데이터를 바로 `OutputStream` 으로 출력
- `transferTo()` 는 성능 최적화가 되어 있기 때문에, 앞의 예제와 비슷하거나 조금 더 빠름(상황에 따라 조금 더 느릴 수도 있음)
- 참고로 디스크는 실행시 시간의 편차가 심하다는 점을 알아두자.
- 파일(copy.dat) 자바(byte) 파일(copy_new.dat)의 과정을 거침
- `transferTo()` 덕분에 매우 편리하게 `InputStream` 의 내용을 `OutputStream`으로 전달할 수 있음

## 파일 복사 예제3

> io.file.FileCopyMainV2 참조

### 실행 결과

```
Time taken: 37ms
```

### Files.copy()

-파일(copy.dat) -> 자바(byte) -> 파일(copy_new.dat)

이 과정들은 파일의 데이터를 자바로 불러오고 또 자바에서 읽은 데이터를 다시 파일에 전달해야 함
 
`Files.copy()` 는 자바에 파일 데이터를 불러오지 않고, 운영체제의 파일 복사 기능을 사용, 따라서 다음과 같이 중간 과정이 생략됨

- 파일(copy.dat) -> 파일(copy_new.dat) 
 
따라서 가장 빠름, 파일을 다루어야 할 일이 있다면 항상 `Files` 의 기능을 먼저 확인

해당 기능은 파일에서 파일을 복사할 때만 유용, 만약 파일의 정보를 읽어서 처리해야 하거나, 스트림을 통해 네트워크에 전달해야 한다면 앞서 설명한 스트림을 직접 사용해야함


