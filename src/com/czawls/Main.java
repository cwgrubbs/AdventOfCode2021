package com.czawls;

import com.czawls.aoc.seven.AocSeven;
import com.czawls.util.FileArrayProvider;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] in = FileArrayProvider.readLines(
                "/home/czawlz/IdeaProjects/AdventOfCode2021/src/com/czawls/aoc/seven/input");
        long ret = AocSeven.solveTwo(in);
        System.out.println(ret);
    }
}
