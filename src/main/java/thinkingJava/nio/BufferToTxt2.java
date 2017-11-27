package thinkingJava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferToTxt2 {

    public static void main(String[] args) throws IOException {
        FileChannel out = new FileOutputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.asCharBuffer().put("hi smallMeiMei");
        out.write(buffer);
        out.close();

        FileChannel in = new FileInputStream("data.txt").getChannel();
        buffer.clear();
        in.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
