package ru.otus.l031;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrList = new MyArrayList<>();

        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            myArrList.add(rnd.nextInt(10));
        }
        System.out.println(myArrList);
    }
}
