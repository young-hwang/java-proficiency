package me.io.trywithresources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryWithResource {
    // 여러 개의 catch로 인한 가독성 저하
    public void scanFile(String fileName, String encoding) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName), encoding);
            System.out.println(scanner.nextLine());
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (FileNotFoundException ffe) {
            ffe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    // catch block 처리 방식이 동일할 경우 간단히 처리 가능
    public void newScanFile(String fileName, String encoding) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName), encoding);
            System.out.println(scanner.nextLine());
        } catch (IllegalArgumentException | FileNotFoundException | NullPointerException exception) {
            exception.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    // try-with-resource
    // AutoCloseable 인터페이스가 추가되어 이를 구현한 클래스는 따로 close() 호출 필요가 없음.
    public void newScanFileTryWithResource(String fileName, String encoding) {
        try (Scanner scanner = new Scanner(new File(fileName), encoding);) {
            System.out.println(scanner.nextLine());
        } catch (IllegalArgumentException | FileNotFoundException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }


}
