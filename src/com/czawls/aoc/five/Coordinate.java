package com.czawls.aoc.five;

public class Coordinate {

    private int x, y, overlapCount;

    public Coordinate(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
        this.overlapCount = 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementOverlapCt() {
        overlapCount++;
    }

    public int getOverlapCount() {
        return overlapCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(Coordinate.class)) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return x == other.x && y == other.y;
    }
}
