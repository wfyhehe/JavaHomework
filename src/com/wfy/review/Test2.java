package com.wfy.review;

import java.io.*;

/**
 * Created by wfy on 2018/5/13, good luck.
 */
public class Test2 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("sample.dat");
            DataInputStream dis = new DataInputStream(fis);
            double Pi = dis.readDouble();
            int i = dis.readInt();
            boolean okay = dis.readBoolean();
            char cc = dis.readChar();
            String s = dis.readUTF();
            dis.close();
            fis.close();
            System.out.println(Pi);
            System.out.println(i);
            System.out.println(okay);
            System.out.println(cc);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
