package ru.otus.l071;

public enum Currency {
    RUB("р."),
    USD("$");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
