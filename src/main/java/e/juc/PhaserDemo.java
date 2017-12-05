package e.juc;

import java.util.concurrent.Phaser;

/*
* 一种任务可以分为多个阶段，对于每个阶段，多个线程可以并发进行，
* 但是希望保证只有前面一个阶段的任务完成之后才能开始后面的任务。
* */
public class PhaserDemo {
    public static void main(String[] args) {
        int parties = 3;
        final int phases = 4;
        final Phaser phaser  = new Phaser(parties) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("====== Phase : " + phase + " ======");
                return registeredParties == 0;
            }
        };

        for(int i = 0; i < parties; i++) {
            final int threadId = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < phases; j++) {
                        System.out.println(String.format("Thread %s, phase %s", threadId, j));
                        phaser.arriveAndAwaitAdvance();
                    }
                }
            });
            thread.start();
        }
    }
}
