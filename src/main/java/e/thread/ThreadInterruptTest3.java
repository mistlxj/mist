package e.thread;

/**
 * Created by zhuxuemei on 17/7/9.
 * 将前面两个列子结合起来,同时利用检查interrupted状态和捕获阻塞线程异常,实现更灵敏精细化的控制,线程退出和逃离阻塞状态
 */
public class ThreadInterruptTest3 {

    public static void main(String[] args) throws InterruptedException {
        //将任务交给一个线程执行
        Thread t = new Thread(new ATask());
        t.start();

        //运行一断时间中断线程
        Thread.sleep(1000);
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
            try {
                //检查程序是否发生中断
                while (!Thread.interrupted()) {
                    System.out.println("I am running!");
                    //111111111111111 before sleep
                    Thread.sleep(20);
                    //222222222222222 after sleep
                    System.out.println("Calculating");
                    for (int i = 0; i < 900000; i++) {
                        d = d + (Math.PI + Math.E) / d;
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("Exiting by Exception");
            }

            System.out.println("ATask.run() interrupted!");
        }
    }


}
