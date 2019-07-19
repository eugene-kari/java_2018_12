package ru.otus.l031;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test Class MyArrayList")
class MyArrayListTest {
    MyArrayList<Integer> myArrList;
    ArrayList<Integer> arrList;
    Integer[] elements;

    @BeforeAll
    static void beforeClass() {
//        System.out.println("BeforeAll");
    }

    @BeforeEach
    void init() {
        System.out.println("----- BeforeEach -----");

        arrList = new ArrayList<>();
        myArrList = new MyArrayList<>();

        Random rnd = new Random();
        elements = new Integer[10];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = rnd.nextInt(10);
        }
        printAll();
    }

    @AfterEach
    void tearDown() {
//        System.out.println("AfterEach");
    }

    @AfterAll
    static void afterClass(){
//        System.out.println("AfterAll");
    }

    void printAll()
    {
        System.out.println("elements: " + Arrays.toString(elements));
        System.out.println("arrList: " + arrList);
        System.out.println("myArrList: " + myArrList);
    }

    @Test
    @DisplayName("addAll")
    void addAll() {
        System.out.println("----- addAll -----");
        Collections.addAll(arrList, elements);
        //
        Collections.addAll(myArrList, elements);
        //
        printAll();
        assertArrayEquals(arrList.toArray(), myArrList.toArray());
    }

    @Test
    @DisplayName("copy")
    void copy() {
        System.out.println("----- copy -----");
        Collections.addAll(arrList, elements);
        for (int i = 0; i < arrList.size(); i++) myArrList.add(0);
        //
        Collections.copy(myArrList, arrList);
        //
        printAll();
        assertArrayEquals(arrList.toArray(), myArrList.toArray());
    }

    @Test
    @DisplayName("sort")
    void sort() {
        System.out.println("----- sort -----");
        Collections.addAll(arrList, elements);
        Collections.sort(arrList, (e1, e2) -> (e1 < e2) ? 1 : ((e1 == e2) ? 0 : -1)); //desc order
        Collections.addAll(myArrList, elements);
        //
        Collections.sort(myArrList, (e1, e2) -> (e1 < e2) ? 1 : ((e1 == e2) ? 0 : -1)); //desc order
        //
        printAll();
        assertArrayEquals(arrList.toArray(), myArrList.toArray());
    }

}
