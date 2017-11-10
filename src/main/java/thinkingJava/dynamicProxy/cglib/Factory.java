package thinkingJava.dynamicProxy.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

/**
 * Created by lixiaojian on 2017/4/12.
 */
public class Factory {

    /**
     * 工厂类，生成增强过的目标类（已加入切入逻辑）
     */
    public static Base getProxyInstance(MyCglibProxy proxy) {
        Enhancer enhancer = new Enhancer();
        //1111进行代理
        enhancer.setSuperclass(Base.class);
        //回调方法的参数为代理类对象CglibProxy，最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
        enhancer.setCallback(proxy);

        //222生成代理实例
        // 此刻，base不是单纯的目标类，而是增强过的目标类
        Base base = (Base) enhancer.create();
        return base;
    }

    public static Base getProxyInstanceByFilter(MyCglibProxy proxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Base.class);
        /**
         * 1,定义所使用的拦截器，其中NoOp.INSTANCE是CGlib所提供的实际是一个没有任何操作的拦截器。
         * 2,所使用的拦截器，和CallbackFilter里面的顺序一致,本例中MyProxyFilter中返回0代表走proxy代理拦截，
         *   返回1表示走NoOp.INSTANCE拦截，即不做任何拦截操作。
         */
        enhancer.setCallbacks(new Callback[]{proxy, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new MyProxyFilter());
        return (Base) enhancer.create();
    }
}