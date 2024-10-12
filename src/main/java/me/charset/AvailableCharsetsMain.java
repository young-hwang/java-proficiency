package me.charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {
    public static void main(String[] args) {
        // 이용 가능한 모든 Charset 자바 + OS
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        for (String charsetName : stringCharsetSortedMap.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }
        System.out.println("==========");

        // 문자로 조회(대소문자 구분 X), MS949, ms949, x-windows-949
        Charset charset1 = Charset.forName("MS949");
        System.out.println("charset1 = " + charset1);

        // 별칭 조회
        Set<String> aliases = charset1.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        // 문자로 조회(대소문자 구분 X), UTF-8
        Charset charset2 = StandardCharsets.UTF_8;
        System.out.println("charset2 = " + charset2);

        // 별칭 조회
        Set<String> aliases2 = charset2.aliases();
        for (String alias : aliases2) {
            System.out.println("alias = " + alias);
        }

        // default Character set 조회
        Charset charset3 = Charset.defaultCharset();
        System.out.println("charset3 = " + charset3);

        Charset charset4 = StandardCharsets.UTF_8;
        System.out.println("charset4 = " + charset3);
    }
}
