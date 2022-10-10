package com.czawls.aoc.nine;

import java.util.ArrayList;
import java.util.List;

public class AocNine {

    public static int solveOne(String[] input) {
        int ret = 0;
        int[][] heightMap = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] pts = line.split("");
            for (int j = 0; j < pts.length; j++) {
                String pt = pts[j];
                heightMap[i][j] = Integer.parseInt(pt);
            }
        }
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] pts = line.split("");
            for (int j = 0; j < pts.length; j++) {
                String pt = pts[j];
                if(isLowPoint(j, i, heightMap)) {
                    ret += (Integer.parseInt(pt) + 1);
                }
            }
        }
        return ret;
    }

    public static int solveTwo(String[] input) {
        int ret = 0;
        List<Integer> basins = new ArrayList<>();
        int[][] heightMap = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] pts = line.split("");
            for (int j = 0; j < pts.length; j++) {
                String pt = pts[j];
                heightMap[i][j] = Integer.parseInt(pt);
            }
        }
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] pts = line.split("");
            for (int j = 0; j < pts.length; j++) {
                String pt = pts[j];
                if(isLowPoint(j, i, heightMap)) {
                    ret += (Integer.parseInt(pt) + 1);
                }
            }
        }
        return ret;
    }

    private static int calculateBasin(int x, int y, int[][] hm) {
        int ret = 0;

        return ret;
    }

    private static boolean isLowPoint(int x, int y, int[][] hm) {
        boolean ret = true;
        int pt = hm[y][x];
        int n = y - 1;
        int s = y + 1;
        int e = x + 1;
        int w = x - 1;
        if(n >= 0) {
            int north = hm[n][x];
            if(pt >= north) {
                ret = false;
            }
        }
        if(s < hm.length) {
            int south = hm[s][x];
            if(pt >= south){
                ret = false;
            }
        }
        if(e < hm[0].length) {
            int east = hm[y][e];
            if(pt >= east) {
                ret = false;
            }
        }
        if(w >= 0) {
            int west = hm[y][w];
            if(pt >= west) {
                ret = false;
            }
        }
        return ret;
    }
}
