package com.wfy.work1;

import java.util.ArrayList;

public class Example3 {
    public static int[] getDigits(int number) {
        int n = String.valueOf(number).length();
        int[] ret = new int[n];
        while(number > 0) {
            int digit = number % 10;
            number = number / 10;
            ret[--n] = digit;
        }
        return ret;
    }

    public static void main(String[] args) {
        int a = 1678;
        int[] digits = getDigits(a);
        for (int digit : digits) {
            System.out.println(digit);
        }
    }
}
