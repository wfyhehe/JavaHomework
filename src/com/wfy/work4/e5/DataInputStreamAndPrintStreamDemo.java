package com.wfy.work4.e5;

import java.io.*;

public class DataInputStreamAndPrintStreamDemo {
    public static void main(String[] args) {
        int count;
        byte input[] = new byte[256];
        DataInputStream dis = new DataInputStream(System.in);
//        BufferedInputStream bis = new BufferedInputStream(dis);
        DataOutputStream dos = new DataOutputStream(System.out);
//        BufferedOutputStream bos = new BufferedOutputStream(dos);
        PrintStream p = new PrintStream(System.out);
        try {
//            count = bis.read(input);
            count = dis.read(input);
            p.println("BufferedOutPutStream输出:");
//            bos.write(input, 0, count);
            dos.write(input, 0, count);
//            bos.flush();
            dos.flush();
            p.println("PrintStream输出:");
            p.write(input, 0, count);
            p.close();
            dis.close();
//            bis.close();
            dos.close();
//            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}