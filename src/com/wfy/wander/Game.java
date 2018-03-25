package com.wfy.wander;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;

/**
 * Created by wfy on 18-3-17, good luck.
 */
public class Game {
    private static final Color backgroundColor = Color.LIGHT_GRAY;
    private static final Color lineColor = Color.CYAN;
    private static final Color successColor = Color.GREEN;
    private static final Color failedColor = Color.RED;
    private Position start;
    private City city;
    private int maxRetry;
    private int step;
    private int totalSucceededSteps;
    private int totalFailedSteps;
    private Random random = new Random();

    private Game(City city, int maxRetry) {
        this.city = city;
        this.maxRetry = maxRetry;
        this.start = new Position(city.getSize() / 2, city.getSize() / 2);
    }

    public static void main(String[] args) {
        int size = 50;
        StdDraw.setXscale(0, size - 1);
        StdDraw.setYscale(0, size - 1);
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledRectangle(1, 1, size, size);
        StdDraw.setPenColor(lineColor);
        long startTime = System.currentTimeMillis();
        new Game(new City(size), 10).run();
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
    }

    private void run() {
        int success = 0;
        int failed = 0;
        for (int i = 0; i < maxRetry; i++) {
            Position cur = start;
            city.clear();
            city.setVisited(start);
            while (true) {
                try {
                    cur = getNextStep(cur);
                } catch (GameOverException e) {
                    drawPoint(cur, failedColor);
                    failed++;
                    totalFailedSteps += step;
                    step = 0;
                    break;
                } catch (EscapeSucceededException e) {
                    drawPoint(cur, successColor);
                    success++;
                    totalSucceededSteps += step;
                    step = 0;
                    break;
                }
            }
        }
        System.out.println("Succeeded: " + success);
        System.out.println("Failed: " + failed);
        System.out.println("Success rate: " + (double) success / maxRetry);
        System.out.println("Average succeeded steps: " + (double) totalSucceededSteps / success);
        System.out.println("Average failed steps: " + (double) totalFailedSteps / failed);
    }

    private Position getNextStep(Position cur) {
        int direction = random.nextInt(4);
        if (cur.x == 0 || cur.y == 0 || cur.x == city.getSize() - 1 || cur.y == city.getSize() - 1) {
            throw new EscapeSucceededException();
        }
        if (!city.northClear(cur) && !city.eastClear(cur) && !city.southClear(cur) && !city.westClear(cur)) {
            throw new GameOverException();
        }
        switch (direction) {
            case 0: // north
                if (city.northClear(cur)) {
                    step++;
                    Position newPos = new Position(cur.x, cur.y + 1);
                    StdDraw.line(cur.x, cur.y, cur.x, cur.y + 1);
                    city.setVisited(newPos);
                    return newPos;
                }
                return getNextStep(cur);
            case 1: // east
                if (city.eastClear(cur)) {
                    step++;
                    Position newPos = new Position(cur.x + 1, cur.y);
                    StdDraw.line(cur.x, cur.y, cur.x + 1, cur.y);
                    city.setVisited(newPos);
                    return newPos;
                }
                return getNextStep(cur);
            case 2: // south
                if (city.southClear(cur)) {
                    step++;
                    Position newPos = new Position(cur.x, cur.y - 1);
                    StdDraw.line(cur.x, cur.y, cur.x, cur.y - 1);
                    city.setVisited(newPos);
                    return newPos;
                }
                return getNextStep(cur);
            case 3: // west
                if (city.westClear(cur)) {
                    step++;
                    Position newPos = new Position(cur.x - 1, cur.y);
                    StdDraw.line(cur.x, cur.y, cur.x - 1, cur.y);
                    city.setVisited(newPos);
                    return newPos;
                }
                return getNextStep(cur);
        }
        return null;
    }

    private void drawPoint(Position p, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.005);
        StdDraw.point(p.x, p.y);
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(lineColor);
    }

    private class GameOverException extends RuntimeException {
    }

    private class EscapeSucceededException extends RuntimeException {
    }
}
