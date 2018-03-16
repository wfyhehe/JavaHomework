package com.wfy.work1;

public class Example2 {
    public static void main(String[] args) {
        System.out.println("Break at 5:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break;
            }
            System.out.println(i);
        }

        System.out.println("Continue at 5:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.println(i);
        }
    }
}
