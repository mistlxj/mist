package thinkingJava.generic.patameterizedType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lixiaojian on 2017/8/10.
 * @des 利用ParameterizedType获取泛型化类型的参数
 */
public class TestDemo {
    public static void main(String[] args) {
        Class paramClass = Object.class;
        Type[] types = Base.class.getGenericInterfaces();
        for (Type t : types) {
            if (t instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) t;
                if (type.getRawType() == BaseInterface.class) {
                    Type[] paramsType = type.getActualTypeArguments();
                    if (null != paramsType && paramsType.length > 1 && paramsType[1] instanceof Class) {
                        paramClass = (Class) paramsType[1];
                    }
                }
            }
        }
        System.out.println(paramClass.getName());
    }
}
