package com.wfy.review;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by wfy on 2018/5/13, good luck.
 */
public class Test3 {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];
        try {
            FileInputStream fis = new FileInputStream("test.txt");
            int bytes = fis.read(buffer, 0, 2048);
            String str = new String(buffer, 0, bytes);
            System.out.println(str);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
