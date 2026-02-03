package me.util.datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int i = Integer.parseInt(reader.readLine());
            writer.write(String.valueOf(i - 543));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
