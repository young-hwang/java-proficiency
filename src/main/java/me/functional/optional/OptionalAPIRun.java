package me.functional.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalAPIRun {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        // Optional 을 return 하는 stream 은 terminate operator 이다.
        Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = spring.isPresent();
        System.out.println(present);

        // get() 값을 가져옴, 값을 있을 경우 문제 없음
        // 하지만 값이 null 일 경우 NoSuchElementException 발생
        // Optional API 다양한 지원 기능을 통해 해결
        OnlineClass onlineClass = spring.get();
        System.out.println(onlineClass.getTitle());

        spring.ifPresent(s -> System.out.println(s.getTitle()));

        // onlineClass 의 값이 있든 없는 createNewItem() 을 항상 호출
        // 상수로 만들어 진걸 참고 해서 리턴 할경우 사용
        OnlineClass onlineClass1 = spring.orElse(createNewItem());
        System.out.println(onlineClass1.getTitle());

        // onlineClass 의 값이 없을 경우 createNewItem() 을 호출
        // 메소드로 작업을 하여 결과가 만들어질 경우 사용
        OnlineClass onlineClass2 = spring.orElseGet(OptionalAPIRun::createNewItem);
        System.out.println(onlineClass2.getTitle());

        // Exception 을 발생 시키고 싶을 경우 사용
        // optional.OnlineClass onlineClass3 = spring.orElseThrow(IllegalStateException::new);
        // System.out.println(onlineClass3.getTitle());

        // filter 를 적용
        Optional<OnlineClass> onlineClass4 = spring.filter(oc -> oc.getId() > 10);
        System.out.println(onlineClass4.isPresent());

        // map 사용
        Optional<Integer> onlineClass5 = spring.map(OnlineClass::getId);
        System.out.println(onlineClass5.isPresent());

        // optional 을 가져오는 경우
        Optional<OnlineClass> onlineClass6 = spring.filter(oc -> oc.getTitle().startsWith("rest"));

        Optional<Optional<Progress>> progress = onlineClass6.map(OnlineClass::getProgress);
        //Optional<optional.Progress> progress1 = progress.get();
        // System.out.println(progress1.isPresent());

        // optional 을 가져 올때 flatMap 을 사용하는 경우, optional 중복 적용시 한 꺼풀 벗겨줌
        // Optional<optional.Progress> progress2 = onlineClass6.flatMap(optional.OnlineClass::getProgress);
        // System.out.println(progress2.isPresent());

    }

    private static OnlineClass createNewItem() {
        System.out.println("createNewItem");
        return new OnlineClass(1, "spring boot", true);
    }
}
