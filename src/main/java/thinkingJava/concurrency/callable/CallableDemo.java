package thinkingJava.concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lixiaojian on 2017/6/6.
 */
public class CallableDemo {

    public static void main(String[] args)  {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> res = new ArrayList<Future<String>>();
        for (int i=0;i<10;i++) {
            res.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : res) {
            if(fs.isDone()) {
                try {
                    System.out.println(fs.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    exec.shutdown();
                }
            }
        }
    }
}
