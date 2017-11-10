package e.thread.threadStop;

/**
 * Created by lixiaojian on 2017/7/6.
 * @Desc 两个线程，利用共享变量mutex 来实现一个线程退出
 */
public class TwoThreadsMonitor {
    private static volatile boolean  mutex = false;

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new T1());
        t1.start();
        Thread t2 = new Thread(new T2());
        t2.start();
        Thread.sleep(3 * 1000); //等待t1,t2线程执行
        System.out.println(t1.getState()); //t1循环多次，任然在执行，状态 Runnable
        System.out.println(t2.getState()); //t2已经执行完毕
        t1.join();
        t2.join(); //确保线程执行完毕
        System.out.println(t1.getState());
        System.out.println(t2.getState()); //此时线程状态肯定是 Terminated
    }

    static class T1 implements Runnable {
        public void run() {
            int cnt = 10;
            while (cnt > 0) {
                mutex = true;
                System.out.println(mutex);
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cnt--;
            }
        }
    }

    static class T2 implements Runnable {
        public void run() {
            while (!mutex) { //s监听共享变量，一直监听，知道状态改变，线程退出
                int i = 0;
                i++;
            }
            System.out.println("Thread T2 exit()"); //程序执行到此的话，说明T2已经执行完毕了
        }
    }
}
