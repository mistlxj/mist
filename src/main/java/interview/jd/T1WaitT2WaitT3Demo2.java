package interview.jd;

/**
 * 利用Thread.join()实现指定线程的监听等待执行
 * action方法加锁，保证其严格按顺序执行。
 */
public class T1WaitT2WaitT3Demo2 {
    public static void main(String[] args) throws InterruptedException {
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

        action(t1, t2, t3);
    }

    private static synchronized void action(Thread t1, Thread t2, Thread t3) throws InterruptedException {
        t3.start();
        t3.join();
        t2.start();
        t2.join();
        t1.start();
    }
}
