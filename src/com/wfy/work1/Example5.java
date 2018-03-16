package com.wfy.work1;

import java.util.ArrayList;

public class Example5 {

    public static ArrayList<Integer> getPrimesLessThan(int number) {
        ArrayList<Integer> primes = new ArrayList<>();
        if (number < 3) {
            return primes;
        }
        primes.add(2);
        for (int i = 3; i < number; i += 2) {
            for (int j = 0; j < primes.size(); j++) {
                int prime = primes.get(j);
                if (i % prime == 0) {
                    break;
                } else if (prime * 2 > i) {
                    primes.add(i);
                    break;
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        System.out.println(getPrimesLessThan(100));
    }
}
