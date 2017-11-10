package e.InnerClass;

/**
 * Created by lixiaojian on 2017/3/22.
 * 1，内部类可以访问外部类的变量
 * 2，静态内部类可以直接访问外部类的静态变量，通过实例化外部类，访问其非静态变量
 * 3，只有静态内部类可以定义静态变量
 * 4，匿名内部类需要实现接口或继承父类来起作用
 * 5，局部内部类，一般是放在方法内部，只能访问外部的final变量
 */

public class Out {
    private  int aaa = 12;
    private static int num = 100;

     static class In {
        private   int age = 13;
        public void print() {
            int age = 14;
            System.out.println("局部变量：" + age);
            System.out.println("内部类变量：" + this.age);
            Out oo = new Out();
            System.out.println("外部类变量：" + oo.aaa);
            System.out.println(Out.num);
        }
    }
}
