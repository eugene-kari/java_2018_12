package ru.otus.l071;

public class Main {

    public static void main(String... args) {


        ATM atm = new ATM();

        CashBox cashBox;
        cashBox = new CashBox(Banknote.RUB500);
        System.out.println(cashBox.getBalance());

        Banknote banknote = Banknote.RUB100;
        System.out.println(banknote);

        Banknote banknote2 = Banknote.USD100;
        System.out.println(banknote2);

    }

}
