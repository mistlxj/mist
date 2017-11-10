package thinkingJava.dynamicProxy.cglib;

/**
 * Created by lixiaojian on 2017/4/12.
 * 目标类
 */
public class Base {

    private String name;

    public Base(String name) {
        this.name = name;
    }

    public Base() {}

    //新增，只有授权才能做写操作
    public void add() {
        System.out.println("invoke base add()");
    }

    //查询 query 方法不做权限控制，所有人都可以访问
    public void query() {
        System.out.println("invoke base query()");
    }
}
