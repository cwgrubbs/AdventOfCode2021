package com.czawls.aoc.six;

import java.util.ArrayList;
import java.util.List;

public class AocSix {

    private static final int DAYS = 128;
    private static final int NUM_FISH = 300;

    //pre-calculated total num fish after 128 days for a fish of age 0, 1, 2, ... 8
    private static final int f0 = 94508;
    private static final int f1 = 90763;
    private static final int f2 = 79321;
    private static final int f3 = 75638;
    private static final int f4 = 67616;
    private static final int f5 = 62475;
    private static final int f6 = 58016;
    private static final int f7 = 51564;
    private static final int f8 = 49380;

    public static int solveOne(String[] input) {

        List<Fish> fishies = new ArrayList<>();
        String[] all = input[0].split(",");
        for (String fishAge : all) {
            int age = Integer.parseInt(fishAge);
            fishies.add(new Fish(age));
        }

        return passDays(fishies, DAYS);
    }

    public static long solveTwo(String[] input) {
        String[] all = input[0].split(",");
        long total = 0;
        for(int i = 0; i < NUM_FISH; i++) {
            int age = Integer.parseInt(all[i]);
            List<Fish> fishies = new ArrayList<>();
            fishies.add(new Fish(age));
            List<Fish> generated = passDaysSingleFish(fishies, DAYS);
            for(Fish genFish : generated) {
                switch (genFish.getAge()) {
                    case 0 : total += f0; break;
                    case 1 : total += f1; break;
                    case 2 : total += f2; break;
                    case 3 : total += f3; break;
                    case 4 : total += f4; break;
                    case 5 : total += f5; break;
                    case 6 : total += f6; break;
                    case 7 : total += f7; break;
                    case 8 : total += f8; break;
                }
            }
        }

        return total;
    }

    private static int passDays(List<Fish> fishies, int daysRemaining) {

        //terminating condition
        if(daysRemaining == 0) {
            return fishies.size();
        }

        List<Fish> totalFishies = new ArrayList<>(fishies);
        for(Fish fish : fishies) {
            if(fish.passDay()) {
                totalFishies.add(new Fish(8));
            }
        }
        return passDays(totalFishies, daysRemaining - 1);
    }

    private static List<Fish> passDaysSingleFish(List<Fish> fishies, int daysRemaining) {

        //terminating condition
        if(daysRemaining == 0) {
            return fishies;
        }

        List<Fish> totalFishies = new ArrayList<>(fishies);
        for(Fish fish : fishies) {
            if(fish.passDay()) {
                totalFishies.add(new Fish(8));
            }
        }
        return passDaysSingleFish(totalFishies, daysRemaining - 1);
    }
}
