package thinkingJava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BufferToTxt {

    public static void main(String[] args) throws IOException {
        FileChannel out = new FileOutputStream("data2.txt").getChannel();
        out.write(ByteBuffer.wrap("hello mist ".getBytes("UTF-16")));
        out.close();


        FileChannel in = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        in.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
