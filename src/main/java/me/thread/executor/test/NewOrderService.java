package me.thread.executor.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class NewOrderService {

    public void order(String orderNo) throws InterruptedException, ExecutionException {
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        try(ExecutorService executor = Executors.newFixedThreadPool(10);) {
            // 작업 요청
            // ExecutorService에 제출
            Future<Boolean> inventoryFuture = executor.submit(inventoryWork);
            Future<Boolean> shippingFuture = executor.submit(shippingWork);
            Future<Boolean> accountingFuture = executor.submit(accountingWork);

            // 작업 완료 대기
            Boolean inventoryResult = inventoryFuture.get();
            Boolean shippingResult = shippingFuture.get();
            Boolean accountResult = accountingFuture.get();

            // 결과 확인
            if (inventoryResult && shippingResult && accountResult) {
                log("모든 주문 처리 성공적으로 완료");
            } else {
                log("일부 작업이 실패");
            }
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e;
        }
    }

    private class InventoryWork implements Callable<Boolean> {
        private String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() throws Exception {
            log("재고 업데이트 시작: " + orderNo);
            sleep(1000);
            log("재고 업데이트 종료: " + orderNo);
            return true;
        }
    }

    private class ShippingWork implements Callable<Boolean> {
        private String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() throws Exception {
            log("배송 시스템 알림 시작: " + orderNo);
            sleep(1000);
            log("배송 시스템 알림 종료: " + orderNo);
            return true;
        }
    }

    private class AccountingWork implements Callable<Boolean> {
        private String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() throws Exception {
            log("회계 시스템 업데이트 시작: " + orderNo);
            sleep(1000);
            log("회계 시스템 업데이트 종료: " + orderNo);
            return true;
        }
    }

}
