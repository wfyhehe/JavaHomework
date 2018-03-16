package com.wfy.work1;

public class Example6 {
    public static void main(String[] args) {
        Object[] objects = new Object[5];
        objects[0] = 0;
        objects[1] = 1.5;
        objects[2] = "crap";
        objects[3] = '/';
        objects[4] = true;

        for (Object object : objects) {
            System.out.println(object);
        }
    }
}
