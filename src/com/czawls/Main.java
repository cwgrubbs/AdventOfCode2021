package com.czawls;

import com.czawls.aoc.six.AocSix;
import com.czawls.util.FileArrayProvider;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] in = FileArrayProvider.readLines(
                "/home/czawlz/IdeaProjects/AdventOfCode2021/src/com/czawls/aoc/six/input");
        long ret = AocSix.solveTwo(in);
        System.out.println(ret);
    }
}
