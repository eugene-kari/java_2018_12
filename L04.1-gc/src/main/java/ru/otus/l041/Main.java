package ru.otus.l041;

/* -Xms30m -Xmx30m */

import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.management.ManagementFactory;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<String> arrayList = new ArrayList<>();
        int size = 100;

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, new ObjectName("ru.otus:type=Benchmark"));

        long startTime = System.nanoTime();
        try {
            mbean.subscribeToGcEvents();

            while (true) {
                for (int i = 0; i < size; i++) {
                    arrayList.add(new String("abcdefghijklmnopqrstuvwxyz0123456789"));
                }
                int currentArraySize = arrayList.size();
                for (int i = 1; i < Math.round(size / 2); i++) {
                    arrayList.remove(currentArraySize - i);
                }
                Thread.sleep(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Thread.sleep(5000);
            System.out.println("gcTime (ms): " + Benchmark.totalTimes);
            System.out.println("gcRuns: " + Benchmark.totalRuns);
            System.out.println("Time (s): " + TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
        }
    }
}