package thinkingJava.concurrency.basic;

/**
 * Created by lixiaojian on 2017/6/6.
 */
public class LiftOff implements Runnable{

    protected int cntDown = 10;
    private static int taskCnt = 0;
    private final int id = taskCnt++;

    public LiftOff(){}

    public LiftOff(int cntDown) {
        this.cntDown = cntDown;
    }

    public String getStatus() {
        return "#" + id + "(" + (cntDown > 0 ? cntDown : "LiftOff!" ) + "),";
    }

    @Override
    public void run() {
        while (cntDown-- >0) {
            System.out.println(getStatus());
            Thread.yield();
        }
    }
}
