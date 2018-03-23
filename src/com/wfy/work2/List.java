package com.wfy.work2;

import java.util.ArrayList;

public class List {
    ArrayList data = new ArrayList();

    public void append(Object obj) {
        data.add(obj);
    }

    public void remove(int index) {
        data.remove(index);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
