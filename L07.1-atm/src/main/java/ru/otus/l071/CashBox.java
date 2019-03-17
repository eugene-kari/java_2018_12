package ru.otus.l071;

public class CashBox {
    public Banknote banknote;
    public int count;
    public int capacity;

    CashBox(Banknote banknote) {
        this.banknote = banknote;
        capacity = 1000;
    }

    public void push(int count) {
        if (this.count + count > capacity) throw new RuntimeException("No place for count=" + count);
        this.count += count;
    }

    public void pop(int count) {
        if (this.count < count) throw new RuntimeException("No banknote count=" + count);
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
