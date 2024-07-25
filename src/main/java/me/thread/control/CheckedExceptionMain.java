package me.thread.control;

/**
 * Runnable 인터페이스의 run() 메소드를 구현할 때 InterruptedException 체크 예외를 던질 수 없는 이유
 * (체크 예외 재정의)
 *
 * Runnable Inteface 정의
 * <pre>
 * {@code
 * public interface Runnable {
 *       void run();
 * }
 * }
 * </pre>
 *
 * 메소드를 재정의 할 때, 재정의 메소드가 지켜야할 예외와 규칙이 존재
 * - 체크 예외
 *  - 부모 메서드가 체크 예외를 던지지 않는 경우, 재정의된 자식 메서드도 체크 예외를 던질 수 없다.
 *  - 자식 메소드는 부모 메서드가 던 질 수 있는 체크 예외 타입만 던질 수 있다.
 * - 언체크(런타임)예외
 *   - 예외 처리를 강제하지 않으므로 상관없이 던질 수 있다.
 *
 * 왜 이런 제약이 필요한가?
 *
 * 부모 클래스의 메소드를 호출하는 클라이언트 코드는 부모 메서드가 던지는 특정 예외만을 처리하도록 작성한다.
 * 자식 클래스가 더 넓은 범위의 예외를 던지면 해당 코드는 모든 예외를 제대로 처리하지 못할 수 있다.
 * 이는 예외 처리의 일관성을 해치고, 예상하지 못한 런타임 오류를 초래할 수 있다.
 *
 *  따라서 자식 클래스에 재정의된 메소드는 부모 메소드가 던질 수 있는 체크 예외의 하위 타입만을 던질 수 있다.
 *  원래 메서드가 체크 예외를 던지지 않는 경우, 재정의된 메서드도 체크 예외를 던질 수 없다.
 *
 *  안전한 예외 처리
 *
 *  체크 예외를 run() 메서드에서 던질수 없도록 강제함으로서 개발자는 반드시 체크 예외를 try-catch 블록 내에서 처리하게 된다.
 *  이는 예외 발생 시 예외가 적절히 처리되지 않아서 프로그램이 비정상 종료되는 상황을 방지할 수 있다.
 *  특히 멀티스레딩 환경에서는 예외 처리를 강제함으로써 스레드의 안정성과 일관성을 유지할 수 있다.
 *  체크 예외를 강제하는 부분은 자바 초창기 기조이고, 최근에는 체크 예외보다는 언체크(런타임) 예외를 선호한다.
 */
public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

//    static class CheckedRunnable implements Runnable {
//
//        // 체크 예외는 throw 할 수 없다
//        @Override
//        public void run() throws Exception {
//            throw new Exception();
//        }
//    }
}
