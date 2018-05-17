package com.wfy.work6.nttt;

enum Slot {
    X("X"), O("O"), NONE("-");
    
    private String name;
    
    Slot(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}