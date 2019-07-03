package e.juc;


import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch是让某一线程等待多个线程的状态，然后该线程被唤醒
 * CyclicBarrier【循环内存屏障】是指，让多个线程互相等待某一事件的发生，然后同时被唤醒。
 *
 * 每个线程都不会在其它所有线程执行await()方法前继续执行，而等所有线程都执行await()方法后所有线程的等待都被唤醒从而继续执行
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int totalThreadNum = 5;
        final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------");
            }
        });
        for (int i=0;i<totalThreadNum;i++) {
            final String threadName = "Thread" + i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                }
            }).start();
        }
    }
}
