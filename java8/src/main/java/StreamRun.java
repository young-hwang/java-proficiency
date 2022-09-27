import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamRun {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("hwang");
        names.add("young");
        names.add("park");
        names.add("kim");

        // stream
        // sequence of elements supporting sequential and parallel aggregate operations
        // 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
        // Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
        // 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
        // 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
        // 중개 오퍼레이션은 근본적으로 lazy 하다.
        // 손쉽게병렬처리할수있다.

        // 중개 오퍼레이터 : stream 리턴, 여러개가 올 수 있음
        Stream<String> stream = names.stream().map(s -> s);

        // 터미널 오퍼레이터 : 실제 적인 실행이 이루어짐, 반드시 하나가 있어야 함
        List<String> names1 = names.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println("----------------");

        // 병렬 실행 - 데이터가 아주 많을 시 효율적
        names.parallelStream().map(s -> {
            System.out.println(s + " " +  Thread.currentThread().getName());
            return s;
        }).collect(Collectors.toList());


    }
}
