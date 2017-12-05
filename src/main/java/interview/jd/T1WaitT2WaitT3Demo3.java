package interview.jd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T1WaitT2WaitT3Demo3 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                action1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                action2();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                action3();
            }
        });

       t1.start();t2.start();t3.start();
    }

    private static void action1()  {
        lock.lock();
        try {
            condition1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1 run");
        lock.unlock();
    }

    private static void action2() {
        lock.lock();
        try {
            condition2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t2 run");
        condition1.signal();
        lock.unlock();
    }

    private static void action3() {
        lock.lock();
        System.out.println("t3 run");
        condition2.signal();
        lock.unlock();
    }

}
