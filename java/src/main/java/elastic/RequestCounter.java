package elastic;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Codesignal requires this class to be called Solution.
 *
 * The focus of this question is on the inner RequestCounter class.
 *
 * This exercise is not intended to be run, however you can check your
 * solution compiles by clicking "Run" in the UI below.
 */
class Solution {

    /**
     * Thread-safe counter for tracking incoming requests per application.
     *
     * <p> Maintain counts for a fixed set of {@link Application} instances.
     * Supports concurrent increments without locks using {@code ConcurrentHashMap} * and {@code AtomicLong}.
     */
    public static class RequestCounter {

        public static enum Application {
            APP_1, APP_2, APP_3, APP_4;
        }

        private final ConcurrentHashMap<Application, AtomicLong> requestMap = new ConcurrentHashMap<>();

        public RequestCounter(Collection<Application> validApplications) {
            for (Application app : validApplications) {
                requestMap.put(app, new AtomicLong(0));
            }
        }

        /**
         * Increments the request count for the given application, Ignore request count if the sourceApplication is invalid.
         *
         */
        public void countRequest(Application sourceApplication) {
            AtomicLong counter = requestMap.get(sourceApplication);
            if (counter != null) {
                counter.incrementAndGet();
            }
        }

        public Set<Application> getApplications() {
            return requestMap.keySet();
        }

        public long getTotalRequests() {
            return requestMap.values().stream().mapToLong(AtomicLong::get).sum();
        }

        /**
         * Returns the number of requests received from a specific application.
         *
         * @param sourceApplication the source of the request
         * @return the request count for the specified application, or 0 if the sourceApplication is invalid.
         */
        public long getRequestsByApplication(Application sourceApplication) {
            AtomicLong counter = requestMap.get(sourceApplication);
            return counter != null ? counter.get() : 0L;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 테스트할 대상 클래스
        Set<Solution.RequestCounter.Application> apps = EnumSet.allOf(Solution.RequestCounter.Application.class);
        Solution.RequestCounter counter = new Solution.RequestCounter(apps);

        int numThreads = 10;
        int incrementsPerThread = 1_000_000;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        Runnable task = () -> {
            for (int i = 0; i < incrementsPerThread; i++) {
                counter.countRequest(Solution.RequestCounter.Application.APP_1);
            }
        };

        long start = System.currentTimeMillis();

        // 여러 개의 스레드 실행
        for (int i = 0; i < numThreads; i++) {
            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        long end = System.currentTimeMillis();

        long total = counter.getRequestsByApplication(Solution.RequestCounter.Application.APP_1);
        System.out.println("Expected count: " + (numThreads * incrementsPerThread));
        System.out.println("Actual count: " + total);
        System.out.println("Total requests (all apps): " + counter.getTotalRequests());
        System.out.println("Execution time: " + (end - start) + " ms");

        // 간단한 검증
        if (total == (long) numThreads * incrementsPerThread) {
            System.out.println("✅ PASS: Thread-safe counting is correct.");
        } else {
            System.out.println("❌ FAIL: Race condition detected.");
        }
    }
}