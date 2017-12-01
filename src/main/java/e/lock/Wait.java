package e.lock;

import java.util.Date;

public class Wait {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try{
                        System.out.println(new Date() + ",t1 is running");
                        //Wait.class.wait();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(new Date() + ",t1 ended");
            }
        });
        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println(new Date() + ",t2 is running");
                        lock.notify();
                        // Don't use sleep method to avoid confusing
                        for(long i = 0; i < 200000; i++) {
                            for(long j = 0; j < 100000; j++) {}
                        }
                        System.out.println(new Date() + ",t2 released lock");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Don't use sleep method to avoid confusing
                for(long i = 0; i < 200000; i++) {
                    for(long j = 0; j < 100000; j++) {}
                }
                System.out.println(new Date() + ",t2 ended");
            }
        });

        // Don't use sleep method to avoid confusing
        for(long i = 0; i < 200000; i++) {
            for(long j = 0; j < 100000; j++) {}
        }
        t2.start();
    }
}
