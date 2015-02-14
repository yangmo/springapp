package com.moyang.compare;

import com.moyang.api.YahooDatum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmo on 15-1-24.
 */
public class HistoryCompare {
    public static List<Double> retrieveDoubleList(List<YahooDatum> list){
        ArrayList<Double> result = new ArrayList<Double>();

        for(YahooDatum datum : list){
            result.add(datum.getAdjClose());
        }

        return result;
    }
    public static double getDifference(List<YahooDatum> listA, List<YahooDatum> listB, double lowBound) throws Exception{

        List<Double> a = retrieveDoubleList(listA);
        List<Double> b = retrieveDoubleList(listB);

        return DiscreteFrechetDistance.computeDFD(a, b, lowBound);
    }
}
