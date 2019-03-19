package e.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lixiaojian on 2018/6/19.
 */
public class CyclicBarrierTest {
     static CyclicBarrier barrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("inner thread run " + Thread.currentThread().getName());
            }
        }).start();

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("outer thread run " + Thread.currentThread().getName());
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println("con thread run " + Thread.currentThread().getName());
        }
    }
}
