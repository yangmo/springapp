package com.moyang.compare;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by moyang on 1/22/15.
 */

class Point {
    /** The dimensions of this point */
    protected int[] dimensions;

    public Point(int[] dims) {
        this.dimensions = dims;
    }

    public Point(List<Integer> dims) {
        int[] arr = new int[dims.size()];
        for(int i = 0; i < dims.size(); i++){
            arr[i] = dims.get(i);
        }
        this.dimensions = arr;
    }
}