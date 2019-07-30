package jdk.java7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * try-with-resource 语法
 */
public class TryWithResDemo {

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(new File("test"))) {
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
