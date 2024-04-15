# Stream

자바의 스트림은 "뭔가 연속된 정보"를 처리하는데 사용한다.
지금까지 사용한 연속된 정보는 기본적으로 배열이 있다. 
또다른 하나는 컬렉션이다.

컬렉션은 스트림을 사용할 수 있지만 배열은 스트림을 사용할 수 없다.
배열을 컬렉션 리스트로 변환하는 방법은 여러가지가 존재한다.

```java
Integer[] values = { 1, 3, 5};
List<Integer> list = new ArrayList<>(Arrays.asList(values));

List<Integer> list = Arrays.stream(values).collect(Collectors.toList());
```

# 스트림의 구조

```java
//            스트림생성          중개연산           중단연산
List list = Arrays.stream(values).filter(x -> x > 10).count();
```

- 스트림 생성: 컬렉션의 목록을 스트림 객체로 변환한다. 스트림 객체는 java.util.stream 패키지의 stream을 의미한다.
- 중개 연산: 생성된 스트림 객체를 사용하여 중개 연산 부분에서 처리한다. 하지만 이부분에서는 아무런 결과를 리턴하지 못한다.(intermediate operator)
- 종단 연산: 마지막으로 중개 연산에서 작업된 내용을 바탕으로 결과를 리턴한다.(terminal operation)

## 중개 연산자

- filter
- map / mapToInt / mpaToLong / mapToDouble
- flatMap / flatMapToInt / fatMapToLong / flatMapToDouble
- distinct
- sorted
- peek
- limit

## 종단 연산자

- forEach / forEachOrdered
- toArray
- reduce
- collect
- min / max / count
- anyMatch / allMatch / noneMatch
- findFirst / anyArray


# 병렬 데이터 처리와 성능

새로운 스트림 인터페이스를 이용해서 데이터 컬렉션을 선언형으로 제어하는 방법을 제공한다.
또한 외부 반복을 내부 반복으로 바꾸면 네이티브 자바 라이브러리가 스트림 요소의 처리를 제어할 수 있음을 확인 했다.
무엇보다도 컴퓨터의 멀티코어를 활용해서 파이프라인 연산을 실행할 수 있다는 점이 가중 중요한 특성이다.

자바 7이 등장하기 전에는 데이터 컬렉을 병렬로 처리하기 어려웠다.
우선 데이터를 서드 파트로 분리하여야 한다.
그리고 분할된 서브 파트를 각각의 스레드로 할당한다.
