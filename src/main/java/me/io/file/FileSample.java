package me.io.file;

import java.io.File;

public class FileSample {
    public static void main(String[] args) {
        FileSample sample = new FileSample();
        String pathname = "/Users/id_young/text";
        sample.checkPath(pathname);
        sample.makeDir(pathname);
        sample.checkFileMethods(pathname);
    }

    private void checkFileMethods(String pathname) {
        File file = new File(pathname);
        System.out.println(pathname + " is directory? = " + file.isDirectory());
        System.out.println(pathname + " is file? = " + file.isFile());
        System.out.println(pathname + " is hidden? = " + file.isHidden());
    }

    private void checkPath(String pathname) {
        File file = new File(pathname);
        System.out.println(pathname + " is exists? " + file.exists());
    }

    private void makeDir(String pathname) {
        File file = new File(pathname);
        System.out.println("Make " + pathname + " result = " + file.mkdir());
    }
}

/*
/Users/id_young/text is exists? false
Make /Users/id_young/text result = true
/Users/id_young/text is directory? = true
/Users/id_young/text is file? = false
/Users/id_young/text is hidden? = false
 */