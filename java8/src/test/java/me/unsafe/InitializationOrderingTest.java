package me.unsafe;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class InitializationOrderingTest {
    private static final Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get Unsafe instance", e);
        }
    }

    @Test
    public void testInitializationOrdering() {
        // given
        InitializationOrdering ordering = new InitializationOrdering();
        // then
        assertEquals(1, ordering.getA());
    }

    @Test
    void testInitializationOrderingWithUnsafe() throws InstantiationException {
        // given
        InitializationOrdering o2 = (InitializationOrdering) unsafe.allocateInstance(InitializationOrdering.class);
        // when
        // then
        assertEquals(o2.getA(), 0);
    }
}