package thinkingJava.concurrency.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lixiaojian on 2017/6/6.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i=0;i<5;i++) {
            exec.submit(new LiftOff());
        }
        exec.shutdown();
    }
}
