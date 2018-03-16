package com.wfy.work1;

import java.util.Scanner;

public class Example4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入1-12的整数");
            String input = scanner.nextLine().trim();
            try {
                int parsedInput = Integer.parseInt(input);
                int days;
                switch (parsedInput) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        days = 30;
                        break;
                    case 2:
                        days = 28;
                        break;
                    default:
                        throw new NumberFormatException();
                }
                System.out.println(parsedInput + "月有" + days + "天");
                break;
            } catch (NumberFormatException e) {
            }
        }
    }
}
