import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeRun {
    public static void main(String[] args) throws InterruptedException {
        // 기존 date 관련 class
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();


        // 기존 date 문제

        // 1. 이름 작명의 문제
        // date 이지만 time 까지 포함됨
        System.out.println(date);

        // 2. 사람이 읽을 수 없는 값이 출력
        long time = date.getTime();
        System.out.println(time);

        // 3. immutable 하지 않음
        // thread safe 하지 않음, multi thread 에서 안전하게 사용 못함
        // 일자, 시간을 변경 시 객체의 값이 바뀜
        Thread.sleep(3000);
        Date delay3Second = new Date();
        System.out.println(delay3Second);
        delay3Second.setTime(time);
        System.out.println(delay3Second);

        System.out.println("===== Calendar ======");

        // Calendar  문제
        // 1. Type Safe 하지 않음
        // int 형이 들어 오므로 어떠한 값이 들어 올 수 있음
        Calendar birthday1 = new GregorianCalendar(1984, 1, 10);
        Calendar birthday2 = new GregorianCalendar(1984, Calendar.FEBRUARY, 10);

        // 2. 이름의 부정확함
        // Date 정보를 가죠오는데 getTime()을 사용
        System.out.println(birthday2.getTime());

        // 3. immutable 하지 않음
        birthday2.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(birthday2.getTime());

    }
}
