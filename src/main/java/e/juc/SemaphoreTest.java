package e.juc;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 根据一些阀值做访问控制，本demo是演示20个线程并发访问一段程序，但同时最多允许5个线程访问
 */
public class SemaphoreTest {

    private final static Semaphore MAX_SEMA_PHORE = new Semaphore(5);


    public static void main1(String []args) {
        for(int i = 0 ; i < 20 ; i++) {
            final int num = i;
            final Random radom = new Random();
            new Thread() {
                @Override
                public void run() {
                    boolean acquired = false;
                    try {
                        MAX_SEMA_PHORE.acquire();
                        acquired = true;
                        System.out.println("我是线程：" + num + " 我获得了使用权！" + new Date());
                        long time = 1000 * Math.max(1, Math.abs(radom.nextInt() % 10));
                        Thread.sleep(time); //sleep随机时间 模拟线程处理自己的业务逻辑
                        System.out.println("我是线程：" + num + " 我执行完了！" + new Date());
                    }catch(Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(acquired) {
                            MAX_SEMA_PHORE.release();
                        }
                    }
                }
            }.start();
        }
    }
}
