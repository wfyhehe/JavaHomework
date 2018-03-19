package com.wfy.wander;

/**
 * Created by wfy on 18-3-17, good luck.
 */
public class City {
    private int[][] map;
    private int size;

    public City(int size) {
        this.size = size;
        this.map = new int[size][size];
    }

    public int[][] getMap() {
        return map;
    }

    public int getSize() {
        return size;
    }

    public void setVisited(Position p) {
        map[p.x][p.y] = 1;
    }

    public void clear() {
        map = new int[size][size];
    }

    public boolean northClear(Position position) {
        return map[position.x][position.y + 1] == 0;
    }

    public boolean eastClear(Position position) {
        return map[position.x + 1][position.y] == 0;
    }

    public boolean southClear(Position position) {
        return map[position.x][position.y - 1] == 0;
    }

    public boolean westClear(Position position) {
        return map[position.x - 1][position.y] == 0;
    }
}
