package e.classLoader;

import com.mmc.tools.json.JSONUtil;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            System.out.println(JSONUtil.json(loader));
            loader = loader.getParent();
        }
    }
}
