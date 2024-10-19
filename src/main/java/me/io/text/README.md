# 문자 다루기1 - 시작

스트림의 모든 데이터는 1byte 단위를 사용함

따라서 byte가 아닌 문자를 스트림에 직접 전달 할 수 없음

String 문자를 스트림을 통해 파일에 저장하려면 String을 byte로 변환한 다음에 저장함

> ReaderWriterMainV1 참조

**byte[] writeBytes = writeString.getBytes(UTF-8)**

- `String`을 `byte`로 변환할 때는 `String.getBytes(Charset)`을 사용
- 이때 문자를 `byte` 숫자로 변경해야 하기 때문에 반드시 문자 집합(인코딩 셋)을 지정해야함
- `UTF-8`을 사용
- `ABC`를 인코딩하면 `65,66,67`이 됨

이렇게 만든 `byte[]`을 `FileOutputStream`에 `write()`로 전달하면 `65, 66, 67`을 파일에 저장

결과적으로 의도한 ABC 문자를 파일에 저장할 수 있음

**String readString = new String(readBytes.UTF_8)**

- 반대의 경우도 비슷, `String` 객체를 생성할 때, 읽어들인 `byte[]`과 디코딩할 문자 집합을 전달함
- `byte[]`를 `String` 문자로 다시 복원할 수 있음

핵심은 스트림은 `byte`만 사용할 수 있으므로 `String`과 같은 문자는 직접 전달할 수 없음

개발자가 번거롭게 다음과 같은 변환 과정을 직접 호출해야 함

- `String` + 문자집합 -> `byte[]`
- `byte[]` + 문자집합 -> `String`

이런 번거로움을 해결할 방안은?

# 문자 다루기2 - 스트림을 문자로

- `OutputStreamWriter`: 스트림에 byte 대신에 문자를 저장할 수 있게 지원
- `InputStreamReader`: 스트림에 byte 대신에 문자를 읽을 수 있게 지원

> text.ReaderWriterMainV2 참조

- 코드를 보면 앞서 작성한 `BufferedXxx`와 비슷한 것을 확인할 수 있음

**OutputStreamWriter**

- `OutputStreamWriter`는 문자를 입력 받고, 받은 문자를 인코딩해서 `byte[]`로 변환
- `OutputStreamWriter`는 변환한 `byte[]`를 전달할 `OutputStream`과 인코딩 문자 집합에 대한 정보가 필요, 두 정보를 생성자를 통해 전달
  - `new OutputStreamWriter(fos, UTF_8)`
- `osw.write(new String)`를 보면 `String`문자를 직접 전달하는 것을 확인
- `OutputStreamWriter`가 문자 인코딩을 통해 `byte[]`로 변환하고, 변환 결과를 `FileOutputStream`에 전달하는 것을 확인

**InputStreamReader**

- 데이터를 읽을 때는 `int ch = read()`를 제공하는데, 여기서는 문자 하나는 `char`형으로 데이터를 받게됨, 실제 반환 타입은 `int`형 이므로 `char`형으로 캐스팅해서 사용
- 자바의 `char` 형은 파일의 끝인 `-1`을 표현할 수 없으므로 `int`를 반환
- 데이터를 읽을 때 `FileInputStream`에서 `byte[]`을 읽은 것을 확인할 수 있음, `InputStreamReader`는 이렇게 읽은 `byte[]`을 문자인 `char`로 변경해서 반환(`byte`를 문자로 변경할 때도 문자 집합 필요)
  - `new InputStreamReader(fis, UTF-8)`

`OutputStreamWriter`, `InputStreamReader` 덕분에 편리하게 문자를 `byte[]`로 변경하고, 그 반대도 가능함

스트림을 배울 때 `byte` 단위로 데이터를 읽고 쓰는 것을 확인

`write()`의 경우에도 `byte` 단위로 데이터를 읽고 씀

최상위 부모인 `OutputSteram`의 경우 분경 `write()`가 `byte` 단위로 입력하도록 되어 있음

하지만 `OutputStreamWriter`의 `write()`는 `byte` 단위가 아니라 `String`이나 `char`를 사용

어떻게 가능한가?



