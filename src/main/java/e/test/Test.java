package e.test;

import com.alibaba.fastjson.JSON;
import com.mmc.tools.json.JSONUtil;
import com.mmc.tools.util.ReflectUtil;

import java.lang.reflect.Method;

/**
 * Created by lixiaojian on 2017/7/27.
 */
public class Test {

    public static void main(String args[]) {


        Method method = ReflectUtil.getMethod(Test.class, "setValue");
        Method method1 = ReflectUtil.getMethod(Test.class, "test1");

        String json = "{\"code\":0,\"error\":true,\"re\":[{\"id\":321},{\"id\":123},{\"id\":222}]}";
        Object t = JSON.parseObject(json, method.getGenericParameterTypes()[0]);
        System.out.println(JSONUtil.json(t));
        System.out.println(t.getClass());


        String json1 = "[{\"id\":321},{\"id\":123},{\"id\":222}]";
        t = JSON.parseObject(json, method.getGenericParameterTypes()[0]);
        System.out.println(JSONUtil.json(t));
        System.out.println(t.getClass());
    }

}
