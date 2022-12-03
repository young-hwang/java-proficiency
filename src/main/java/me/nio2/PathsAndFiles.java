package me.nio2;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFiles {
    public static void main(String[] args) {
        PathsAndFiles pathsAndFiles = new PathsAndFiles();
        String dir = new File(".").getAbsolutePath();
        pathsAndFiles.checkPath(dir);
        String dir2 = new File("./../../nio2").getAbsolutePath();
        pathsAndFiles.checkPath2(dir, dir2);
    }

    private void checkPath(String dir) {
        Path path = Paths.get(dir);
        System.out.println(path.toString());
        System.out.println("getFileName():" + path.getFileName());
        System.out.println("getNameCount():" + path.getNameCount());
        System.out.println("getParent():" + path.getParent());
        System.out.println("getRoot():" + path.getRoot());
    }

    private void checkPath2(String dir, String dir2) {
        Path path = Paths.get(dir);
        Path path2 = Paths.get(dir2);
        Path relativize = path.relativize(path2);   // 현재 path와 매개변수 path 와이 상대 경로
        System.out.println("relativize():" + relativize);
        Path absolutePath = relativize.toAbsolutePath();    // relative로 구해진 상대경로를 절대 경로로 변경
        System.out.println("toAbsolutePath():" + absolutePath);
        Path normalize = absolutePath.normalize();  // 경로상에 '.'이나 '..'을 제거
        System.out.println("normalize():" + normalize);
        Path resolve = path.resolve("young");
        System.out.println("resolve():" + resolve); // 현재 path에 매개변수 path 추가
    }
}
