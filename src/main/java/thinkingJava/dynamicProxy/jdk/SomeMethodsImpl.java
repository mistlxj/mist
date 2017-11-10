package thinkingJava.dynamicProxy.jdk;

/**
 * Created by lixiaojian on 2017/4/12.
 */
public class SomeMethodsImpl implements SomeMethods {
    @Override
    public void boring1() {
        System.out.println("boring1");
    }

    @Override
    public void boring2() {
        System.out.println("boring2");
    }

    @Override
    public void interesting(String args) {
        System.out.println("interesting" + args);
    }

    @Override
    public void boring3() {
        System.out.println("boring3");
    }
}
