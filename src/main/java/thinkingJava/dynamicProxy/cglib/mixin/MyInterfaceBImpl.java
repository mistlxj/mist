package thinkingJava.dynamicProxy.cglib.mixin;

/**
 * Created by lixiaojian on 2017/4/13.
 */
public class MyInterfaceBImpl implements MyInterfaceB{
    @Override
    public void methodB() {
        System.out.println("MyInterfaceBImpl.methodB()");
    }
}
