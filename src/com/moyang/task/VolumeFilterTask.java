package com.moyang.task;

import com.moyang.api.AverageUtil;
import com.moyang.api.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.common.StockNameUtil;
import com.moyang.common.StockUtil;
import com.moyang.model.AverageDatum;
import com.moyang.api.YahooDatum;
import com.moyang.stockfilter.*;

import java.util.List;

/**
 * Created by yangmo on 15-1-25.
 */
public class VolumeFilterTask {


    public static boolean isRecentRisingDay(String stockId)throws Exception{
        YahooHistory history = new YahooHistory(stockId);
        double recent = history.getYahooHistory().get(history.getYahooHistory().size()-1).getClose();

        double pre = history.getYahooHistory().get(history.getYahooHistory().size()-2).getClose();
        return recent > pre;

    }
    public static void run(){
        int count = 0;
        AndCriteria andCriteria = new AndCriteria();
        andCriteria.appendCriteria(new HighVolumeCriteria("13,2"));
   //     andCriteria.appendCriteria(new RecentMaxVolCriteria("13"));
      // andCriteria.appendCriteria(new OversoldCriteria("10,0.03"));
     //   andCriteria.appendCriteria(new SimilarKAverageCriteria("30,0.05"))
       //         .appendCriteria(new SimilarKAverageCriteria("5,0.05"));
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
