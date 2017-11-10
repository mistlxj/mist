package e.thread.multiThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by lixiaojian on 2017/7/20.
 * @Des 假设有一个任务队列，实现一个线程必须等到当前线程把当前任务执行完成后才能继续执行下一个任务
 */
public class FutureTaskDemo1 {
    private final static ConcurrentHashMap<String, Future<String>> taskCache = new ConcurrentHashMap<String, Future<String>>();

    static {
        Callable<String> task1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task1";
            }
        };
        FutureTask<String> futureTask1 = new FutureTask<String>(task1);

        Callable<String> task2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task2";
            }
        };
        FutureTask<String> futureTask2 = new FutureTask<String>(task2);

        Callable<String> task3 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task3";
            }
        };
        FutureTask<String> futureTask3 = new FutureTask<String>(task3);

        taskCache.put("task1", futureTask1);
        taskCache.put("task2", futureTask2);
        taskCache.put("task3", futureTask3);
    }

    public static void main(String[] args) {

        for (int i=0;i<3;i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String res = executeTask("task1");
                        System.out.println(res);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        System.out.println("OK");
    }


    private static String executeTask(final String taskName) throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName); //1
//            if (null == future) {
//                Callable<String> task = new Callable<String>() {
//                    @Override
//                    public String call() throws Exception {
//                        System.out.println(Thread.currentThread().getName() + "Step into");
//                        return taskName;
//                    }
//                };
//
//                FutureTask<String> futureTask = new FutureTask<String>(task); //2
//                future = taskCache.putIfAbsent(taskName, futureTask); //3
//                if (null == future) {
//                    future = futureTask;
//                    futureTask.run(); //4
//                }
//            }
            FutureTask<String> futureTask = (FutureTask<String>) future;
            futureTask.run();
            try {
                String res = future.get(); //5
                return res;
            } catch (CancellationException e) {
                taskCache.remove(taskName,future);
            }
        }
    }
}
