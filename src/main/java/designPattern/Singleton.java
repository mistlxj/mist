package designPattern;

/**
 * 静态内部类的单例模式.
 * 当 Singleton 被加载时，其内部类并不会被初始化，
 * 故可以确保当 Singleton 类被载入 JVM 时，不会初始化单例类，
 *
 * 而当 getInstance() 方法调用时，才会加载 Singleton，从而初始化 instance。
 *
 * 同时，由于实例的建立是时在类加载时完成，【类的加载机制保证类只会被加载一次？】
 * 故天生对多线程友好，getInstance() 方法也无需使用同步关键字。
 */
public class Singleton {

    private Singleton(){
        System.out.println("Singleton is create");
    }
    private static class SingletonHolder{
        private static Singleton instance = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

}

