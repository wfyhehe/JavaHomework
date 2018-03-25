package com.wfy.horse;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class HorseTraversal {
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

    private int size;
    private boolean flag = false;
    private boolean[][] board;
    private int traversedCount = 0;
    private Stack<Map.Entry<Integer, Integer>> path;
    private int startX = 0;
    private int startY = 0;
    private boolean showPathOrNot = false;

    public HorseTraversal(int size) {
        this.size = size;
        board = new boolean[size][size];
        path = new Stack<>();
        startX = size / 2;
        startY = size / 2;
    }

    public HorseTraversal(int size, boolean showPathOrNot) {
        this(size);
        this.showPathOrNot = showPathOrNot;
    }

    public boolean canTraverse() {
        jump(startX, startY);
        return flag;
    }

    private void jump(int i, int j) {
        if (flag) {
            return;
        }
        if (i == startX & j == startY && traversedCount == size * size) {
            flag = true;
            if(this.showPathOrNot) {
                showPath();
            }
            return;
        }
        if (!inBoard(i, j) || board[i][j]) { // outOfBoard or visited
            return;
        }
        int[][] diffEntriesCopy = diffEntries.clone();
        Arrays.sort(diffEntriesCopy, (o1, o2) -> {
            if (!inBoard(i + o2[0], j + o2[1]) || board[i + o2[0]][j + o2[1]]) {
                return -1;
            }
            if (!inBoard(i + o1[0], j + o1[1]) || board[i + o1[0]][j + o1[1]]) {
                return 1;
            }
            int choices1 = 0;
            int choices2 = 0;
            for (int[] entry : diffEntries) {
                int o1I = i + o1[0] + entry[0];
                int o1J = j + o1[1] + entry[1];
                int o2I = i + o2[0] + entry[0];
                int o2J = j + o2[1] + entry[1];
                if ((inBoard(o1I, o1J) && !board[o1I][o1J])) choices1++;
                if ((inBoard(o2I, o2J) && !board[o2I][o2J])) choices2++;
            }
            if (choices1 != choices2) return choices1 - choices2;
            int distanceFromCenter1 = Math.abs(o1[0] - size / 2) +
                    Math.abs(o1[1] - size / 2);
            int distanceFromCenter2 = Math.abs(o2[0] - size / 2) +
                    Math.abs(o2[1] - size / 2);
            return distanceFromCenter2 - distanceFromCenter1;
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
        return i >= 0 && j >= 0 && i < size && j < size;
    }

    private void showPath() {
        for (Map.Entry<Integer, Integer> pathEntry : path) {
            System.out.printf("(%d, %d)\n", pathEntry.getKey(), pathEntry.getValue());
        }
    }

}
