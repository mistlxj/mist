package thinkingJava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ChannelCopy {
    private static final int size = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("src/main/src.txt").getChannel();
        FileChannel out = new FileOutputStream("src/main/des.txt").getChannel();

        //1 第一种方法
//        ByteBuffer buffer = ByteBuffer.allocate(size);
//        while (in.read(buffer) != -1) {
//            buffer.flip();
//            out.write(buffer);
//            buffer.clear();
//        }

        //2 第二种方法
        in.transferTo(0, in.size(), out);
        out.transferFrom(in, 0, in.size());

        System.out.println("succeed");
    }
}
