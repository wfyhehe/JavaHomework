package com.wfy.work6.nttt;

public class Board {
    private Slot[][] board = new Slot[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Slot.NONE;
            }
        }
    }

    public Slot get(int x, int y) {
        return board[y - 1][x - 1];
    }

    public void set(int x, int y, Slot slot) {
        board[y - 1][x - 1] = slot;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 3; x++) {
                result.append(get(x, y));
            }
            result.append("\n");
        }
        return result.toString();
    }
}