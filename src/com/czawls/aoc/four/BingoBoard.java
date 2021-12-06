package com.czawls.aoc.four;

import java.util.Arrays;

public class BingoBoard {

    private static final int SZ = 5;

    private final int[][] board = new int[SZ][SZ];

    private int marks = 0;
    private int score = 0;

    public BingoBoard(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int[] row = Arrays.stream(line.stripLeading().split("\\s+"))
                    .map(Integer::parseInt)
                    .mapToInt(v -> v)
                    .toArray();
            System.arraycopy(row, 0, board[i], 0, row.length);
            score += Arrays.stream(row).sum();
        }
    }

    public void move(int num) {
        for(int i = 0; i < SZ; i++) {
            int[] row = board[i];
            for(int j = 0; j < SZ; j++) {
                if(board[i][j] == num) {
                    markSpace(j, i);
                    score -= num;
                }
            }
        }
    }

    public boolean isWinner() {
        if(marks < 5) {
            return false;
        }
        for(int i = 0; i < SZ; i++) {
            if(isRowWin(i)) {
                return true;
            }
            if(isColWin(i)) {
                return true;
            }
        }
        return false;
    }

    public int getBoardScore(int winningNum) {
        return winningNum * score;
    }

    private boolean isRowWin(int rowIndex) {
        return Arrays.stream(this.board[rowIndex]).sum() == -SZ;
    }

    private boolean isColWin(int colIndex) {
        int colSum = 0;
        for(int i = 0; i < SZ; i++) {
            colSum += board[i][colIndex];
        }
        return colSum == -SZ;
    }

    private void markSpace(int x, int y) {
        this.board[y][x] = -1;
        marks++;
    }
}
