package e.thread;

/**
 * Created by zhuxuemei on 17/7/9.
 * 调用Thread.interrupt()方法可以改变线程的interrupted状态.
 * 本例其实类似于检查线程的状态变量,可以做到让线程退出了,
 * 不过通信方式不是监听共享变量,而是由另外的线程发起调用,改变被调用线程的状态变量的状态值.
 */
public class ThreadInterruptTest2 {

    public static void main(String[] args) throws Exception{
        //将任务交给一个线程执行
        Thread t = new Thread(new ATask());
        t.start();

        //运行一断时间中断线程
        Thread.sleep(100);
        System.out.println("****************************");
        System.out.println("Interrupted Thread!");
        System.out.println("****************************");
        t.interrupt();
        t.join();
        System.out.println("Stop the application");
    }

    static class ATask implements Runnable{
        private double d = 0.0;
        public void run() {
            //检查程序是否发生中断
            while (!Thread.interrupted()) {
                System.out.println("I am running!");
                for (int i = 0; i < 900000; i++) {
                    d = d + (Math.PI + Math.E) / d;
                }
            }
            System.out.println("ATask.run() interrupted!");
        }
    }
}
