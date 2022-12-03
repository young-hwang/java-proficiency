import java.util.Arrays;

@ChickenAll("노랑치킨")
@ChickenAll("굽네")
public class AnnotationRun {
    public static void main(String[] args) {
        // annotation type 에 따른 조회
        ChickenAll[] declaredAnnotationsByType = AnnotationRun.class.getDeclaredAnnotationsByType(ChickenAll.class);
        Arrays.asList(declaredAnnotationsByType).forEach(c -> System.out.println(c.value()));

        System.out.println("=======");

        // container annotation 을 이용한 조회
        ChickenContainer annotation = AnnotationRun.class.getAnnotation(ChickenContainer.class);
        Arrays.asList(annotation.value()).forEach(c -> System.out.println(c.value()));
    }

    static class LikeChicken<@Chicken T> {
        public static <@ChickenAll("test") C> void printChicken(@ChickenAll("test1") C chicken) {
            System.out.println(chicken);
        }
    }
}
