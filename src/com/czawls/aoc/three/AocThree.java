package com.czawls.aoc.three;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AocThree {

    public static int getRate(String[] lines) {
        int b0 = 0, b1 = 0, b2 = 0, b3 = 0,
            b4 = 0, b5 = 0, b6 = 0, b7 = 0,
            b8 = 0, b9 = 0,b10 = 0,b11 = 0;
        int[] g = new int[12];
        int[] e = new int[12];
        for(String line : lines) {
            int[] bits = getBits(line);
            b0 += bits[0] == 1 ? 1 : -1;
            b1 += bits[1] == 1 ? 1 : -1;
            b2 += bits[2] == 1 ? 1 : -1;
            b3 += bits[3] == 1 ? 1 : -1;
            b4 += bits[4] == 1 ? 1 : -1;
            b5 += bits[5] == 1 ? 1 : -1;
            b6 += bits[6] == 1 ? 1 : -1;
            b7 += bits[7] == 1 ? 1 : -1;
            b8 += bits[8] == 1 ? 1 : -1;
            b9 += bits[9] == 1 ? 1 : -1;
            b10 += bits[10] == 1 ? 1 : -1;
            b11 += bits[11] == 1 ? 1 : -1;
        }
        g[0] = b0 > 0 ? 1 : 0;
        e[0] = b0 > 0 ? 0 : 1;
        g[1] = b1 > 0 ? 1 : 0;
        e[1] = b1 > 0 ? 0 : 1;
        g[2] = b2 > 0 ? 1 : 0;
        e[2] = b2 > 0 ? 0 : 1;
        g[3] = b3 > 0 ? 1 : 0;
        e[3] = b3 > 0 ? 0 : 1;
        g[4] = b4 > 0 ? 1 : 0;
        e[4] = b4 > 0 ? 0 : 1;
        g[5] = b5 > 0 ? 1 : 0;
        e[5] = b5 > 0 ? 0 : 1;
        g[6] = b6 > 0 ? 1 : 0;
        e[6] = b6 > 0 ? 0 : 1;
        g[7] = b7 > 0 ? 1 : 0;
        e[7] = b7 > 0 ? 0 : 1;
        g[8] = b8 > 0 ? 1 : 0;
        e[8] = b8 > 0 ? 0 : 1;
        g[9] = b9 > 0 ? 1 : 0;
        e[9] = b9 > 0 ? 0 : 1;
        g[10] = b10 > 0 ? 1 : 0;
        e[10] = b10 > 0 ? 0 : 1;
        g[11] = b11 > 0 ? 1 : 0;
        e[11] = b11 > 0 ? 0 : 1;

        int gamma = calcRate(g);
        int epsilon = calcRate(e);
        return epsilon * gamma;
    }

    public static int getRateTwo(String[] lines) {
        int oRating = calcOxygenRating(Arrays.asList(lines), 0);
        int sRating = calcScrubRating(Arrays.asList(lines), 0);
        return oRating * sRating;
    }

    private static int calcOxygenRating(List<String> lines, int bitColIndex) {

        //terminating condition
        if(lines.size() == 1) {
            return calcRate(getBits(lines.get(0)));
        }

        //get column most-common-bit magnitude
        int curColumnMagnitude = 0;
        for(String line : lines) {
            int[] bits = getBits(line);
            curColumnMagnitude += bits[bitColIndex] == 1 ? 1 : -1;
        }

        //filter out lines that don't meet bit criteria for oxygen rating
        String regexTemplate = getOxygenRegexTemplate(curColumnMagnitude, bitColIndex);
        Pattern pattern = Pattern.compile(regexTemplate);
        List<String> remainingLines = lines.stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList());

        return calcOxygenRating(remainingLines, bitColIndex + 1);
    }

    private static int calcScrubRating(List<String> lines, int bitColIndex) {

        //terminating condition
        if(lines.size() == 1) {
            return calcRate(getBits(lines.get(0)));
        }

        //get column most-common-bit magnitude
        int curColumnMagnitude = 0;
        for(String line : lines) {
            int[] bits = getBits(line);
            curColumnMagnitude += bits[bitColIndex] == 1 ? 1 : -1;
        }

        //filter out lines that don't meet bit criteria for scrubber rating
        String regexTemplate = getScrubRegexTemplate(curColumnMagnitude, bitColIndex);
        Pattern pattern = Pattern.compile(regexTemplate);
        List<String> remainingLines = lines.stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList());

        return calcScrubRating(remainingLines, bitColIndex + 1);
    }

    private static String getOxygenRegexTemplate(int colMag, int colIndex) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 12; i++) {
            if(i == colIndex) {
                sb.append(colMag >= 0 ? 1 : 0);
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static String getScrubRegexTemplate(int colMag, int colIndex) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 12; i++) {
            if(i == colIndex) {
                sb.append(colMag < 0 ? 1 : 0);
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static int[] getBits(String line) {
        return Arrays.stream(line.split(""))
                .map(Integer::parseInt)
                .mapToInt(v -> v)
                .toArray();
    }

    private static int calcRate(int[] g) {
        int rate = g[0] * 2048 + g[1] * 1024 + g[2] * 512 + g[3] * 256
                + g[4] * 128  + g[5] * 64   + g[6] * 32  + g[7] * 16
                + g[8] * 8    + g[9] * 4   + g[10] * 2   + g[11];
        return rate;
    }
}
