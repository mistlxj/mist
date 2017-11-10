package thinkingJava.dynamicProxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lixiaojian on 2017/4/12.
 * cglib可以实现对非接口的动态代理
 */
public class MyCglibProxy implements MethodInterceptor{

    private Boolean isAuthed;

    public MyCglibProxy(Boolean isAuthed) {
        this.isAuthed = isAuthed;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("调用的方法是：" + method.getName());
        if (isAuthed) {
            // 执行目标类add方法
            //method.invoke(o,args);
            proxy.invokeSuper(o, args);
        } else {
            System.out.println("没有权限访问方法：" + method.getName());
        }

        System.out.println("after invoke real method");
        return null;
    }
}
