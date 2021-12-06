package com.czawls.aoc.two;

public class AocTwo {

    public static int getCourse(String[] moves) {
        String dir = "";
        int d = 0, h = 0, mag = 0;
        for(String move : moves) {
            String[] dirMag = move.split(" ");
            dir = dirMag[0];
            mag = Integer.parseInt(dirMag[1]);
            switch(dir) {
                case "up" : d -= mag; break;
                case "down" : d += mag; break;
                case "forward" : h += mag; break;
            }
        }
        return d * h;
    }

    public static int getCourseTwo(String[] moves) {
        String dir = "";
        int d = 0, h = 0, mag = 0, aim = 0;
        for(String move : moves) {
            String[] dirMag = move.split(" ");
            dir = dirMag[0];
            mag = Integer.parseInt(dirMag[1]);
            switch(dir) {
                case "up" : aim -= mag; break;
                case "down" : aim += mag; break;
                case "forward" : {
                    d += mag * aim;
                    h += mag;
                    break;
                }
            }
        }
        return d * h;
    }
}
