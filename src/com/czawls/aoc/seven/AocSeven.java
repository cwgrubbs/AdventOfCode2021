package com.czawls.aoc.seven;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class AocSeven {

    public static int solveOne(String[] input) {
        int[] crabbies = Arrays.stream(input[0].split(","))
                .map(Integer::parseInt)
                .mapToInt(v -> v)
                .toArray();

        int tempFuelCost = 0, minFuelCost = 447418;
        int max = Arrays.stream(crabbies).max().orElseThrow(NoSuchElementException::new);
        for(int i = 0; i < max; i++) {
            for (int crab : crabbies) {
                tempFuelCost += Math.abs(i - crab);
            }
            if(tempFuelCost < minFuelCost) {
                minFuelCost = tempFuelCost;
            }
            tempFuelCost = 0;
        }

        return minFuelCost;
    }

    public static long solveTwo(String[] input) {
        int[] crabbies = Arrays.stream(input[0].split(","))
                .map(Integer::parseInt)
                .mapToInt(v -> v)
                .toArray();

        long tempFuelCost = 0, minFuelCost = 205962652;
        int max = Arrays.stream(crabbies).max().orElseThrow(NoSuchElementException::new);
        for(int i = 0; i < max; i++) {
            for (int crab : crabbies) {
                tempFuelCost += getFuelCostForMove(Math.abs(i - crab));
            }
            if(tempFuelCost < minFuelCost) {
                minFuelCost = tempFuelCost;
            }
            tempFuelCost = 0;
        }

        return minFuelCost;
    }

    private static long getFuelCostForMove(int move) {
        long ret = 0;
        for(int i = 1; i <= move; i++) {
            ret += i;
        }
        return ret;
    }
}
