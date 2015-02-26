package com.moyang.evaluate;

import com.moyang.model.AverageDatum;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-14.
 */
public class MACDEvaluator {

    private ArrayList<AverageDatum> macdList = new ArrayList<AverageDatum>();

    public MACDEvaluator(ArrayList<AverageDatum> macdList){
        this.macdList = macdList;
    }
}
