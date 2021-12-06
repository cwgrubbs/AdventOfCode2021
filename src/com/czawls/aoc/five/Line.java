package com.czawls.aoc.five;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private final List<Coordinate> coordinateList = new ArrayList<>();

    public Line(Coordinate start, Coordinate end) {
        if (start.getX() == end.getX()) { //rows
            if (start.getY() >= end.getY()) {
                for (int i = end.getY(); i <= start.getY(); i++) {
                    coordinateList.add(new Coordinate(start.getX(), i));
                }
            } else {
                for (int i = start.getY(); i <= end.getY(); i++) {
                    coordinateList.add(new Coordinate(start.getX(), i));
                }
            }
        } else if (start.getY() == end.getY()) { //cols
            if (start.getX() >= end.getX()) {
                for (int i = end.getX(); i <= start.getX(); i++) {
                    coordinateList.add(new Coordinate(i, start.getY()));
                }
            } else {
                for (int i = start.getX(); i <= end.getX(); i++) {
                    coordinateList.add(new Coordinate(i, start.getY()));
                }
            }
        } else { //45 deg diags
            if (start.getX() <= end.getX() && start.getY() <= end.getY()) { //southeast
                for (int i = start.getX(), j = start.getY(); i <= end.getX(); i++, j++) {
                    coordinateList.add(new Coordinate(i, j));
                }
            } else if (start.getX() > end.getX() && start.getY() <= end.getY()) { //southwest
                for (int i = start.getX(), j = start.getY(); i >= end.getX(); i--, j++) {
                    coordinateList.add(new Coordinate(i, j));
                }
            } else if (start.getX() <= end.getX() && start.getY() > end.getY()) { //northeast
                for (int i = start.getX(), j = start.getY(); i <= end.getX(); i++, j--) {
                    coordinateList.add(new Coordinate(i, j));
                }
            } else { //northwest
                for (int i = start.getX(), j = start.getY(); i >= end.getX(); i--, j--) {
                    coordinateList.add(new Coordinate(i, j));
                }
            }
        }
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }
}
