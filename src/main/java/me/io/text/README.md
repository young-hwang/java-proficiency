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





