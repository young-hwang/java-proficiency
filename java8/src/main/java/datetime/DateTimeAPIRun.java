package datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeAPIRun {
    public static void main(String[] args) {
        // 현재 시간을 기계식으로 표현
        Instant now = Instant.now();
        System.out.println(now); // utc, gmt

        // 현재의 시스템 타임존 적용
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime1 =  now.atZone(zoneId);
        System.out.println(zonedDateTime1);

        System.out.println("===============");

        // LocalDateTime 사람이 이해하기 편한
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        LocalDateTime birthday = LocalDateTime.of(1984, Month.FEBRUARY, 10, 0, 0, 0);
        System.out.println(birthday);

        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(now2);

        System.out.println("===============");

        // period : 사람이 판달하기 좋은 날짜를 가지고 비교
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate nextYearBirthday = LocalDate.of(2023, Month.FEBRUARY, 10);
        System.out.println(nextYearBirthday);

        Period between = Period.between(today, nextYearBirthday);
        System.out.println(between.getYears() + " " + between.getMonths() + " " + between.getDays());

        Period until = today.until(nextYearBirthday);
        System.out.println(until.getYears() + " " + until.getMonths() + " " + until.getDays());

        System.out.println("===============");

        // duration : 기계가 판단하는 시간을 비교
        Instant now3 = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between1 = Duration.between(now3, plus);
        System.out.println(between1.getSeconds());

        System.out.println("===============");

        // formatting : 문자열 출력 시 사용
        LocalDateTime now4 = LocalDateTime.now();

        // 출력형 변환
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(now4.format(dateFormatter));

        // parsing
        LocalDate parse = LocalDate.parse("1984-02-10", dateFormatter);
        System.out.println(parse);

        System.out.println("===============");

        // 레거시 api 지원
        // 이전 Date 형 변환 지원
        Date date = new Date();
        Instant instant = date.toInstant();
        Date from = Date.from(instant);

        // 이전 Calendar 형 변환 지원
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime zonedDateTime2 = gregorianCalendar.getTime().toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from1 = GregorianCalendar.from(zonedDateTime2);

        // 이전 Timezone 형 변환 지원
        ZoneId pst = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone.getTimeZone(pst);
    }
}
