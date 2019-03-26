package ru.otus.l081;

public interface Banknote {
    Banknote(Currency currency, int value);

    public Currency getCurrency();

    public int getValue();
}
