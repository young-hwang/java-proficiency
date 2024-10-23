package me.io.file;

import java.io.File;
import java.io.IOException;

public class OldFileMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        // exists(): 파일이나 디렉토리 존재 여부 확인
        System.out.println("File exists: " + file.exists());

        // createNewFile(): 새 파일 생성
        boolean created = file.createNewFile();
        System.out.println("File created: " + created);

        // mkdir(): 새 디렉토리 생성
        boolean dirCreated = directory.mkdir();
        System.out.println("Directory created: " + dirCreated);

        // isFile(): 파일 여부 확인
        boolean isFile = file.isFile();
        System.out.println("File isFile: " + isFile);

        // isDirectory(): 폴더 여부 확인
        boolean isDirectory = file.isDirectory();
        System.out.println("File isDirectory: " + isDirectory);

        // getName(): 파일명
        System.out.println("File name: " + file.getName());

        // length(): 파일 크기
        System.out.println("File size: " + file.length());

        // renameTo: 파일 이름 변경
        File newFile = new File("temp/example-new.txt");
        System.out.println("File name change: " + file.renameTo(newFile));

        // delete(): 파일 삭제
        boolean deleted = file.delete();
        System.out.println("File deleted: " + deleted);
    }
}
