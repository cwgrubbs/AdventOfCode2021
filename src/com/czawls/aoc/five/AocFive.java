package com.czawls.aoc.five;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AocFive {

    public static int solve(String[] input) {

        int[][] board;
        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        for (String newLine : input) {
            String[] coords = newLine.split(" -> ");

            String[] startCoord = coords[0].split(",");
            String[] endCoord = coords[1].split(",");

            Coordinate startCoordinate =
                    new Coordinate(Integer.parseInt(startCoord[0]), Integer.parseInt(startCoord[1]));
            Coordinate endCoordinate =
                    new Coordinate(Integer.parseInt(endCoord[0]), Integer.parseInt(endCoord[1]));

            if (startCoordinate.getX() == endCoordinate.getX() || startCoordinate.getY() == endCoordinate.getY()) {
                lines.add(new Line(startCoordinate, endCoordinate));
                xCoords.add(Integer.valueOf(startCoord[0]));
                xCoords.add(Integer.valueOf(endCoord[0]));
                yCoords.add(Integer.valueOf(startCoord[1]));
                yCoords.add(Integer.valueOf(endCoord[1]));
            }
        }

        int xMax = xCoords.stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        int yMax = yCoords.stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        board = new int[yMax + 1][xMax + 1];

        //populate board
        for (int i = 0; i <= yMax; i++) {
            for (int j = 0; j <= xMax; j++) {
                board[i][j] = 0;
            }
        }

        //mark coords
        for (Line curLine : lines) {
            for (Coordinate c : curLine.getCoordinateList()) {
                board[c.getY()][c.getX()]++;
            }
        }

        int total = 0;
        for (int i = 0; i <= yMax; i++) {
            for (int j = 0; j <= xMax; j++) {
                if (board[i][j] > 1) {
                    total++;
                }
            }
        }

        return total;
    }

    public static int solveTwo(String[] input) {

        int[][] board;
        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        for (String newLine : input) {
            String[] coords = newLine.split(" -> ");
            String[] startCoord = coords[0].split(",");
            String[] endCoord = coords[1].split(",");

            Coordinate startCoordinate =
                    new Coordinate(Integer.parseInt(startCoord[0]), Integer.parseInt(startCoord[1]));
            Coordinate endCoordinate =
                    new Coordinate(Integer.parseInt(endCoord[0]), Integer.parseInt(endCoord[1]));

            boolean isRowOrColumn = startCoordinate.getX() == endCoordinate.getX()
                    || startCoordinate.getY() == endCoordinate.getY();
            boolean is45DegDiag = Math.abs(startCoordinate.getX() - endCoordinate.getX())
                    == Math.abs(startCoordinate.getY() - endCoordinate.getY());

            if (isRowOrColumn || is45DegDiag) {
                lines.add(new Line(startCoordinate, endCoordinate));
                xCoords.add(Integer.valueOf(startCoord[0]));
                xCoords.add(Integer.valueOf(endCoord[0]));
                yCoords.add(Integer.valueOf(startCoord[1]));
                yCoords.add(Integer.valueOf(endCoord[1]));
            }
        }

        int xMax = xCoords.stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        int yMax = yCoords.stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        board = new int[yMax + 1][xMax + 1];

        //populate board
        for (int i = 0; i <= yMax; i++) {
            for (int j = 0; j <= xMax; j++) {
                board[i][j] = 0;
            }
        }

        //mark coords
        for (Line curLine : lines) {
            for (Coordinate c : curLine.getCoordinateList()) {
                board[c.getY()][c.getX()]++;
            }
        }

        int total = 0;
        for (int i = 0; i <= yMax; i++) {
            for (int j = 0; j <= xMax; j++) {
                if (board[i][j] > 1) {
                    total++;
                }
            }
        }

        return total;
    }
}
