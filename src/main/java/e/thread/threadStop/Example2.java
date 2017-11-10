package e.thread.threadStop;

/**
 * Created by zhuxuemei on 17/7/9.
 *
 * 当线程等待某些事件发生而被阻塞，又会发生什么？此时，线程被阻塞，它便不能核查共享变量，也就不能停止。
 * 如果线程被阻塞，正确的停止线程方式是设置共享变量，并调用interrupt()（注意变量应该先设置）。
 * 如果线程没有被阻塞，这时调用interrupt()将不起作用.
 */

class Example2 extends Thread {
    static volatile boolean stop = false;
    public static void main( String args[] ) throws Exception {
        Example2 thread = new Example2();
        System.out.println( "Starting thread..." );
        thread.start();
        Thread.sleep( 3000 );
        System.out.println( "Asking thread to stop..." );
        stop = true;         //如果线程阻塞，将不会检查此变量
        thread.interrupt();
        Thread.sleep( 3000 );
        System.out.println( "Stopping application..." );
    }

    public void run() {
        while (!stop ) {
            System.out.println( "Thread running..." );
            try {
                Thread.sleep( 1000 );
            } catch ( InterruptedException e ) {
                System.out.println( "Thread interrupted..." );
            }
        }
        System.out.println( "Thread exiting under request..." );
    }
}
