package me.thread.executor.test;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class OldOrderService {
    public void order(String orderNo) {
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        // 작업 요청
        Boolean inventoryResult = inventoryWork.call();
        Boolean shippingResult = shippingWork.call();
        Boolean accountResult = accountingWork.call();

        // 결과 확인
        if (inventoryResult && shippingResult && accountResult) {
            log("모든 주문 처리 성공적으로 완료");
        } else {
            log("일부 작업이 실패");
        }
    }

    private class InventoryWork {
        private String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("재고 업데이트 시작: " + orderNo);
            sleep(1000);
            log("재고 업데이트 종료: " + orderNo);
            return true;
        }
    }

    private class ShippingWork {
        private String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("배송 시스템 알림 시작: " + orderNo);
            sleep(1000);
            log("배송 시스템 알림 종료: " + orderNo);
            return true;
        }
    }

    private class AccountingWork {
        private String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("회계 시스템 업데이트 시작: " + orderNo);
            sleep(1000);
            log("회계 시스템 업데이트 종료: " + orderNo);
            return true;
        }
    }
}
