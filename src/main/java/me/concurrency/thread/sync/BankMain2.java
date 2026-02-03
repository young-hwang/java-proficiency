package me.concurrency.thread.sync;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class BankMain2 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccountV2(1000);
        Thread t1 = new Thread(new WithdrawTask(bankAccount, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(bankAccount, 800), "t2");
        t1.start();
        t2.start();

        log("t1 state " + t1.getState());
        log("t2 state " + t2.getState());

        sleep(100);
        t1.join();
        t2.join();

        log("최종 잔액: " + bankAccount.getBalance());
    }
}
