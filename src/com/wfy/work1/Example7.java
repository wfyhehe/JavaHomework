package com.wfy.work1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Example7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> integers = new ArrayList<>(10);
        System.out.println("请输入10个数字");
        for (int i = 0; i < 10; i++) {
            int integer = scanner.nextInt();
            integers.add(integer);
        }
        Collections.sort(integers);
        System.out.println("最大值: " + integers.get(integers.size() - 1));
        System.out.println("最小值: " + integers.get(0));
        System.out.println(integers);
    }
}
