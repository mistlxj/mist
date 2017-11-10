package thinkingJava.dynamicProxy.cglib.mixin;

import org.springframework.cglib.proxy.Mixin;

/**
 * Created by lixiaojian on 2017/4/13.
 * 除了代理类外，CGLIB通过提供一个对java.lang.reflect.Proxy的drop-in替代来实现对对接口的代理。
 * CGLIB的代理包也对net.sf.cglib.proxy.Mixin提供支持。
 * 基本上，它允许多个对象被绑定到一个单个的大对象。在代理中对方法的调用委托到下面相应的对象中。
 */
public class Test {
    public static void main(String[] args) {
        Class[] interfaces = new Class[] { MyInterfaceA.class,
                MyInterfaceB.class };
        Object[] delegates = new Object[] { new MyInterfaceAImpl(),
                new MyInterfaceBImpl() };
        Object obj = Mixin.create(interfaces, delegates);
        MyInterfaceA myA = (MyInterfaceA) obj;
        myA.methodA();
        MyInterfaceB myB = (MyInterfaceB) obj;
        myB.methodB();
    }
}
