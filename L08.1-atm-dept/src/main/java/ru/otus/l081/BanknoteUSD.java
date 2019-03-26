package ru.otus.l081;

public enum BanknoteUSD {
    USD1(Currency.USD, 1),
    USD2(Currency.USD, 2),
    USD5(Currency.USD, 5),
    USD10(Currency.USD, 10),
    USD20(Currency.USD, 20),
    USD50(Currency.USD, 50),
    USD100(Currency.USD, 100);

    private Currency currency;
    private int value;

    BanknoteUSD(Currency currency, int value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return currency.toString() + value;
    }
}
