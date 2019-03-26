package ru.otus.l081;

public enum BanknoteRUB extends BanknoteUSD {
    RUB10(Currency.RUB, 10),
    RUB50(Currency.RUB, 50),
    RUB100(Currency.RUB, 100),
    RUB200(Currency.RUB, 200),
    RUB500(Currency.RUB, 500),
    RUB1000(Currency.RUB, 1000),
    RUB2000(Currency.RUB, 2000),
    RUB5000(Currency.RUB, 5000);

    private Currency currency;
    private int value;

    BanknoteRUB(Currency currency, int value) {
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
