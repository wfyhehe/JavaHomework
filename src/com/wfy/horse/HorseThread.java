package com.wfy.horse;

public class HorseThread extends Thread {
    private int rows;
    private int cols;
    private boolean showPath;

    public HorseThread(int row, int col, boolean showPath) {
        this.rows = row;
        this.cols = col;
        this.showPath = showPath;
    }

    public HorseThread(int row, int col) {
        this.rows = row;
        this.cols = col;
        this.showPath = false;
    }

    @Override
    public void run() {
        HorseTraversal ht = new HorseTraversal(rows, cols, showPath);
        long start = System.currentTimeMillis();
        boolean canTraverse = ht.canTraverse();
        long end = System.currentTimeMillis();
        System.out.printf("%d*%d: %s\nTime span: %dms\n"
                , rows, cols, canTraverse ? "Succeeded" : "Failed", (end - start));
    }
}
