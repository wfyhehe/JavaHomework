package com.wfy.review;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wfy on 2018/5/12, good luck.
 */
public class Test {
    public static void main(int[] sb) {
        double pi = 3.1415;
        int i = 10;
        boolean okay = true;
        char cc = 'J';
        String s = "Java sucks";
        try {
            FileOutputStream fos = new FileOutputStream("sample.dat");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeDouble(pi);
            dos.writeInt(i);
            dos.writeBoolean(okay);
            dos.writeChar(cc);
            dos.writeUTF(s);
            dos.close();
//            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
