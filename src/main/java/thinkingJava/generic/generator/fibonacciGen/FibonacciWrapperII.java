package thinkingJava.generic.generator.fibonacciGen;

import java.util.Iterator;

/**
 * Created by lixiaojian on 2017/8/17.
 * 对象的适配器模式，持有Iterator的实例，然后去完成相应的功能（实现迭代）
 */
public class FibonacciWrapperII extends Fibonacci{

    private  Integer cnt;

    public FibonacciWrapperII(Integer cnt) {
        this.cnt = cnt;
    }

    public Iterator<Integer> genIterator() {
        Iterator<Integer> iterator = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return cnt > 0;
            }

            @Override
            public Integer next() {
                cnt--;
                int fib = FibonacciWrapperII.this.next();
                return fib;
            }

            @Override
            public void remove() {

            }
        };
        return iterator;
    }

    public static void main(String[] args) {
        FibonacciWrapperII fibonacciWrapperII = new FibonacciWrapperII(10);
        Iterator<Integer> iterator = fibonacciWrapperII.genIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

}
