package com.czawls.aoc.six;

import java.util.ArrayList;
import java.util.List;

public class AocSix {

    private static final int DAYS = 80;

    public static int solveOne(String[] input) {

        List<Fish> fishies = new ArrayList<>();
        String[] all = input[0].split(",");
        for (String fishAge : all) {
            int age = Integer.parseInt(fishAge);
            fishies.add(new Fish(age));
        }

        return passDays(fishies, DAYS);
    }

    public static int solveTwo(String[] input) {

        List<Fish> fishies = new ArrayList<>();
        String[] all = input[0].split(",");
        for (String fishAge : all) {
            int age = Integer.parseInt(fishAge);
            fishies.add(new Fish(age));
        }

        return passDays(fishies, DAYS);
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
}
