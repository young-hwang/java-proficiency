package me.io.net.autoclosable;

public class ResourceCloseMainV2 {
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
                resource2.closeEx();
            }
            if (resource1 != null) {
                resource1.close();
            }
        }
    }
}
