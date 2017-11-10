package thinkingJava.dynamicProxy.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by lixiaojian on 2017/4/13.
 */
public class MyProxyFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if(!"query".equalsIgnoreCase(method.getName())) {
            return 0;
        } else {
            //query 方法不做权限控制，所有人都可以访问
            return 1;
        }
    }
}
