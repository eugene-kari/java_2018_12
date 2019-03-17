package ru.otus.l071;

public enum Banknote {
    RUB5(Currency.RUB, 5),
    RUB10(Currency.RUB, 10),
    RUB50(Currency.RUB, 50),
    RUB100(Currency.RUB, 100),
    RUB200(Currency.RUB, 200),
    RUB500(Currency.RUB, 500),
    RUB1000(Currency.RUB, 1000),
    RUB2000(Currency.RUB, 2000),
    RUB5000(Currency.RUB, 5000),

    USD1(Currency.USD, 1),
    USD2(Currency.USD, 2),
    USD5(Currency.USD, 5),
    USD10(Currency.USD, 10),
    USD20(Currency.USD, 20),
    USD50(Currency.USD, 50),
    USD100(Currency.USD, 100);

    private Currency currency;
    private int value;

    Banknote(Currency currency, int value) {
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
        return value + currency.toString();
    }
}
