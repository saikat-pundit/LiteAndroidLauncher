
package com.hayaisoftware.launcher.threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleTaskConsumerManager {

    private final BlockingQueue<Task> mTasks;
    private volatile int mNumThreadsAlive;
    private boolean mConsumersShouldDie;
    private SimpleTaskConsumer[] mSimpleTaskConsumers;
    private Thread[] threads;

    public SimpleTaskConsumerManager(final int numConsumers, final int queueSize) {
        if(queueSize<1)
            mTasks = new LinkedBlockingQueue<>();
        else
            mTasks = new ArrayBlockingQueue<>(queueSize);
        startConsumers(numConsumers);

    }

    public SimpleTaskConsumerManager(final int numConsumers) {
        mTasks = new LinkedBlockingQueue<>();
        startConsumers(numConsumers);

    }

    private void startConsumers(final int numConsumers) {
        mSimpleTaskConsumers = new SimpleTaskConsumer[numConsumers];
        threads=new Thread[numConsumers];
        for (int i = 0; i < numConsumers; i++) {
            mSimpleTaskConsumers[i] = new SimpleTaskConsumer();
            threads[i] = new Thread(mSimpleTaskConsumers[i]);
            threads[i].start();
        }
    }

    public void addTask(final Task task) {
        if (mConsumersShouldDie) return; 

        try {
            mTasks.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void removeAllTasks() {
        mTasks.clear();
    }

    public void destroyAllConsumers(final boolean finishCurrentTasks) {
        destroyAllConsumers(finishCurrentTasks, false);
    }

    public void destroyAllConsumers(final boolean finishCurrentTasks,
                                    final boolean blockUntilFinished) {
        if (mConsumersShouldDie) return;
        mConsumersShouldDie = true;

        if (!finishCurrentTasks) removeAllTasks();

        final DieTask dieTask = new DieTask();
        for(final Thread thread:threads){
            try {
                mTasks.put(dieTask);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if (blockUntilFinished) {

            for(final Thread thread:threads){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        
        destroyAllConsumers(false);

        super.finalize();
    }

    public static abstract class Task {

        
        public abstract boolean doTask();
    }

    
    public class DieTask extends Task {

        public boolean doTask() {
            
            return false;
        }
    }

    private class SimpleTaskConsumer implements Runnable {

        @Override
        public void run() {
            int threadId = mNumThreadsAlive++;
            
            do {
                try {
                    final Task task = mTasks.take();
                    if(!task.doTask()){
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (true);

            
            mNumThreadsAlive--;

        }
    }
}
