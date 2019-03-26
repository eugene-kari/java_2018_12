package ru.otus.l041;

import com.sun.management.GarbageCollectionNotificationInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.management.openmbean.CompositeData;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import java.lang.management.GarbageCollectorMXBean;

public class Benchmark implements BenchmarkMBean {
    private static ArrayList<String> arrayList = new ArrayList<>();
    private int size = 0;

    public static Map<String, Long> totalTimes = new HashMap<>();
    public static Map<String, Long> totalRuns = new HashMap<>();

    void run() throws InterruptedException {
        while (true) {
            for (int i = 0; i < this.size; i++) {
                arrayList.add("abcdefghijklmnopqrstuvwxyz0123456789");
            }
            int currentArraySize = arrayList.size();
            for (int i = 1; i < Math.round(this.size / 2); i++) {
                arrayList.remove(currentArraySize - i);
            }
            Thread.sleep(1);
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(final int size) {
        this.size = size;
    }

    @Override
    public void subscribeToGcEvents() {
        List<GarbageCollectorMXBean> gcBeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println(gcBean.getName());

            NotificationEmitter emitter = (NotificationEmitter) gcBean;

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    long duration = gcInfo.getGcInfo().getDuration();
                    String gcName = gcInfo.getGcName();

                    totalRuns.put(gcName, gcInfo.getGcInfo().getId());
                    totalTimes.merge(gcName, duration, Long::sum);

                    System.out.println(gcInfo.getGcAction() + ": "
                            + gcInfo.getGcInfo().getId()
                            + " = " + gcName
                            + " (from " + gcInfo.getGcCause() + ") "
                            + duration + " milliseconds");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}