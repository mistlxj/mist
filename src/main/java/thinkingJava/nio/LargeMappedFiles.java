package thinkingJava.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {
    private static int length = 0x1fffffff;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new RandomAccessFile("abc.txt", "rw").getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);

        for (int i=0;i<length;i++) {
            mbb.put((byte) 'X');
        }

        for (int i= length >> 1;i< (length >> 1) + 6;i++) {
            System.out.print((char)mbb.get(i));
        }
        System.out.println();
        for (int i= 0;i< 16;i++) {
            System.out.print((char)mbb.get(i));
        }
    }
}
