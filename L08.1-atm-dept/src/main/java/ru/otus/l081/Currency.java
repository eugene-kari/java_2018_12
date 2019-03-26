package ru.otus.l081;

public enum Currency {
    RUB("RUB"),
    USD("USD");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
