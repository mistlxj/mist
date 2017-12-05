package interview.other;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程循环交替执行
 */
public class MultiThreadLoopPrint {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static Condition c3 = lock.newCondition();

    private static AtomicInteger cnt = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    actionA();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    actionB();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    actionC();
                }
            }
        }, "C").start();
    }

    private static void actionA() {
        try {
            lock.lock();
            if (cnt.get() % 3 != 0) {
                c1.await();
            }
            System.out.print(Thread.currentThread().getName() + " ");
            cnt.incrementAndGet();
            c2.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void actionB() {
        try {
            lock.lock();
            if (cnt.get() % 3 != 1) {
                c2.await();
            }
            System.out.print(Thread.currentThread().getName() + " ");
            cnt.incrementAndGet();
            c3.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void actionC() {
        try {
            lock.lock();
            if (cnt.get() % 3 != 2) {
                c3.await();
            }
            System.out.print(Thread.currentThread().getName() + " ");
            cnt.incrementAndGet();
            c1.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
