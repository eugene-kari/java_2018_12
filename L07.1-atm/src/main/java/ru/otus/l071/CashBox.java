package ru.otus.l071;

public class CashBox {
    private Banknote banknote;
    private int count;
    private int capacity = 1000;

    CashBox(Banknote banknote) {
        this.banknote = banknote;
    }

    public void push(int count) {
        if (this.count + count > capacity) throw new RuntimeException("No place for banknotes");
        this.count += count;
    }

    public void pop(int count) {
        if (this.count < count) throw new RuntimeException("No banknotes");
        this.count -= count;
    }

    public int getBalance() {
        return banknote.getValue() * count;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return count;
    }
}
