package me.util.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IMEWithImSelect {
    public static void getIMEWithRuntime() {
        try {
            System.out.println("start getIMEWithRuntime");
            Process process = Runtime.getRuntime().exec("/opt/homebrew/bin/im-select");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String ime = reader.readLine();
            if (ime != null) {
                System.out.println(ime);
            } else {
                System.out.println("No ime selected");
            }
            System.out.println("end getIMEWithRuntime");
        } catch (IOException e) {
            System.out.println("Error while executing ime select");
        }
    }

    public static void getIMEWithProcessBuilder() {
        try {
            System.out.println("start getIMEWithProcessBuilder");
            ProcessBuilder processBuilder = new ProcessBuilder("/opt/homebrew/bin/im-select");
            processBuilder.redirectErrorStream(true);   // 오류도 같은 스트림으로 처리
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String ime = reader.readLine();
            if (ime != null) {
                System.out.println(ime);
            } else {
                System.out.println("No ime selected");
            }
            System.out.println("end getIMEWithProcessBuilder");
        } catch (IOException e) {
            System.out.println("Error while executing ime select");
        }
    }

    public static void main(String[] args) {
        getIMEWithRuntime();
        getIMEWithProcessBuilder();
    }
}
