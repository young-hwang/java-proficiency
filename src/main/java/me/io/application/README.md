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

