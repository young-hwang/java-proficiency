# 회원 관리 예제1 - 메모리

I/O를 사용해서 회원 데이터를 관리하는 예제

## 요구사항

회원 관리 프로그램 작성
회원의 속성은 아래와 같음

- ID
- Name
- Age

회원을 등록하고, 등록한 회원의 목록을 조회할 수 있어야함

### 프로그램 작동 예시

```
1.회원 등록 | 2.회원 목록 조회 | 3.종료
선택: 1
ID 입력: id1
Name 입력: name1
Age 입력: 20
회원이 성공적으로 등록되었습니다.

1.회원 등록 | 2.회원 목록 조회 | 3.종료
선택: 1
ID 입력: id2
Name 입력: name2
Age 입력: 30
회원이 성공적으로 등록되었습니다.

1.회원 등록 | 2.회원 목록 조회 | 3.종료
선택: 2
회원 목록:
[ID: id1, Name: name1, Age: 20]
[ID: id2, Name: name2, Age: 30]

1.회원 등록 | 2.회원 목록 조회 | 3.종료
선택: 3
프로그램을 종료합니다.
```

> io.application. 폴더
> io.application.MemberConsoleMain 참조

### 문제

잘 작동하지만 데이터를 메모리에 보관하기 때문에 자바를 종료하면 모든 회원 정보가 사라

프로그램을 종료하고 다시 실행해도 회원 데이터가 영구 보관 되어야함

# 회원 관리 예제2 - 파일에 보관

회원 데이터를 영구 보존하려면 파일에 저장하면 됨


> io.application.FileConsoleMain 참조
 
## temp/members-txt.dat 예시

```
id1,member1,20
id2,member2,30
```

## 문제

모든 타입을 문자로 저장하는 문제

```java
public class Member {
private String id;
private String name;
private Integer age;
}
```

- 회원 객체는 `String` , `Integer` 같은 자바의 다양한 타입을 사용함
- 그런데 이런 타입을 무시하고 모든 데이터를 문자로 변경해서 저장하는 부분이 아쉬움
- `age` 의 경우 문자를 숫자로 변경하기 위한 코드도 따로 작성 필요
  - `Integer.valueOf(memberData[2])`

구분자(`DELIMITER` )를 사용하는 문제

- `id` ,`name` ,`age` 각 필드를 구분하기 위해 구분자를 넣어서 저장하고, 또 조회할 때도 구분자를 사용해서 각 필드를 구분해야 함

# 회원 관리 예제3 - DataStream

`DataOutputStream`, `DataInputStream`을 확인

스트림들은 자바의 데이터 타입을 그대로 사용할 수 있음

따라서 자바의 타입을 그대로 사용하면서 파일에 데이터를 저장하고 불러올 수 있으며 구분자가 필요 없음


> io.application.DataConsoleMain 참조

**회원 저장**

```java
dos.writeUTF(member.getId());
dos.writeUTF(member.getName());
dos.writeInt(member.getAge());
```

회원을 저장할 때는 회원 필드의 타입에 맞는 메서드를 호출하면 됨
이전 예제에서는 각 회원을 한 줄 단위로 구분했는데, 여기서는 그런 구분이 필요없음

**실행 결과 - temp/members-data.dat**

파일이 정상 보관되나 문자와 byte가 섞여 있음

## DataStream 원리

`DataStream` 은 어떤 원리로 구분자나 한 줄 라인 없이 데이터를 저장하고 조회할 수 있는 것일까?

### String

```java
dos.writeUTF("id1"); // 저장
dis.readUTF(); // 조회 id1
```

- `readUTF()` 로 문자를 읽어올 때 어떻게 `id1` 이라는 3글자만 정확하게 읽어올 수 있는 것일까?
- `writeUTF()` 은 UTF-8 형식으로 문자를 저장하는데, 저장할 때 2byte를 추가로 사용해서 앞에 글자의 길이를 저장해둔다. (65535 길이까지만 사용 가능)
 
> 3id1(2byte(문자 길이) + 3byte(실제 문자 데이터))

- 따라서 `readUTF()` 로 읽어들일 때 먼저 앞의 2byte로 글자의 길이를 확인하고 해당 길이 만큼 글자를 읽어들임
- 이 경우 2byte를 사용해서 3이라는 문자의 길이를 숫자로 보관하고, 나머지 3byte로 실제 문자 데이터를 보관함

### 기타 타입

```java
dos.writeInt(20);
dis.readInt()
```

- 자바의 `Int(Integer)` 는 4byte를 사용하기 때문에 4byte를 사용해서 파일을 저장하고, 읽을 때도 4byte를 읽어서 복원함

### 저장 예시


```java
dos.writeUTF("id1");
dos.writeUTF("name1");
dos.writeInt(20);
dos.writeUTF("id2");
dos.writeUTF("name2");
dos.writeInt(30);
```

## 저장된 파일 예시

```
3id1(2byte(문자 길이) + 3byte)
5name1(2byte(문자 길이) + 5byte)
20(4byte)
3id2(2byte(문자 길이) + 3byte)
5name2(2byte(문자 길이) + 5byte)
30(4byte)
```

여기서는 이해를 돕기 위해 각 필드를 엔터로 구분하였으나 실제로는 엔터 없이 한 줄로 연결되어 있음

저장된 파일은 실제로는 문자와 byte가 섞여있음


## 정리

`DataStream` 덕분에 자바의 타입도 그대로 사용하고, 구분자도 제거할 수 있었음

추가로 모든 데이터를 문자로 저장할 때 보다 저장 용량도 더 최적화 할 수 있음

예를 들어서 숫자의 1,000,000,000(10억)을 문자로 저장하게 되면 총 10byte가 사용됨

왜냐하면 숫자 1 0 0 0 0 0 0 0 0 0 각각 하나하나를 문자로 저장해야 하기 때문에 ASCII 인코딩을 해도 각각 1byte 가 사용됨

하지만 이것을 자바의 int와 같이 4byte를 사용해서 저장한다면 4byte만 사용하게 됨

여기서는 `writeInt()` 를 사용하면 4byte를 사용해서 저장

물론 이렇게 byte를 직접 저장하면, 문서 파일을 열어서 확인하고 수정하는 것이 어렵다는 단점도 있음

## 문제

`DataStream` 덕분에 회원 데이터를 더 편리하게 저장할 수 있는 것은 맞지만, 회원의 필드 하나하나를 다 조회해서 각 타입에 맞도록 따로따로 저장해야 함

이것은 회원 객체를 저장한다기 보다는 회원 데이터를 하나하나 분류해서 따로 저장한 것

다시 처음으로 돌아와서 회원 객체를 자바 컬렉션에 저장하는 예

```java
dos.writeUTF(member.getId());
dos.writeUTF(member.getName());
dos.writeInt(member.getAge());
```

```java
public class MemoryMemberRepository implements MemberRepository {
    private final List<Member> members = new ArrayList<>();
    
    @Override
    public void add(Member member) {
        members.add(member);
    }
    
    @Override
    public List<Member> findAll() {
        return members;
    }
}
```
    
자바 컬렉션에 회원 객체를 저장할 때는 복잡하게 회원의 필드를 하나하나 꺼내서 저장할 필요가 없음
    
단순하게 회원 객체를 그대로 자바 컬렉션에 보관하면 됨, 조회할 때도 마찬가지

이렇게 편리하게 회원 객체를 저장할 수 있는 방법은 없을까?
