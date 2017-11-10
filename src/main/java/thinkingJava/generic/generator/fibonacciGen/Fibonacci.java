package thinkingJava.generic.generator.fibonacciGen;

import thinkingJava.generic.generator.Generator;

/**
 * Created by zhuxuemei on 17/8/13.
 */
public class Fibonacci implements Generator<Integer> {
    @Override
    public Integer next() {
        return fib(cnt++);
    }

    private int cnt = 0;

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        Fibonacci fibGen = new Fibonacci();
        for (int i=0;i<10;i++) {
            System.out.print(fibGen.next() + " ");
        }
    }
}
