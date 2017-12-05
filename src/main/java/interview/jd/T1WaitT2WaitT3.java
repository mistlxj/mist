package interview.jd;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
* 线程t1等待线程t2执行等待线程t3执行
* */
public class T1WaitT2WaitT3 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 run");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 run");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 run");
            }
        });

        t1.start();

        CyclicBarrier barrier2 = new CyclicBarrier(1, t2);
        CyclicBarrier barrier3 = new CyclicBarrier(1, t3);
        barrier3.await();
        barrier2.await();

    }
}
