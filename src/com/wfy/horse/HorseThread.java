package com.wfy.horse;

public class HorseThread extends Thread {
    private int size;
    private boolean showPath;

    public HorseThread(int size, boolean showPath) {
        this.size = size;
        this.showPath = showPath;
    }

    public HorseThread(int size) {
        this.size = size;
        this.showPath = false;
    }

    @Override
    public void run() {
        HorseTraversal ht = new HorseTraversal(size, showPath);
        long start = System.currentTimeMillis();
        boolean canTraverse = ht.canTraverse();
        long end = System.currentTimeMillis();
        System.out.printf("%d: %s\nTime span: %dms\n"
                , size, canTraverse ? "Succeeded" : "Failed", (end - start));
    }
}
