package thinkingJava.dynamicProxy.cglib;

/**
 * Created by lixiaojian on 2017/4/12.
 */
public class Test {

    public static void main(String[] args) {
        // base为生成的增强过的目标类
        Base base1 = Factory.getProxyInstance(new MyCglibProxy(true));
        base1.add();
        base1.query();
        System.out.println("-------------------------------------------");
//        Base base2 = Factory.getProxyInstance(new MyCglibProxy(false));
//        base2.add();
//        base2.query();
//        System.out.println("-------------------------------------------");
//        Base base3 = Factory.getProxyInstanceByFilter(new MyCglibProxy(true));
//        base3.add();
//        base3.query();
//        System.out.println("-------------------------------------------");
//        Base base4 = Factory.getProxyInstanceByFilter(new MyCglibProxy(false));
//        base4.add();
//        base4.query();
    }
}
