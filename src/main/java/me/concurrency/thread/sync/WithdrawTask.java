package me.concurrency.thread.sync;

import static me.util.MyLogger.log;

public class WithdrawTask implements Runnable{
    private BankAccount bankAccount;
    private int amount;

    public WithdrawTask(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
//        log(Thread.currentThread().getName() + ": run start " + Thread.currentThread().getState());
        bankAccount.withdraw(amount);
//        log(Thread.currentThread().getName() + ": run end " + Thread.currentThread().getState());
    }
}
