package thinkingJava.generic.model;

/**
 * Created by lixiaojian on 2017/4/11.
 */
 public class CountInteger {
    private static long counter;

    private final long id = counter++;

    public String toString() {
        return Long.toString(id);
    }

}
