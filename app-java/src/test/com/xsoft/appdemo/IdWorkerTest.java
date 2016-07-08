package com.xsoft.appdemo;

import com.xsoft.appdemo.util.IdWorkerDemo1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dengwei06015 on 2016/7/8.
 */
public class IdWorkerTest {
    static class IdWorkThread implements Runnable {
        private Set<Long> set;
        private IdWorkerDemo1 idWorker;

        public IdWorkThread(Set<Long> set, IdWorkerDemo1 idWorker) {
            this.set = set;
            this.idWorker = idWorker;
        }

        @Override
        public void run() {
            while (true) {
                long id = idWorker.nextId();
                if (!set.add(id)) {
                    System.out.println("duplicate:"+ id);
                }
            }
        }
    }

    public static void main(String[] args) {
        Set<Long> set = new HashSet<Long>();
        final IdWorkerDemo1 idWorker1 = new IdWorkerDemo1(0, 0);
        final IdWorkerDemo1 idWorker2 = new IdWorkerDemo1(1, 0);
        Thread t1 = new Thread(new IdWorkThread(set, idWorker1));
        Thread t2 = new Thread(new IdWorkThread(set, idWorker2));
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}