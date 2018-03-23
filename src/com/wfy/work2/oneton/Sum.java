package com.wfy.work2.oneton;

public class Sum implements OneToN {
    @Override
    public int disp(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
