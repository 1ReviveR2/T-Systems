package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PyramidTest {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 15, 2);
        PyramidBuilder pb = new PyramidBuilder();
        pb.buildPyramid(input);
    }

}
