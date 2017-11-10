package thinkingJava.concurrency.callable;

import java.util.concurrent.Callable;

/**
 * Created by lixiaojian on 2017/6/6.
 */
public class TaskWithResult implements Callable<String>{

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of taskWithResult" + id;
    }
}
