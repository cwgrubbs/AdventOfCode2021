package com.czawls.aoc.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AocFour {

    public static int partOne(String[] input) {
        int[] moves = Arrays.stream(input[0].split(","))
                .map((Integer::parseInt))
                .mapToInt(v -> v)
                .toArray();

        //create boards
        List<BingoBoard> boards = IntStream.iterate(2, i -> i < input.length, i -> i + 6)
                .mapToObj(i -> new String[]{input[i], input[i + 1], input[i + 2], input[i + 3], input[i + 4]})
                .map(BingoBoard::new)
                .collect(Collectors.toList());

        for(int move : moves ){
            for(BingoBoard board : boards) {
                board.move(move);
                if(board.isWinner()){
                    return board.getBoardScore(move);
                }
            }
        }

        return 0;
    }

    public static int partTwo(String[] input) {
        int[] moves = Arrays.stream(input[0].split(","))
                .map((Integer::parseInt))
                .mapToInt(v -> v)
                .toArray();

        //create boards
        List<BingoBoard> boards = IntStream.iterate(2, i -> i < input.length, i -> i + 6)
                .mapToObj(i -> new String[]{input[i], input[i + 1], input[i + 2], input[i + 3], input[i + 4]})
                .map(BingoBoard::new)
                .collect(Collectors.toList());

        return getLastWinnerScore(boards, moves);
    }

    //recursive solution
    private static int getLastWinnerScore(List<BingoBoard> boards, int[] moves) {

        //terminating condition
        if(boards.size() == 1) {
            //keep going until this loser wins...
            for(int move : moves ){
                for(BingoBoard board : boards) {
                    board.move(move);
                    if(board.isWinner()){
                        return board.getBoardScore(move);
                    }
                }
            }
        }

        for(int move : moves){
            List<BingoBoard> remainingBoards = new ArrayList<>(boards);
            int[] remainingMoves = new int[moves.length - 1];
            System.arraycopy(moves, 1, remainingMoves, 0, moves.length - 1);
            for(BingoBoard board : boards) {
                board.move(move);
                if(board.isWinner()) {
                    remainingBoards.remove(board);
                }
            }
            if(remainingBoards.size() < boards.size()) {
                return getLastWinnerScore(remainingBoards, remainingMoves);
            }
        }

        return -1;
    }
}
