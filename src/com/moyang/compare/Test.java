package com.moyang.compare;

import com.moyang.model.SnapShoot;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.common.StockNameUtil;
import com.moyang.hibernate.StockDaily;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmo on 15-1-24.
 */
public class Test {

    public static List<Double> retrieveDoubleList(SnapShoot snapShoot){
        List<Double> result = new ArrayList<Double>();

        for(StockDaily datum : snapShoot.getDatumList()){
            result.add(datum.getAdjClose());
        }

        return result;
    }
    public static void main(String[] args) throws Exception{

        SnapShootStore.init();

        String stockId = "600886";


        SnapShoot original = SnapShootStore.generateSnapShoot(stockId, 0, Constants.INTERVAL);

        int count = 0;
        for(String tempStock : StockNameUtil.getAllStockIds()){

            System.out.println(tempStock + count++ );
            int size = new YahooHistory(tempStock).getYahooHistory().size();

            for(int i = size - 1; i >= Constants.INTERVAL; i--){
                SnapShoot cur = SnapShootStore.generateSnapShoot(tempStock, i - Constants.INTERVAL, i);
                double distance = DiscreteFrechetDistance.computeDFD(retrieveDoubleList(original)
                        , retrieveDoubleList(cur), SnapShootStore.lowBound());

                cur.setDistance(distance);
                SnapShootStore.add(cur);
            }
            SnapShootStore.printResult();
        }
    }
}
