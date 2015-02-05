package com.moyang.common.concurrency;

/**
 * This class is a controller for multiple threads test all runners are added into the controller and started by the
 * controller
 * 
 */
public class MultiThreadsTestController {
    private Throwable exception;
    private final TestRunnable[] allRunners;
    private ThreadGroup threadGroup;

    public MultiThreadsTestController(TestRunnable[] runners) {
        if (runners == null) {
            throw new IllegalArgumentException("No runners are defined.");
        }

        allRunners = new TestRunnable[runners.length];
        for (int i = 0; i < runners.length; i++) {
            allRunners[i] = runners[i];
        }
    }

    /**
     * starts all runnable tests
     * 
     * @throws Throwable
     *             if any error or exception during running.
     */
    public void runAllTests() throws Throwable {
        threadGroup = new ThreadGroup(MultiThreadsTestController.class.getName());
        Thread[] runnerThreads = setupThreads(threadGroup, allRunners);

        // wait for all threads to complete
        joinThreads(runnerThreads);

        synchronized (this) {
            if (exception != null) {
                throw exception;
            }
        }
    }

    /**
     * run threads without join
     * 
     * @throws Throwable
     */
    public void runWithoutJoin() throws Throwable {
        threadGroup = new ThreadGroup(MultiThreadsTestController.class.getName());
        setupThreads(threadGroup, allRunners);

        synchronized (this) {
            if (exception != null) {
                throw exception;
            }
        }
    }

    /**
     * get live thread count
     * 
     * @return
     */
    public int getLiveThreadCount() {
        int count = threadGroup.activeCount();
        return count;
    }

    /**
     * 
     * @param t
     * @throws InterruptedException
     */
    public void joinThreads(Thread[] t) throws InterruptedException {
        // check the arguments
        if (t == null || t.length < 0) {
            return;
        }

        for (Thread element : t) {
            if (element != null) {
                element.join();
            }
        }

    }

    private Thread[] setupThreads(ThreadGroup tg, TestRunnable[] tr) {
        int len = tr.length;
        Thread[] threads = new Thread[len];

        for (int i = 0; i < len; i++) {
            tr[i].setController(this);
            threads[i] = new Thread(tg, tr[i]);
            threads[i].setDaemon(true);
        }

        for (int i = 0; i < len; i++) {
            threads[i].start();
        }
        return threads;
    }

    /**
     * a synchronized method to handle exception passed from runnable tests.
     * 
     * @param t
     *            a error or exception caught in runnable test
     */
    public synchronized void handleException(Throwable t) {
        if (exception == null) {
            exception = t;
        }
        stopThreads();
    }

    private void stopThreads() {
        int count = threadGroup.activeCount();
        Thread[] t = new Thread[count];
        threadGroup.enumerate(t);
        for (Thread element : t) {
            if (element != null && element.isAlive()) {
                element.interrupt();
            }
        }
    }
}