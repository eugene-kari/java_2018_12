package ru.otus.l081;

public class CashBox {
    private BanknoteRUB banknoteRUB;
    private int count;
    static final int CAPACITY = 500;

    CashBox(BanknoteRUB banknoteRUB) {
        this.banknoteRUB = banknoteRUB;
    }

    public void push(int count) {
        if (this.count + count > CAPACITY) throw new IllegalArgumentException("No place for banknotes");
        this.count += count;
    }

    public void pop(int count) {
        if (this.count < count) throw new IllegalArgumentException("No banknotes");
        this.count -= count;
    }

    public int getBalance() {
        return banknoteRUB.getValue() * count;
    }

    public BanknoteRUB getBanknoteRUB() {
        return banknoteRUB;
    }

    public int getCount() {
        return count;
    }
}
