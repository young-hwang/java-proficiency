import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcuationTest {
    @Test
    void java_tricky_calculation_test() {
        // given
        int a = 10;
        // when
        a += (a = 5) * (a / 5);
        // then
        Assertions.assertEquals(a, 15);
    }
}
