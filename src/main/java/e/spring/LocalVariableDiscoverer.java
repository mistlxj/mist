package e.spring;

import java.lang.reflect.Method;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * Created by lixiaojian on 2017/3/27.
 * @des 利用spring封装的ASM框架获取方法的参数名称
 */
public class LocalVariableDiscoverer {
    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    /**
     * 获取方法所有参数名
     * @param method
     * @return
     */
    public static String[] getParameterNames(Method method) {
        return parameterNameDiscoverer.getParameterNames(method);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("e.spring.service.UserService");
        Method[] methods = aClass.getDeclaredMethods();
        StringBuilder sb = new StringBuilder();
        for (Method method : methods) {
            sb.append("方法："+method.getName() + " ");
            String[] parameterNames = getParameterNames(method);
            if (parameterNames == null || parameterNames.length <1) {
                sb.append("无参");
            } else {
                sb.append("[");
                for (int i = 0; i < parameterNames.length ; i++) {
                    sb.append(parameterNames[i]);
                    sb.append(",");
                }
                sb.append("]");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
