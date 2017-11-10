package thinkingJava.dynamicProxy.cglib.mixin;

/**
 * Created by lixiaojian on 2017/4/13.
 */
public class MyInterfaceAImpl implements MyInterfaceA{
    @Override
    public void methodA() {
        System.out.println("MyInterfaceAImpl.methodA()");
    }
}
