package me.util.regular;

import java.util.regex.Pattern;

public class RomanNumerals {
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    private static final Pattern pattern = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return pattern.matcher(s).matches();
    }

    public static void main(String[] args) {
        var result = false;

        System.out.println("===== slow =====================");
        var start = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            result = isRomanNumeralSlow("MCMLXXVI");
        }

        var end = System.nanoTime();
        System.out.println("execute time = " + (end - start));
        System.out.println(result);

        System.out.println("===============================");
        System.out.println();
        System.out.println("===== fast =====================");
        start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            result = isRomanNumeralFast("MCMLXXVI");
        }

        end = System.nanoTime();
        System.out.println("execute time = " + (end - start));
        System.out.println(result);
        System.out.println("===============================");
    }
}
