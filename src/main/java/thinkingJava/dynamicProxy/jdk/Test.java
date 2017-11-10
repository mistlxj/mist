package thinkingJava.dynamicProxy.jdk;

import java.lang.reflect.Proxy;

/**
 * Created by lixiaojian on 2017/4/12.
 */
public class Test {

    public static void main(String[] args) {
        /*
        * jdk动态代理的应用前提，必须是目标类基于统一的接口。如果没有上述前提，jdk动态代理不能应用
        * */
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance
                (SomeMethods.class.getClassLoader(),new Class[]{SomeMethods.class},new MethodSelector(new SomeMethodsImpl()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("mist");
        proxy.boring3();
    }
}
