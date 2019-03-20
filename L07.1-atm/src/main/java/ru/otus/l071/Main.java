package ru.otus.l071;

import java.util.Map;

public class Main {

    public static void main(String... args) {
        Map<Banknote, Integer> result;

        ATM atm = new ATM();

        //Первичная загрузка банкнот
        System.out.println("=== init ===");
        atm.cashIn(Banknote.RUB100, 100);
        atm.cashIn(Banknote.RUB500, 10);
        atm.cashIn(Banknote.RUB1000, 5);
        atm.cashIn(Banknote.USD10, 100);
        atm.cashIn(Banknote.USD100, 10);
        atm.getBalance().forEach((k, v) -> System.out.println(k + "=" + v));
        //
        System.out.println("=== cashOut(RUB5500) ===");
        result = atm.cashOut(Currency.RUB, 5500);
        result.forEach((k, v) -> System.out.println(v + " x " + k));
        System.out.println("=== balance ===");
        atm.getBalance().forEach((k, v) -> System.out.println(k + "=" + v));
        //
        System.out.println("=== cashOut(USD350) ===");
        result = atm.cashOut(Currency.USD, 350);
        result.forEach((k, v) -> System.out.println(v + " x " + k));
        System.out.println("=== balance ===");
        atm.getBalance().forEach((k, v) -> System.out.println(k + "=" + v));
        //
        System.out.println("=== cashIn(RUB800) ===");
        atm.cashIn(Banknote.RUB100, 8);
        System.out.println("=== balance ===");
        atm.getBalance().forEach((k, v) -> System.out.println(k + "=" + v));
        //
        System.out.println("=== cashIn(USD50) ===");
        atm.cashIn(Banknote.USD10, 5);
        System.out.println("=== balance ===");
        atm.getBalance().forEach((k, v) -> System.out.println(k + "=" + v));
        //
        System.out.println("=== cashOut(RUB20000) ===");
        result = atm.cashOut(Currency.RUB, 20000);
        result.forEach((k, v) -> System.out.println(v + " x " + k));
        System.out.println("=== balance ===");
        atm.getBalance().forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
