package e.thread;

/**
 * Created by zhuxuemei on 17/7/8.
 * @Desc  jion的语义是,在当前线程的执行轨迹中,持有另一个线程的实例并调用其jion()方法
 *        那么,当前线程将会等待被调用线程执行完毕后,才能继续执行自己的线程轨迹
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");
        T1 t1 = new T1();
        T2 t2 = new T2(t1);
        try {
            t1.start();
            Thread.sleep(2 * 1000);
            t2.start();
            t2.join();
            System.out.println(name + " ended");
        } catch (Exception e) {
            System.out.println(name + " Exception,in running!");
        }

    }


    static class T1 extends Thread {
        public T1() {
            super("Thread-T1");
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " started");
            try {
                for (int i = 0; i < 5; i++) {
                    float pi = Math.abs(new Float("5.25"));
                    System.out.println(name + " loop at " + i);
                    Thread.sleep(2 * 1000);
                }
                System.out.println(name + " ended");
            } catch (Exception e) {
                System.out.println(name + " Exception,in running!");
            }

        }
    }

    static class T2 extends Thread {
        private T1 t1;

        public T2(T1 t1) {
            super("Thread-T2");
            this.t1 = t1;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " started");
            try {
                t1.join();
                System.out.println(name + " ended");
            } catch (InterruptedException e) {
                System.out.println(name + " Exception,in running!");
            }

        }
    }
}
