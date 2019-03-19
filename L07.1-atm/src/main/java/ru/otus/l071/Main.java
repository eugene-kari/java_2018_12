package ru.otus.l071;

import java.util.Map;

public class Main {

    public static void main(String... args) {
        ATM atm = new ATM();
        atm.cashIn(Banknote.RUB200, 100);
        atm.cashIn(Banknote.RUB500, 100);
        atm.cashIn(Banknote.RUB1000, 5);
        atm.cashIn(Banknote.USD10, 100);
        atm.cashIn(Banknote.USD100, 1);

        System.out.println(atm.getRemainder());
        System.out.println("------------------");
        Map<Banknote, Integer> result = atm.cashOut(Currency.RUB, 5200);
        System.out.println(result);
        System.out.println(atm.getRemainder());

        Map<Banknote, Integer> result1 = atm.cashOut(Currency.RUB, 5100);
        System.out.println(result1);
        System.out.println(atm.getRemainder());

        Map<Banknote, Integer> result2 = atm.cashOut(Currency.RUB, 200);
        System.out.println(result2);
        System.out.println(atm.getRemainder());

        Map<Banknote, Integer> result3 = atm.cashOut(Currency.RUB, 600);
        System.out.println(result3);
        System.out.println(atm.getRemainder());

        Map<Banknote, Integer> result4 = atm.cashOut(Currency.USD, 220);
        System.out.println(result4);
        System.out.println(atm.getRemainder());

    }

}
