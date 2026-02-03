package me.core.reflection;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassLoader.getResource()를 사용한 패키지 스캔 예제
 */
public class ClassPathScanner {

    /**
     * 지정된 패키지에서 모든 클래스를 찾아 인스턴스 생성
     */
    public static List<Object> createInstancesFromPackage(String packageName) throws Exception {
        List<Object> instances = new ArrayList<>();
        List<Class<?>> classes = scanPackage(packageName);

        for (Class<?> clazz : classes) {
            // 인터페이스, 추상 클래스, 내부 클래스 제외
            if (clazz.isInterface() || java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) {
                continue;
            }
            if (clazz.isMemberClass() || clazz.isLocalClass() || clazz.isAnonymousClass()) {
                continue;
            }

            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                instances.add(instance);
                System.out.println("Created: " + clazz.getName());
            } catch (NoSuchMethodException e) {
                System.out.println("Skipped (no default constructor): " + clazz.getName());
            }
        }

        return instances;
    }

    /**
     * 패키지 내 모든 클래스 스캔
     */
    public static List<Class<?>> scanPackage(String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();

        // 1. 패키지명 -> 경로 변환
        String path = packageName.replace('.', '/');

        // 2. ClassLoader로 리소스 URL 획득
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        if (resource == null) {
            throw new IllegalArgumentException("Package not found: " + packageName);
        }

        System.out.println("Resource URL: " + resource);
        System.out.println("Protocol: " + resource.getProtocol());

        // 3. file:// 프로토콜인 경우 디렉토리 스캔
        if ("file".equals(resource.getProtocol())) {
            File directory = new File(resource.toURI());
            scanDirectory(directory, packageName, classes);
        }
        // jar:// 프로토콜은 별도 처리 필요 (생략)

        return classes;
    }

    /**
     * 디렉토리에서 .class 파일 탐색
     */
    private static void scanDirectory(File directory, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                // 하위 패키지 재귀 탐색
                scanDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                // .class 파일 -> 클래스 로드
                String className = packageName + "." + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // me.core.reflection.data 패키지의 모든 클래스 인스턴스 생성
        String targetPackage = "me.core.reflection.data";

        System.out.println("=== Scanning package: " + targetPackage + " ===\n");

        List<Class<?>> classes = scanPackage(targetPackage);
        System.out.println("\nFound " + classes.size() + " classes:");
        for (Class<?> clazz : classes) {
            System.out.println("  - " + clazz.getSimpleName());
        }

        System.out.println("\n=== Creating instances ===\n");
        List<Object> instances = createInstancesFromPackage(targetPackage);

        System.out.println("\n=== Result ===");
        System.out.println("Created " + instances.size() + " instances");
    }
}
