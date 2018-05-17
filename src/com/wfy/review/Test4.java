package com.wfy.review;

import java.io.File;

/**
 * Created by wfy on 2018/5/13, good luck.
 */
public class Test4 {
    public static void main(String[] args) {
//        File f = new File("test.txt");
        File f = new File("sample.dat");
        File path = new File(".");
//        System.out.println(f.length());
        System.out.println(path.isDirectory());
        for (File file : path.listFiles()) {
            System.out.println(file.getName());
        }
    }
}
