# datetime classes

이전의 Date나 SimpleDateFormatter라는 클래스를 사용하여 날짜를 처리해 왔다.
이들 클래스의 문제점은 다음과 같다.

- thread safe 하지 않다
- immutable (불변) 객체가 아니다
- API 구성이 복잡하다
- 1900년부터 시작하도록 되어 있으며 달은 1부터, 일은 0부터 시작한다(e.g. 1900년 1월 1일 => 1900, 1, 0)


<table>
  <tr>
    <th>내용</th>
    <th>버전</th>
    <th>패키지</th>
    <th>설명</th>
  </tr>
  <tr>
    <td rowspan="2">값 유지</td>
    <td>예전 버전</td>
    <td>java.util.Date<br>java.util.Calendar</td>
    <td>Date 클래스는 날짜 계산을 할 수 없다. Calendar 클래스는 불변 객체가 아니므로 연산시 객체 자체가 변경되었다</td>
  </tr>
  <tr>
    <td>Java 8</td>
    <td>java.time.ZonedDatetime<br>java.time.LocalDate 등 </td>
    <td>ZonedDateTime과 LocalDate 등은 불변 객체이다.<br>모든 클래스가 연산용의 메소드를 갖고 있으며, 연산시 새로운 불변 객체를 돌려 준다. 쓰레드에 안전하다.</td>
  </tr>
  <tr>
    <td rowspan="2">변경</td>
    <td>예전 버전</td>
    <td>java.text.SimpleDateFormat</td>
    <td>Thread Safe 하지 않고 느리다</td>
  </tr>
  <tr>
    <td>Java 8</td>
    <td>java.time.format.DateTimeFormatter</td>
    <td>Thread Safe 하며 빠르다</td>
  </tr>
  <tr>
    <td rowspan="2">시간대</td>
    <td>예전 버전</td>
    <td>java.util.TimeZone</td>
    <td>"Asia/Seoul" 이나 "+09:00" 같은 정보를 가진다</td>
  </tr>
  <tr>
    <td>Java 8</td>
    <td>java.time.ZoneId<br>java.time.ZoneOffset</td>
    <td>ZoneId는 "Asia/Seoul" 정보를 갖고 있고 ZoneOffset은 "+09:00" 라는 정보를 가진다</td>
  </tr>
  <tr>
    <td rowspan="3">속성 관련</td>
    <td>예전 버전</td>
    <td>java.util.Calendar</td>
    <td>Calendar.YEAR, Calendar.MONTH, Calendar.DATE 등 모두 정수(int) 이다</td>
  </tr>
  <tr>
    <td rowspan="2">Java 8</td>
    <td>java.time.temporal.ChronoField<br>(java.file.temporal.TmporalField)</td>
    <td>ChronoField.YEAR, ChronoField.MONTH_OF_YEAR, ChronoField.DAY_OF_MONTH 등 enum 타입이다</td>
  </tr>
  <tr>
    <td>java.time.temporal.ChronoUnit<br>(java.file.temporal.TmporalUnit)</td>
    <td>ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS 등 enum 타입 이다</td>
  </tr>
</table>

추가로 시간을 나타내는 Local, Offset, Zoned로 3가지 종류가 존재한다.

- Local: 시간대가 없는 시간 e.g. 1시를 예로 들면 어느 지역의 1시 인지 구분되지 않느다.
- Offset: UTC(그리니치 시간대)와의 오프셋(차이)을 가지는 시간, 한국은 "+09:00"
- Zoned: 시간대("한국시간"과 같은 정보)를 갖는 시간, 한국은 "Asia/Seoul"