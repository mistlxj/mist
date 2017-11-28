package thinkingJava.nio;


import java.nio.ByteBuffer;
import java.nio.CharBuffer;


public class UsingBuffer {
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();  //保留position到mark
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset(); //回退position到上次的mark
            buffer.put(c2).put(c1);
        }
        buffer.rewind();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[] data = "sheisvreypretty".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        cb.rewind();
        symmetricScramble(cb);

        bb.slice();
        bb.compact();
    }

}
