package me.charset;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_16;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_16LE;
import static java.nio.charset.StandardCharsets.UTF_8;

public class EncodingMain {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE);
        encoding("A", UTF_16LE);
        encoding("A", UTF_16);

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR);
        encoding("가", MS_949);
        encoding("가", UTF_8);
        encoding("가", UTF_16BE);

        System.out.println("===========");
        printBytes("가", EUC_KR);

    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte", text, charset, Arrays.toString(bytes), bytes.length);
        System.out.println();
    }

    private static void printBytes(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(printByteToBinary(bytes[i]));
            System.out.print(" ");
        }
    }

    private static String printByteToBinary(byte b) {
        // 8비트로 맞추기 위해 AND 연산으로 0xFF 비트 마스킹 적용
        return String.format("%8s", Integer.toBinaryString( b & 0xFF)).replace(' ', '0');
    }
}
