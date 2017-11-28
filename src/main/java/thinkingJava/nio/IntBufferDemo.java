package thinkingJava.nio;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public class IntBufferDemo {
    private static final int size = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(size);
        DoubleBuffer db = bb.asDoubleBuffer();
        db.put(new double[]{12,34,534,564,76,23,5});
        System.out.println(db.get(3));
        db.put(3,100);

        db.flip();
        while (db.hasRemaining()) {
            double re = db.get();
            System.out.println(re);
        }
    }
}
