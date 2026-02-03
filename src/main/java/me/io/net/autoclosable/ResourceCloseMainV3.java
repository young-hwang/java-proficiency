package me.io.net.autoclosable;

public class ResourceCloseMainV3 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");
            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            System.out.println("CallException" + e);
            throw e;
        } finally {
            System.out.println("자원 정리");
            if (resource2 != null) {
                try {
                    resource2.closeEx();
                } catch (CloseException e) {
                    // close()에서 발생한 예외는 버림. 필요하면 로깅 정도
                    // io에서 발생한 예외는 처리할 방안이 딱히 없음
                    System.out.println("CloseException" + e);
                }
            }
            if (resource1 != null) {
                resource1.close();
            }
        }
    }
}
