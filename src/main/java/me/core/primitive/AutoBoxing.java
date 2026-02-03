package me.core.primitive;

public class AutoBoxing {
    private static long sum_Long() {
        Long sum = 0L;
        for (long i = 1; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    private static long sum_long() {
        long sum = 0L;
        for (long i = 1; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    private static Long sum_Long_Long() {
        Long sum = 0L; // 0L = primitive, auto-boxing
        for (Long i = 1L; i <= Integer.MAX_VALUE; i++) {    // 1L = primitive, auto-boxing
            sum += i; // sum = Long Object, i = Long Object, 산술 연산은 primitive 필요 = auto-unboxing, 연산 후 sum auto-boxing
        }
        return sum;
    }

    public static void main(String[] args) {
        long start;
        long end;
        System.out.println("======= Long ========");
        start = System.nanoTime();
        sum_Long();
        end = System.nanoTime();
        System.out.println("long time " + (end - start));
        System.out.println("======= end ========");

        System.out.println();
        System.out.println("======= long ========");
        start = System.nanoTime();
        sum_long();
        end = System.nanoTime();
        System.out.println("long time " + (end - start));
        System.out.println("======= end ========");

        System.out.println();
        System.out.println("======= Long to Long ========");
        start = System.nanoTime();
        sum_Long_Long();
        end = System.nanoTime();
        System.out.println("long time " + (end - start));
        System.out.println("======= end ========");
    }
}
