package me.core.reflection;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * 리플렉션으로 ClassLoader 내부 필드에 접근하여 로드된 클래스 목록 조회
 *
 * 주의: 내부 API 접근으로 Java 버전에 따라 동작하지 않을 수 있음
 */
public class ClassLoaderInspector {

    /**
     * ClassLoader에 이미 로드된 클래스 목록 조회
     */
    @SuppressWarnings("unchecked")
    public static Vector<Class<?>> getLoadedClasses(ClassLoader classLoader) throws Exception {
        // ClassLoader 내부의 'classes' 필드 접근
        // private final Vector<Class<?>> classes = new Vector<>();
        Field classesField = ClassLoader.class.getDeclaredField("classes");
        classesField.setAccessible(true);
        return (Vector<Class<?>>) classesField.get(classLoader);
    }

    /**
     * 특정 패키지의 로드된 클래스만 필터링
     */
    public static void printLoadedClasses(ClassLoader classLoader, String packageFilter) throws Exception {
        Vector<Class<?>> classes = getLoadedClasses(classLoader);

        System.out.println("=== Loaded classes" +
                (packageFilter != null ? " (filter: " + packageFilter + ")" : "") + " ===\n");

        int count = 0;
        for (Class<?> clazz : classes) {
            String name = clazz.getName();
            if (packageFilter == null || name.startsWith(packageFilter)) {
                System.out.println("  " + name);
                count++;
            }
        }

        System.out.println("\nTotal: " + count + " classes");
    }

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 1. 현재 로드된 클래스 확인 (me 패키지만)
        System.out.println("[ Before loading any class ]\n");
        printLoadedClasses(classLoader, "me.");

        // 2. 몇 가지 클래스 강제 로드
        System.out.println("\n[ Loading some classes... ]\n");
        Class.forName("me.core.reflection.data.User");
        Class.forName("me.core.reflection.data.Calculator");
        Class.forName("me.core.reflection.data.BasicData");

        // 3. 로드 후 다시 확인
        System.out.println("\n[ After loading classes ]\n");
        printLoadedClasses(classLoader, "me.core.reflection.data");

        // 4. 로드된 클래스에서 인스턴스 생성
        System.out.println("\n[ Creating instances from loaded classes ]\n");
        Vector<Class<?>> loadedClasses = getLoadedClasses(classLoader);

        for (Class<?> clazz : loadedClasses) {
            if (clazz.getName().startsWith("me.core.reflection.data")) {
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    System.out.println("Created: " + instance.getClass().getSimpleName());
                } catch (NoSuchMethodException e) {
                    System.out.println("Skipped (no default constructor): " + clazz.getSimpleName());
                }
            }
        }
    }
}
