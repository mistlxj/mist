package e.thread;

/**
 * Created by zhuxuemei on 17/7/9.
 * 当线程被阻塞时,Thread.sleep(),Thread.join(),Object.wait()时,调用interrupt()方法可以跑出异常,使线程逃离阻塞状态
 */
public class ThreadInterruptTest1 {

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
            //死循环执行打印"I am running!" 和做消耗时间的浮点计算
            try {
                while (true) {
                    System.out.println("I am running!");

                    for (int i = 0; i < 900000; i++) {
                        d = d + (Math.PI + Math.E) / d;
                    }
                    //休眠一断时间,中断时会抛出InterruptedException
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println("ATask.run() interrupted!");
            }
            //*********  如果此处还有代码执行的话,将会继续执行,线程只是,逃离了阻塞状态,并没有退出!!!!
        }
    }
}
