package e.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by lixiaojian on 2017/7/7.
 * @des Thread.yield()会引起cpu的重新竞争,所有线程,包括自己都会重新竞争CPU
 */
public class ThreadYieldTest extends Thread{

    private static int i = 0;

    private static int flag = 0;

    public ThreadYieldTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            if (i == 30 && this.getName().equals("张三")) {
                flag = 1;
            } else if (i == 30 && this.getName().equals("李四")) {
                flag = 2;
            }
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 30; j++) {
            ThreadYieldTest yt1 = new ThreadYieldTest("张三");
            ThreadYieldTest yt2 = new ThreadYieldTest("李四");
            yt1.start();
            yt2.start();
            Thread.sleep(3 * 1000);
            System.out.println(flag + "====" + j);
        }
    }
}
