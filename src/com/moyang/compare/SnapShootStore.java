package com.moyang.compare;

import com.moyang.model.SnapShoot;
import com.moyang.api.Yahoo.YahooAPI;
import com.moyang.common.Constants;
import com.moyang.common.DateUtil;
import com.moyang.common.StockNameUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yangmo on 15-1-24.
 */
public class SnapShootStore {
    public static final int SIZE = 50;

    public static ArrayList<SnapShoot> resultList = new ArrayList<SnapShoot>();

    static {
        init();
    }

    public static double lowBound(){
        return resultList.get(SIZE - 1).getDistance();
    }

    public static SnapShoot generateSnapShoot(String stockId, int start, int end)
        throws Exception{
        return new SnapShoot(stockId, YahooAPI.getHistoryBetween(stockId, start, end), 0);
    }

    public static void init(){
        for(int i = 0; i < SIZE; i++){
            resultList.add(new SnapShoot("", null, Double.MAX_VALUE));
        }
    }

    public static void add(SnapShoot snapShoot){
        if(snapShoot.getDistance() < resultList.get(SIZE - 1).getDistance()){
            resultList.set(SIZE - 1, snapShoot);
            Collections.sort(resultList);
        }
    }



    public static void printResult() throws ParseException{

        for(SnapShoot snapShoot : resultList){
            if(snapShoot.getDatumList() != null){
                String dateStr =  snapShoot.getDatumList().get(0).getDateStr();
                int end   = DateUtil.daysFromNow(dateStr) + Constants.INTERVAL;
                int start = end - 1 * Constants.INTERVAL;

                System.out.println(
                         StockNameUtil.getName(snapShoot.getStockId()) + "\t"
                        + snapShoot+ "\t"
                        + snapShoot.getDistance());
            }
        }
        System.out.println("\n");
    }
}
