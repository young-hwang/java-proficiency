# nio2

NIO 는 New I/O의 약자이며 JDK 1.4 부터 제공 되었습니다.
데이터를 보다 빠르게 읽고 쓰는 데 주안점을 두고 각종 API를 제공하고 있습니다.
하지만 이를 이해하기는 쉽지 않고 제대로 구현하려면 많은 고려를 해야 합니다.
어떻게 보면 이 NIO와 이름은 비슷하지만 크게 관련이 없는 NIO2라는 것이 java 7 부터 제공 됩니다.

java 에서 파일을 다루기 위해서 제공한 java.io 패키지의 File class 에는 미흡한 부분이 많았습니다.
그래서 이를 보완하는 내용이 매우 많이 포함 되었습니다.

java 에서 지금까지 다루지 않은 '파일 속성'을 다룰수 있으며, 심볼릭 링크(symbolic link)까지 처리할 수 있는 기능을 제공합니다.
또한 어떤 파일이 변경되었는지를 쉽게 확인할 수 있는 WatcherService 라는 클래스도 제공합니다.

## File

java.io 패키지의 File 클래스는 이름만 File 이고 실제로는 경로에 대한 정보를 담을 수 있어 사용할 때 많은 혼동이 있습니다.
게다가 확장성을 고려하지 않고 만들어졌기에 많은 단점이 존재합니다.

- 심볼릭 링크, 속성, 파일의 권한 등에 대한 기능이 없음
- 파일을 삭제하는 delete() 메소드는 실패시 아무런 예외를 발생시키지 않고, boolean 타입의 결과만 제공
- 파일이 변경되었는지 확인하는 방법은 lastModified() 라는 메소드에서 제공해주는 long 타입의 결과로 이전 시간과 비교하는 방법 뿐이였습니다.

## NIO2 classes

java.nio.file 패키지에 위치하고 있습니다.

| 클래스        | 설명                                                                                                                |
|:-----------|:------------------------------------------------------------------------------------------------------------------|
| Paths      | get() 메소드를 사용하면 Path Interface 객체를 얻는다. 파일과 경로에 대한 정보를 갖는다.                                                       |
| Files      | 기존 File class 의 단점을 보완한 class 이다. Path 객체를 사용하여 파일을 통제하는데 사용된다.                                                   |
| FileSystem | 파일 시스템에 대한 정보를 처리하는데 필요한 메소드를 제공한다. getDefault() 메소드를 사용하면 기본 파일 시스템에 대한 정보를 갖는 FileSystem 이라는 인터페이스 객체를 얻을 수 있다. |
| FileStore  | 파일을 저장하는 디바이스, 파티션, 볼륨 등에 대한 정보를 확인하는데 필요한 메소드를 제공한다.                                                             |

## Paths

Paths class 는 생성자가 없고 static 한 get() 메소드를 통해 Path 객체를 얻을 수 있습니다.

| 리턴 타입       | 메소드                               |
|:------------|:----------------------------------|
| static Path | get(String first, String... more) |
| static Path | get(URI uri)                      |

## Files

Files 클래스는 기존의 File 클래스에서 제공되는 기능보다 많은 기능을 제공하는 클래스입니다.

| 기능             | 관련 메소드                                                                                                                            |
|:---------------|:----------------------------------------------------------------------------------------------------------------------------------|
| 복사 및 이동        | copy(), move()                                                                                                                    |
| 파일, 디렉토리 등 생성  | createDirectories(), createDirectory(), createFile(), createLink(), createSymbolicLink(), createTempDirectory(), createTempFile() |
| 삭제             | delete(), deleteIfExists()                                                                                                        |
| 읽기와 쓰기         | readAllBytes(), newBufferedWriter(), newByteChannel(), newDirectoryStream(), newInputStream(), newOutputStream()                  |
| Stream 및 객체 생성 | newBufferedReader(), newBufferedWriter(), newByteChannel(), newDirectoryStream(), newInputStream(), newOutputStream()             |
| 각종 확인          | get으로 시작하는 메소드와 is로 시작하는 메소드들로 파일의 상태를 확인                                                                                         |

## WatchService

기존에 파일 변경되었는지 확인하는 방법은 File 클래서에서 제공하는 lastModified() 메소드를 사용하여 파일의 시간을 가져와서 기존에 저장된 시간괴 비교하는 방법이 었습니다.
이는 내부적으로 호출되는 연계된 메소드가 많아 성능에 양향이 적지 않았습니다.
이를 보완하기 위해 WatchService 라는 인터페이스를 제공합니다. 
해당 디렉토르에 파일을 생성, 수정, 삭제 시 WatchService를 고용한 주인에게 연락을 해 줍니다.
