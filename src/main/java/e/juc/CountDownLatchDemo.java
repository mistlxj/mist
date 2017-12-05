package e.juc;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 主线程等待全部子线程执行完毕
 * CountDownLatch是让某一线程等待多个线程的状态，然后该线程被唤醒
 * CyclicBarrier【循环内存屏障】是指，让多个线程互相等待某一事件的发生，然后同时被唤醒。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int totalThreadNum = 3;
        long startTime = System.currentTimeMillis();
        final CountDownLatch latch = new CountDownLatch(totalThreadNum);
        for (int i=0;i<totalThreadNum;i++) {
            final String threadName = "Thread" + i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                }
            }).start();
        }
        latch.await(); //等待计数器变为0，主线程开始执行
        long stopTime = System.currentTimeMillis();
        System.out.println("Total times" + (stopTime - startTime));
    }
}
