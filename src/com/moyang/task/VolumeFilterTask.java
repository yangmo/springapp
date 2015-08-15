package com.moyang.task;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.api.YuCeZhe.YucezheAPI;
import com.moyang.common.StockNameUtil;
import com.moyang.model.StockDaily;
import com.moyang.stockfilter.AndCriteria;
import com.moyang.stockfilter.*;
import com.moyang.stockfilter.HighVolumeCriteria;

import java.util.List;

/**
 * Created by yangmo on 15-1-25.
 */
public class VolumeFilterTask {

    public static int count = 0;

    public static boolean isRecentRisingDay(String stockId)throws Exception{
        YahooHistory history = new YahooHistory(stockId);
        double recent = history.getYahooHistory().get(history.getYahooHistory().size()-1).getAdjClose();

        double pre = history.getYahooHistory().get(history.getYahooHistory().size()-2).getAdjClose();
        return recent > pre;

    }
    public static void run(){
        AndCriteria andCriteria = new AndCriteria("");
        // andCriteria.appendCriteria(new HighVolumeCriteria("12,2"));
        // andCriteria.appendCriteria(new RecentMaxVolCriteria("13"));
        // andCriteria.appendCriteria(new OversoldCriteria("20,0.31"));
        andCriteria.appendCriteria(new SimilarKAverageCriteria("30,0.03"));
        //         .appendCriteria(new SimilarKAverageCriteria("5,0.05"));
        andCriteria.appendCriteria(new PECriteria("50"));
        // andCriteria.appendCriteria(new PBCriteria("1.6"));
        // andCriteria.appendCriteria(new TurnoverCriteria("0.20"));
        // andCriteria.appendCriteria(new MACDCriteria("1"));
        
        for(String stockId : StockNameUtil.getAllStockIds()) {
            try {
                List<StockDaily> stockDailies = YucezheAPI.getStockDailies(stockId);
                filter(andCriteria, stockId, stockDailies);
            } catch (Exception e) {

            }
        }
    }
    public static void filter(AndCriteria andCriteria, String stockId, List<StockDaily> stockDailies) {
        try{

            if(andCriteria.meetCriteria(stockDailies)){
                System.out.println(count++ + "\t" + stockId  +"\t" + StockNameUtil.getName(stockId) + andCriteria.getDetail(stockDailies));
            }
        } catch (Exception e) {

        }
    }
    public static void main(String[] args) throws Exception{
        run();
    }
}
