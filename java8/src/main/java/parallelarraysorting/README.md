# Parallel Array Sorting

배열을 정렬하는 가장 간편한 방법은 java.util 패키지의 Arrays 클래스를 사용하는 것이다.
Arrays 클래스에는 다음과 같은 static method 들이 있다.

- binarySearch(): 배열 내에서의 검색
- copyOf(): 배열의 복제
- equals(): 배열의 비교
- fill(): 배열 채우기
- hashCode(): 배열의 해시코드 제공
- sort(): 정렬
- toString(): 배열 내용을 출력

java 8에서는 parallelSort() 라는 정렬 메소드가 제공되며 Fork-Join 프레임웍이 내부적으로 사용된다.
5,000개 정도부터 parallelSort() 성능이 더 빨리진다.

