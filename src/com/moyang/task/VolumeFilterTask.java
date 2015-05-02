package com.moyang.task;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.StockNameUtil;
import com.moyang.common.StockUtil;
import com.moyang.stockfilter.*;

/**
 * Created by yangmo on 15-1-25.
 */
public class VolumeFilterTask {


    public static boolean isRecentRisingDay(String stockId)throws Exception{
        YahooHistory history = new YahooHistory(stockId);
        double recent = history.getYahooHistory().get(history.getYahooHistory().size()-1).getAdjClose();

        double pre = history.getYahooHistory().get(history.getYahooHistory().size()-2).getAdjClose();
        return recent > pre;

    }
    public static void run(){
        int count = 0;
        AndCriteria andCriteria = new AndCriteria();
        andCriteria.appendCriteria(new HighVolumeCriteria("12,2"));
    //   andCriteria.appendCriteria(new RecentMaxVolCriteria("13"));
     //andCriteria.appendCriteria(new OversoldCriteria("20,0.21"));
     // andCriteria.appendCriteria(new SimilarKAverageCriteria("30,0.2"));
       //         .appendCriteria(new SimilarKAverageCriteria("5,0.05"));
    // andCriteria.appendCriteria(new MACDCriteria("1"));
     for(String stockId : StockNameUtil.getAllStockIds()){
            try{
                if(!StockUtil.isAlreadyUpdated(stockId)){
                    continue;
                }


                YahooHistory history = new YahooHistory(stockId);
                if(andCriteria.meetCriteria(history)){
                    System.out.print(count ++ + "\t");
                    System.out.println(stockId  +"\t" + StockNameUtil.getName(stockId) + andCriteria.getDetail(history));
                }
            } catch (Exception e) {
                continue;
            }

        }
    }
    public static void main(String[] args) throws Exception{
        run();
    }
}
