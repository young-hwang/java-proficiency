# File

Java 7 부터는 NIO2가 등장하면서 java.nio.file 패키지에 있는 Files라는 클래스에서 File클래스에 있는 메소드를 대체하여 제공한다.
File 클래스는 객체를 생성하여 데이터를 처리하는 데 반하여, Files 클래스는 모든 메소드가 static으로 선언되어 있기에 별로의 객체를 생성할 필요가 없다.

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
