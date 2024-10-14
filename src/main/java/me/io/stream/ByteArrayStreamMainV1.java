package me.io.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayStreamMainV1 {
    public static void main(String[] args) throws IOException {
        byte[] input = {1, 2, 3};

        // 메모리에 쓰기
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(input);

        // 메모리에서 읽기
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        byte[] bytes = inputStream.readAllBytes();

        System.out.println(Arrays.toString(bytes));

        outputStream.close();
        inputStream.close();
    }
}
