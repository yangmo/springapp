package com.moyang.common.concurrency;


import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;


/**
 * This class represents a test task to run in one thread. 
 * each special task should extend from this class and implement the abstract runTest() method 
 * for special business logic.   
 *
 */
public abstract class TestRunnable implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(TestRunnable.class);
    private static AtomicInteger count = new AtomicInteger(0);
    
    private MultiThreadsTestController controller;     
    private int index;    
    
    protected TestRunnable() {
        this.index = count.incrementAndGet();        
    }
    
    public void setController(MultiThreadsTestController controller) {
        this.controller = controller;
    }
    
    /**
     * an abstract method to run test logic
     */
    public abstract void runTest();
    
    /**
     * implements run() in Runnable interface.
     */
    public void run() {
        if (controller == null) {
            throw new IllegalStateException("The MultiThreadsTestController instance should be set before starting.");
        }
        
        LOGGER.info("Starting test thread " + this.index);
        
        try {
            runTest();
        } catch (Throwable t) {
            controller.handleException(t);
        } 
        
        LOGGER.info("Ended test thread " + this.index);
    }
}
