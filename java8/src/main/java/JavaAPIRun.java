import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class JavaAPIRun {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("hwang");
        names.add("young");
        names.add("park");
        names.add("kim");

        // Java Iterator
        names.forEach(System.out::println);

        System.out.println("----------------");

        for (String name: names) {
            System.out.println(name);
        }

        System.out.println("----------------");

        Spliterator<String> spliterator = names.spliterator();
        while(spliterator.tryAdvance(System.out::println));

        System.out.println("----------------");

//        Spliterator<String> halfSpliterator = spliterator.trySplit();
//        while(halfSpliterator.tryAdvance(System.out::println));
//
//        System.out.println("----------------");

        // Java Collector
        System.out.println("Collector");
        System.out.println("----------------");

        names.removeIf(s -> s.startsWith("h"));
        names.forEach(System.out::println);

        System.out.println("----------------");

        // Java Comparator
        System.out.println("Comparator");
        System.out.println("----------------");

        names.sort(String::compareToIgnoreCase);
        names.forEach(System.out::println);

        System.out.println("----------------");

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);
    }
}
