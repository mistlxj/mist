package thinkingJava.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class GetChannel {
    private static final int size = 1024;

    /**
     * 演示通过FileOutputStream，FileInputStream，RandomAccessFile等方式获取通道Channel，
     * 展示了将buffer向通道Channel写数据
     * 展示了buffer从通道Channel读数据
     */
    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("some txt ".getBytes()));
        fc.close();

        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();


        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer bb = ByteBuffer.allocate(size);
        fc.read(bb);
        bb.flip();
        while (bb.hasRemaining()) {
            System.out.print((char) bb.get());
        }

    }
}
