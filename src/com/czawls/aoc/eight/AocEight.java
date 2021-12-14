package com.czawls.aoc.eight;

import java.util.*;


//why did I use char[] arrays?... why did I solve any of this like this?...
public class AocEight {

    //unique seg cts
    public static final int ONE_SEG_CT = 2;
    public static final int SEVEN_SEG_CT = 3;
    public static final int FOUR_SEG_CT = 4;
    public static final int EIGHT_SEG_CT = 7;

    //non-unique seg cts
    public static final int TWO_THREE_FIVE_CT = 5;
    public static final int SIX_NINE_CT = 6;

    /** signal maps
     *          *
     *          *           1111
     *          *          2    3
     *          *          2    3
     *          *           4444
     *          *          5    6
     *          *          5    6
     *          *           7777
     */
    public static final char[] ZERO =   new char[]{'.','.','.','-','.','.','.'};
    public static final char[] ONE =    new char[]{'-','-','.','-','-','.','-'};
    public static final char[] TWO =    new char[]{'.','-','.','.','.','-','.'};
    public static final char[] THREE =  new char[]{'.','-','.','.','-','.','.'};
    public static final char[] FOUR =   new char[]{'-','.','.','.','-','.','-'};
    public static final char[] FIVE =   new char[]{'.','.','-','.','-','.','.'};
    public static final char[] SIX =    new char[]{'.','.','-','.','.','.','.'};
    public static final char[] SEVEN =  new char[]{'.','-','.','-','-','.','-'};
    public static final char[] EIGHT =  new char[]{'.','.','.','.','.','.','.'};
    public static final char[] NINE =   new char[]{'.','.','.','.','-','.','.'};

    public static int solveOne(String[] input) {
        int totalUnique = 0;
        for(String line : input) {
            String output = line.split(" \\| ")[1];
            String[] digits = output.split(" ");
            for(String digit : digits) {
                int digitLength = digit.length();
                if(digitLength == ONE_SEG_CT
                        || digitLength == FOUR_SEG_CT
                        || digitLength == SEVEN_SEG_CT
                        || digitLength == EIGHT_SEG_CT) {
                    totalUnique++;
                }
            }
        }

        return totalUnique;
    }

    public static int solveTwo(String[] input) {

        /************************************************
         *
         * Map of char[] arrays, with an integer key
         *      mapping to each segment as follows:
         *
         *           1111
         *          2    3
         *          2    3
         *           4444
         *          5    6
         *          5    6
         *           7777
         *
         *  Each char[] array holds all possible
         *      chars for that segment based on what
         *      info we have so far.
         *
         ***********************************************/

        int ret = 0;
        for(String line : input) {
            Map<Integer, char[]> segmentPossibilities = new HashMap<>();
            String inputs = line.split(" \\| ")[0];
            String outputs = line.split(" \\| ")[1];
            Comparator<String> compByLength = Comparator.comparingInt(String::length);
            Object[] sortedDigitsByStrLength = Arrays.stream(inputs.split(" "))
                    .sorted(compByLength).toArray();
            for(Object digit : sortedDigitsByStrLength) {
                String num = (String) digit;
                solveDisplayPossibilitiesForUnique(num, segmentPossibilities);
            }
            for(Object digit : sortedDigitsByStrLength) {
                String num = (String) digit;
                if(num.length() == 6) {
                    solveDisplayPossibilitiesForSixNineZero(num, segmentPossibilities);
                }
            }
            char[] solvedDisplay = new char[]{segmentPossibilities.get(1)[0],
                    segmentPossibilities.get(2)[0],
                    segmentPossibilities.get(3)[0],
                    segmentPossibilities.get(4)[0],
                    segmentPossibilities.get(5)[0],
                    segmentPossibilities.get(6)[0],
                    segmentPossibilities.get(7)[0]};

            String[] outputVals = outputs.split(" ");
            String numString = "";
            for(String val : outputVals) {
                char[] signals = val.toCharArray();
                char[] tempDisp = new char[solvedDisplay.length];
                System.arraycopy(solvedDisplay, 0, tempDisp, 0, solvedDisplay.length);
                for(char sig : signals) {
                    for (int i = 0; i < tempDisp.length; i++) {
                        char c = tempDisp[i];
                        if (sig == c) {
                            tempDisp[i] = '.';
                        }
                    }
                }
                for(int i = 0; i < tempDisp.length; i++) {
                    char c = tempDisp[i];
                    if(c != '.') {
                        tempDisp[i] = '-';
                    }
                }
                numString += getValueForSignals(tempDisp);
            }
            ret += Integer.parseInt(numString);
        }
        return ret;
    }

    private static String getValueForSignals(char[] signalMap) {
        String ret = "";
        if(Arrays.equals(signalMap, ONE)){
            ret = "1";
        } else if(Arrays.equals(signalMap, TWO)){
            ret = "2";
        } else if(Arrays.equals(signalMap, THREE)){
            ret = "3";
        } else if(Arrays.equals(signalMap, FOUR)){
            ret = "4";
        } else if(Arrays.equals(signalMap, FIVE)){
            ret = "5";
        } else if(Arrays.equals(signalMap, SIX)){
            ret = "6";
        } else if(Arrays.equals(signalMap, SEVEN)){
            ret = "7";
        } else if(Arrays.equals(signalMap, EIGHT)){
            ret = "8";
        } else if(Arrays.equals(signalMap, NINE)){
            ret = "9";
        } else if(Arrays.equals(signalMap, ZERO)){
            ret = "0";
        }
        return ret;
    }

    private static void solveDisplayPossibilitiesForUnique(String digit, Map<Integer, char[]> segPoss) {
        char[] digitChars = digit.toCharArray();

        /*  Solve as much as possible using unique seg cts,
         *      then solve using non-unique (in increasing ct order).
         *
         *  Mappings reference:
         *           1111
         *          2    3
         *          2    3
         *           4444
         *          5    6
         *          5    6
         *           7777
         */
        switch(digit.length()) {
            case ONE_SEG_CT: { //it's a 1, so we know the possibilities of segments 3 and 6
                segPoss.put(3, new char[]{digitChars[0], digitChars[1]});
                segPoss.put(6, new char[]{digitChars[0], digitChars[1]});
                break;
            }
            case SEVEN_SEG_CT: { //it's a 7, and we can solve for the top segment
                char[] oneChars = segPoss.get(3);
                for(char digitChar : digitChars) {
                    if(digitChar != oneChars[0] && digitChar != oneChars[1]) {
                        segPoss.put(1, new char[]{digitChar});
                    }
                }
                break;
            }
            case FOUR_SEG_CT: { //it's a 4, and we can solve for the possibilities of segments 1 and 4
                char[] oneChars = segPoss.get(3);
                char[] nonOneChars = new char[2];
                for(char digitChar : digitChars) {
                    if(digitChar != oneChars[0] && digitChar != oneChars[1]) {
                        if(nonOneChars[0] == '\u0000') {
                            nonOneChars[0] = digitChar;
                        } else {
                            nonOneChars[1] = digitChar;
                        }
                    }
                }
                segPoss.put(2, nonOneChars);
                segPoss.put(4, nonOneChars);
                break;
            }
            case EIGHT_SEG_CT: { //it's an 8, ... solve for remaining possibilities
                char[] knownSegChars = new char[5];
                char[] unknownChars = new char[2];
                knownSegChars[0] = segPoss.get(1)[0];
                knownSegChars[1] = segPoss.get(2)[0];
                knownSegChars[2] = segPoss.get(2)[1];
                knownSegChars[3] = segPoss.get(3)[0];
                knownSegChars[4] = segPoss.get(3)[1];
                for(char digitChar : digitChars) {
                    if(digitChar != knownSegChars[0]
                            && digitChar != knownSegChars[1]
                            && digitChar != knownSegChars[2]
                            && digitChar != knownSegChars[3]
                            && digitChar != knownSegChars[4]) {
                        if(unknownChars[0] == '\u0000') {
                            unknownChars[0] = digitChar;
                        } else {
                            unknownChars[1] = digitChar;
                        }
                    }
                }
                segPoss.put(5, unknownChars);
                segPoss.put(7, unknownChars);
                break;
            }
        }
    }

    private static void solveDisplayPossibilitiesForSixNineZero(String digit, Map<Integer, char[]> segPoss) {
        char[] digitChars = digit.toCharArray();

        /*
         *  Mappings reference:
         *           1111
         *          2    3
         *          2    3
         *           4444
         *          5    6
         *          5    6
         *           7777
         */

        char[] oneSegs = segPoss.get(3);
        if(oneSegs.length == 1) {
            oneSegs = new char[2];
            oneSegs[0] = segPoss.get(3)[0];
            oneSegs[1] = segPoss.get(6)[0];
        }
        int matchingOneSegs = 0;
        if(oneSegs.length == 2) {
            for (char digitChar : digitChars) {
                if (digitChar == oneSegs[0]) {
                    matchingOneSegs++;
                } else if (digitChar == oneSegs[1]) {
                    matchingOneSegs++;
                }
            }
        }

        char[] fiveSegs = segPoss.get(5);
        if(fiveSegs.length == 1) {
            fiveSegs = new char[2];
            fiveSegs[0] = segPoss.get(5)[0];
            fiveSegs[1] = segPoss.get(7)[0];
        }
        int matchingFiveSegs = 0;
        if(fiveSegs.length == 2) {
            for (char digitChar : digitChars) {
                if (digitChar == fiveSegs[0]) {
                    matchingFiveSegs++;
                } else if (digitChar == fiveSegs[1]) {
                    matchingFiveSegs++;
                }
            }
        }

        if(matchingOneSegs == 2 && matchingFiveSegs == 2) { //it's a 0

            char[] allChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
            for(char digitChar : digitChars) {
                for (int i = 0; i < allChars.length; i++) {
                    if (allChars[i] == digitChar) {
                        allChars[i] = '.';
                    }
                }
            }
            for(char c : allChars) {
                if(c != '.') {
                    char[] segsTwoOrFour = segPoss.get(4);
                    for (char twoOrFour : segsTwoOrFour) {
                        if (c != twoOrFour) {
                            segPoss.put(2, new char[]{twoOrFour});
                        } else {
                            segPoss.put(4, new char[]{twoOrFour});
                        }
                    }
                }
            }
        } else if(matchingOneSegs == 1 && matchingFiveSegs == 2) { //it's a 6, solve for segment 3 and 6
            char[] allChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
            for(char digitChar : digitChars) {
                for (int i = 0; i < allChars.length; i++) {
                    if (allChars[i] == digitChar) {
                        allChars[i] = '.';
                    }
                }
            }
            for(char c : allChars) {
                if(c != '.'){
                    char[] segsThreeSix = segPoss.get(3);
                    for(char threeOrSix : segsThreeSix) {
                        if(c != threeOrSix) {
                            segPoss.put(6, new char[]{threeOrSix});
                        } else {
                            segPoss.put(3, new char[]{threeOrSix}); }
                    }
                }
            }
        } else if(matchingOneSegs == 2 && matchingFiveSegs == 1) { //it's a 9
            char[] allChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
            for (char digitChar : digitChars) {
                for (int i = 0; i < allChars.length; i++) {
                    if (allChars[i] == digitChar) {
                        allChars[i] = '.';
                    }
                }
            }
            for (char c : allChars) {
                if (c != '.') {
                    char[] segsFiveSeven = segPoss.get(5);
                    for (char fiveOrSeven : segsFiveSeven) {
                        if (c != fiveOrSeven) {
                            segPoss.put(7, new char[]{fiveOrSeven});
                        } else {
                            segPoss.put(5, new char[]{fiveOrSeven});
                        }
                    }
                }
            }
        }
    }
}
