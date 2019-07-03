package e.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lixiaojian on 2018/6/19.
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("=========next================");
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    try {
                        System.out.println("i am thread:" + this.getName() + "first job");
                        cyclicBarrier.await();
                        System.out.println("i am thread:" + this.getName() + "second job");
                        cyclicBarrier.await();
                        System.out.println("i am thread:" + this.getName() + "third job");
                        cyclicBarrier.await();
                        System.out.println("i am thread:" + this.getName() + "finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
