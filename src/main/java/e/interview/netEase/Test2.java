package e.interview.netEase;
public class Test2 {

    public static void main(String[] args) {
        Test2 t2 = new Test2();
        t2.test();
    }

    public void test() {
        int i = 99;
        Temp tmp = new Temp();
        tmp.i = 30;
        testTest(tmp, i);
        System.out.println(tmp.i);
    }

    private void testTest(Temp tmp, int i) {
        i = 0;
        Temp t = new Temp();
        tmp = t;
        System.out.println(tmp.i + " " + i);
    }

    class Temp {
        int i = 10;
    }

}
