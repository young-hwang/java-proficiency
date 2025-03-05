package datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekOfMonthTest {

    public static void main(String[] args) {
        // 특정 날짜 설정 (예: 2023년 11월 15일)
        LocalDate date = LocalDate.of(2025, 3, 2);
        System.out.println(DayOfWeek.THURSDAY.getValue());
        System.out.println(date.getDayOfMonth());
        LocalDate preMonthLastDate = date.plusDays(-1 * (date.getDayOfMonth()));

        // 현재 날짜를 사용하려면 아래 코드 사용
        // LocalDate date = LocalDate.now();

        // 주의 시작일을 설정 (예: 월요일부터 시작)
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);

        // 해당 월의 몇 번째 주인지 계산
        int weekOfMonth = date.get(weekFields.weekOfMonth());
        int yesterdayWeekOfMonth = preMonthLastDate.get(weekFields.weekOfMonth());

        System.out.println("날짜: " + date);
        System.out.println("해당 월의 " + weekOfMonth + "번째 주입니다.");

        System.out.println("날짜: " + preMonthLastDate);
        System.out.println("해당 월의 " + yesterdayWeekOfMonth + "번째 주입니다.");

        // 추가 정보 출력
//        System.out.println("월: " + date.getMonthValue());
//        System.out.println("일: " + date.getDayOfMonth());
//        System.out.println("요일: " + date.getDayOfWeek());
    }
}
