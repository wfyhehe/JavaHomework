package com.wfy.work2.oneton;

public class Pro implements OneToN {
    @Override
    public int disp(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= n;
        }
        return sum;
    }
}
