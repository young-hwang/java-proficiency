package me.stream;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class CollectStream {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("USD", 100),
                new Transaction("USD", 200),
                new Transaction("USD", 300),
                new Transaction("USD", 400),
                new Transaction("USD", 500),
                new Transaction("USD", 600),
                new Transaction("USD", 700),
                new Transaction("USD", 800),
                new Transaction("USD", 900),
                new Transaction("USD", 1000)
        );

        Map<String, List<Transaction>> collect = transactions.stream().collect(groupingBy(Transaction::getCurrency));
    }
}

class Transaction {
    private String currency;
    private int value;

    public Transaction(String currency, int value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public int getValue() {
        return value;
    }
}
