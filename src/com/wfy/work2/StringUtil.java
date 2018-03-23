package com.wfy.work2;

import java.util.Scanner;

public class StringUtil {
    public static void main(String[] args) {
        StringUtil su = new StringUtil();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            su.showMenu();
            System.out.println(">>");
            int order = Integer.parseInt(scanner.nextLine());
            switch (order) {
                case 1:
                    System.out.println("字符串比较");
                    System.out.println("请输入第一个字符串");
                    String s11 = scanner.nextLine();
                    System.out.println("请输入第二个字符串");
                    String s12 = scanner.nextLine();
                    int result1 = su.compareString(s11, s12);
                    if (result1 > 0) {
                        System.out.println(s11 + " 更大");
                    } else if (result1 < 0) {
                        System.out.println(s12 + " 更大");
                    } else {
                        System.out.println("两字符串相等");
                    }
                    break;
                case 2:
                    System.out.println("字符串搜索");
                    System.out.println("请输入母字符串");
                    String s21 = scanner.nextLine();
                    System.out.println("请输入您想要搜索的字符串");
                    String s22 = scanner.nextLine();
                    int result2 = su.searchString(s21, s22);
                    if (result2 >= 0) {
                        System.out.printf("%s在%s的index=%s处\n", s22, s21, result2);
                    } else {
                        System.out.println("没有找到该字符串");
                    }
                    break;
                case 3:
                    System.out.println("字符串替换");
                    System.out.println("请输入母字符串");
                    String s31 = scanner.nextLine();
                    System.out.println("请输入您想要替换的旧字符串");
                    String s32 = scanner.nextLine();
                    System.out.println("请输入您想要替换的新字符串");
                    String s33 = scanner.nextLine();
                    String result3 = su.replaceString(s31, s32, s33);
                    System.out.println("替换结果：" + result3);
                    break;
                case 4:
                    System.out.println("字符串截取");
                    System.out.println("请输入母字符串");
                    String s41 = scanner.nextLine();
                    System.out.println("请输入您想要截取的头");
                    int s42 = Integer.parseInt(scanner.nextLine());
                    System.out.println("请输入您想要截取的尾");
                    int s43 = Integer.parseInt(scanner.nextLine());
                    String result4 = su.subString(s41, s42, s43);
                    System.out.println("截取结果：" + result4);
                    break;
                case 5:
                    System.out.println("字符串反转");
                    System.out.println("请输入字符串");
                    String s5 = scanner.nextLine();
                    String result5 = su.reverseString(s5);
                    System.out.println("反转结果：" + result5);
                    break;
                case 6:
                    System.out.println("字符串追加");
                    System.out.println("请输入字符串");
                    String s61 = scanner.nextLine();
                    System.out.println("请输入追加部分");
                    String s62 = scanner.nextLine();
                    String result6 = su.appendString(s61, s62);
                    System.out.println("结果：" + result6);
                    break;
                case 7:
                    System.out.println("字符串拆分");
                    System.out.println("请输入字符串");
                    String s71 = scanner.nextLine();
                    System.out.println("请输入分隔符");
                    String s72 = scanner.nextLine();
                    String[] result7 = su.splitString(s71, s72);
                    System.out.println("结果：");
                    for (String str : result7) {
                        System.out.println(str);
                    }
                    break;
                case 0:
                    System.out.println("bye");
                    return;
                default:
                    System.out.println("请输入0-7的数字");
                    break;
            }

        }
    }

    public void showMenu() {
        System.out.println("1.字符串比较");
        System.out.println("2.字符串搜索");
        System.out.println("3.字符串替换");
        System.out.println("4.字符串截取");
        System.out.println("5.字符串反转");
        System.out.println("6.字符串追加");
        System.out.println("7.字符串拆分");
        System.out.println("0.退出");
    }

    public int compareString(String s1, String s2) {
        return s1.compareTo(s2);
    }

    public int searchString(String pattern, String str) {
        return str.indexOf(pattern);
    }

    public String subString(String str, int from, int to) {
        return str.substring(from, to);
    }

    public String subString(String str, int from) {
        return str.substring(from);
    }

    public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public String appendString(String str, String tail) {
        return str + tail;
    }

    public String[] splitString(String str, String spliter) {
        return str.split(spliter);
    }

    public String replaceString(String str, String oldStr, String newStr) {
        return str.replaceAll(oldStr, newStr);
    }
}
