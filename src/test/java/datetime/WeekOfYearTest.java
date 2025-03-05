package datetime;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekOfYearTest {
        public static void main(String[] args) {
        // 현재 날짜를 가져옵니다.
        LocalDate currentDate = LocalDate.now();

        // 기본 지역(Locale) 설정을 따르거나, 특정 Locale을 지정할 수 있습니다.
        WeekFields weekFields = WeekFields.of(Locale.KOREA);

        // 현재 날짜의 연 주차를 계산합니다.
        int weekOfYear = currentDate.get(weekFields.weekOfYear());

        System.out.println("현재 연도의 주차: " + weekOfYear);
    }
}
