package com.example.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Converter {
    public static void convert(String inputPath, String outputPath) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        int size = 4096;
        byte[] buffer = new byte[size];
        try {
            fileInputStream = new FileInputStream(inputPath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(outputPath);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int count_read = 0;
            int len_read = 0;
            while ((len_read = bufferedInputStream.read(buffer)) != -1) {
                ++count_read;
                if (count_read == 1) {
                    // skip the first 8 bytes
                    bufferedOutputStream.write(buffer, 9, len_read - 9);
                } else {
                    bufferedOutputStream.write(buffer);
                }
            }
            long mega_byte_read = count_read * size / (1024 * 1024);
            System.out.printf("read %d MB\n", mega_byte_read);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
