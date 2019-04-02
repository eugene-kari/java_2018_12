package ru.otus.l041;

import com.sun.management.GarbageCollectionNotificationInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.management.openmbean.CompositeData;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import java.lang.management.GarbageCollectorMXBean;

public class Benchmark implements BenchmarkMBean {
    public static Map<String, Long> totalTimes = new HashMap<>();
    public static Map<String, Long> totalRuns = new HashMap<>();

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