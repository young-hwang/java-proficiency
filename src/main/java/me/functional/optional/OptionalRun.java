package me.functional.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalRun {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        // optional.Progress is null, Exception
        // Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        // System.out.println(studyDuration);

        // Exception 발생을 막기 위해 아래와 같이 처리
        // 에러가 발생하기 쉬운 구조
        // optional.Progress progress = spring_boot.getProgress();
        // if (progress != null) {
        //     System.out.println(progress.getStudyDuration());
        // }

        // Optional 을 이용
        Optional<Progress> progress = spring_boot.getProgress();
        progress.ifPresent(p -> System.out.println(p.getStudyDuration()));

        // Map key 로 optional 을 사용하면 안됨, key 는 null 될 수 없다

        // Class 의 멤버 변수로 사용하면 안됨

        // primitive type optional 을 사용하는게 좋다
        // OptionalInt, OptionalDouble 등

        // Container 타입을 Optional 로 wrapping 하지 마라
    }
}
