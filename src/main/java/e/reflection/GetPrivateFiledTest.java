package e.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lixiaojian on 2017/7/19.
 * @des 利用反射获取类的私有属性（静态和非静态），可以指定属性名称获取
 */
public class GetPrivateFiledTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        //
        Object res = getFieldsByReflection(PrimitiveClass.class, "tempMap");
        if (res instanceof Map) {
            res = (HashMap<String,Object>) res;
            for (Map.Entry e : ((HashMap<String, Object>) res).entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
        }
        getFieldsByReflection(PrimitiveClass.class);

    }

    private static void getFieldsByReflection(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (!f.isAccessible()) {
                f.setAccessible(true);
                System.out.println(f.getName() + " = " + f.get(clazz.newInstance()));
            }
        }
    }

    private static Object getFieldsByReflection(Class<?> clazz, String filedName) throws NoSuchFieldException,
            IllegalAccessException {
        Field field = clazz.getDeclaredField(filedName);
        System.out.println(Modifier.isStatic(field.getModifiers()));
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Object res = field.get(clazz);
        return res;
    }

}
