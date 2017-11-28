package thinkingJava.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLockDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileChannel fc = new FileOutputStream("file.txt").getChannel();
        FileLock fLock = fc.tryLock();
        if (fLock != null) {
            System.out.println("Locked file");
            TimeUnit.MILLISECONDS.sleep(100);
            fLock.release();
            System.out.println("Released Lock");
        }
        fc.close();
    }
}
