import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) // TYPE_PARAMETER 보다 포괄적인 사용
@Repeatable(ChickenContainer.class)
public @interface ChickenAll {
    String value();
}
