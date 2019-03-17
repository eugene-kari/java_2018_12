package ru.otus.l021;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int size     = 1000000;
        int timeWait = 10;

        try {
            Benchmark benchmark = new Benchmark(size, timeWait);

            benchmark.run(() -> {
                return new String(new char[0]);
            });
            benchmark.run(() -> new String(new byte[0]));
            benchmark.run(() -> new String());

            benchmark.run(() -> new Integer("0"));

            benchmark.run(() -> new Short("0"));

            benchmark.run(() -> new Byte("0"));

            benchmark.run(() -> new Long("0"));

            benchmark.run(() -> new Float("0"));

            benchmark.run(() -> new Boolean("0"));

            benchmark.run(() -> new MyClass4());

            benchmark.run(() -> integerArrayList(1));
            benchmark.run(() -> integerArrayList(2));
            benchmark.run(() -> integerArrayList(5));
            benchmark.run(() -> integerArrayList(10));
            benchmark.run(() -> integerArrayList(20));
            benchmark.run(() -> integerArrayList(50));
            benchmark.run(() -> integerArrayList(100));
            benchmark.run(() -> integerArrayList(200));
        } catch (OutOfMemoryError e) {
            System.out.println("Not Enough memory");
        } catch (InterruptedException e) {
            System.out.printf("Interrupted exception happened, message: %s\n", e.getMessage());
        }
    }
    private static List<Integer> integerArrayList(int size){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }
}