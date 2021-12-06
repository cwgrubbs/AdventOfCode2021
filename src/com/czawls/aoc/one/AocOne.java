package com.czawls.aoc.one;

public class AocOne {

    public static int solveOne(int[] depths) {
        int ret = 0;
        for(int i = 1; i < depths.length; i++) {
            if(depths[i - 1] < depths[i]) {
                ret++;
            }
        }
        return ret;
    }

    public static int solveTwo(int[] depths) {
        int ret = 0;
        int curWin = 0;
        int prevWin = depths[0] + depths[1] + depths[2];
        for(int i = 0; i < depths.length - 2; i++) {
            curWin = depths[i] + depths[i+1] + depths[i+2];
            if(curWin > prevWin) {
                ret++;
            }
            prevWin = curWin;
        }
        return ret;
    }
}
