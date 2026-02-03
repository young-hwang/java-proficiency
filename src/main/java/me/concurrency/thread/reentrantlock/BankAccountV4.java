package me.concurrency.thread.reentrantlock;

import me.concurrency.thread.sync.BankAccount;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount {
    private int balance;
    private Lock lock = new ReentrantLock();

    public BankAccountV4(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        lock.lock(); // ReentrantLock 이용하여 Lock 걸리
        try {
            // 잔고가 출금액 보다 적으면 진행 X
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }

            // 잔고가 출금액 보다 많으면 진행 O
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(500);
            balance -= amount;
            log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
        } finally {
            lock.unlock();
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
