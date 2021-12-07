package com.czawls.aoc.six;

public class Fish {

    private static final int TIMER = 6;

    private int age;

    public Fish(int ageIn) {
        this.age = ageIn;
    }

    //return true if a new fish is spawned
    public boolean passDay() {

        if(age == 0) {
            age = 6;
            return true;
        }

        age--;
        return false;
    }

    public int getAge() {
        return age;
    }
}
