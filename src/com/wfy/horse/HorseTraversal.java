package com.wfy.horse;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class HorseTraversal {
    private static final Color backgroundColor = Color.LIGHT_GRAY;
    private static final Color gridColor = Color.DARK_GRAY;
    private static final Color routeColor = Color.BLACK;
    private static final Color horseColor = Color.RED;
    private static int[][] diffEntries = new int[][]{
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
    };
    private int rows;
    private int cols;
    private boolean succeeded = false;
    private boolean[][] board;
    private int traversedCount = 0;
    private Stack<Map.Entry<Integer, Integer>> path;
    private int startX = 0;
    private int startY = 0;
    private boolean showPathOrNot = false;

    public HorseTraversal(int col, int row) {
        this.cols = col;
        this.rows = row;
        board = new boolean[row][col];
        path = new Stack<>();
        startX = row / 2;
        startY = col / 2;
    }

    public HorseTraversal(int col, int row, boolean showPathOrNot) {
        this(col, row);
        this.showPathOrNot = showPathOrNot;
    }

    public static void main(String[] args) {
        for (int i = 4; i < 20; i++) {
            // 4x 5x 6√ 7? 8√ 9? 10√ 11? 12√ 13? 14√ 15? 16? 17? 18√ 19?
            new HorseThread(i, i).start();
        }
//        new HorseThread(10, 11, true).start();
    }

    public boolean canTraverse() {
        jump(startX, startY);
        return succeeded;
    }

    private void jump(int i, int j) {
        if (succeeded) {
            return;
        }
        if (i == startX & j == startY && traversedCount == rows * cols) {
            succeeded = true;
            if (this.showPathOrNot) {
                showPath();
            }
            return;
        }
        if (!inBoard(i, j) || board[i][j]) { // outOfBoard or visited
            return;
        }
        int[][] diffEntriesCopy = diffEntries.clone();
        Arrays.sort(diffEntriesCopy, (o1, o2) -> { // 可以选择的两个点，o1[0]表示x，o1[1]表示y
            if (!inBoard(i + o2[0], j + o2[1]) || board[i + o2[0]][j + o2[1]]) {
                return -1; // 优先把OutOfBounds的选择排除掉
            }
            if (!inBoard(i + o1[0], j + o1[1]) || board[i + o1[0]][j + o1[1]]) {
                return 1; // 优先把OutOfBounds的选择排除掉
            }
            int choices1 = 0;
            int choices2 = 0;
            for (int[] entry : diffEntries) { // 对于o1, o2，他们又有8个不同的选择，遍历每一个选择
                int o1I = i + o1[0] + entry[0]; // o1->next的i(x)坐标
                int o1J = j + o1[1] + entry[1]; // o1->next的j(y)坐标
                int o2I = i + o2[0] + entry[0]; // ...
                int o2J = j + o2[1] + entry[1];
                if ((inBoard(o1I, o1J) && !board[o1I][o1J])) choices1++; // 对o1的下一个可走位置计数
                if ((inBoard(o2I, o2J) && !board[o2I][o2J])) choices2++;
            }
            if (choices1 != choices2) return choices1 - choices2; // 优先选择可走位置数量少的
            int distanceFromCenter1 = Math.abs(o1[0] - rows / 2) + // 如果可走位置数相等，比较他们距中心的距离
                    Math.abs(o1[1] - cols / 2);
            int distanceFromCenter2 = Math.abs(o2[0] - rows / 2) +
                    Math.abs(o2[1] - cols / 2);
            return distanceFromCenter2 - distanceFromCenter1; // 选择离中心最远的位置前进
        });
        for (int[] entry : diffEntriesCopy) {
            board[i][j] = true;
            traversedCount++;
            path.push(new AbstractMap.SimpleEntry<>(i, j));
            jump(i + entry[0], j + entry[1]);
            board[i][j] = false;
            traversedCount--;
            path.pop();
        }
    }

    private boolean inBoard(int i, int j) {
        return i >= 0 && j >= 0 && i < rows && j < cols;
    }

    private void showPath() {
        StdDraw.setXscale(0, rows + 1);
        StdDraw.setYscale(0, cols + 1);
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledRectangle(1, 1, rows, cols);
        StdDraw.setPenColor(gridColor);
        for (int i = 1; i <= rows; i++) {
            StdDraw.line(i, 1, i, cols);
        }
        for (int i = 1; i <= cols; i++) {
            StdDraw.line(1, i, rows, i);
        }
        StdDraw.setPenColor(horseColor);
        StdDraw.filledCircle(startX + 1, startY + 1, 0.1);
        StdDraw.setPenColor(routeColor);
        Integer prevX = startX + 1;
        Integer prevY = startY + 1;
        for (Map.Entry<Integer, Integer> pathEntry : path) {
            Integer x = pathEntry.getKey() + 1;
            Integer y = pathEntry.getValue() + 1;
            StdDraw.line(prevX, prevY, x, y);
            System.out.printf("(%d, %d)\n", x, y);
            prevX = x;
            prevY = y;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StdDraw.line(prevX, prevY, startX + 1, startY + 1);
    }

}
