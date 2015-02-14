package com.moyang.model;

import com.moyang.api.XueQiuLink;
import com.moyang.api.YahooHistory;
import com.moyang.common.StockNameUtil;
import com.moyang.common.StockUtil;
import com.moyang.stockfilter.*;
import org.apache.commons.lang.StringUtils;

/**
 * Created by yangmo on 15-2-6.
 */
public class StockFilterParas {
    private String highVolumeCriteria;
    private String oversoldCriteria;
    private String recentMaxVolCriteria;
    private String similarKAverageCriteria;

    public AndCriteria getAndCriteria(){
        AndCriteria andCriteria = new AndCriteria();
        if(!StringUtils.isEmpty(highVolumeCriteria)){
            andCriteria.appendCriteria(new HighVolumeCriteria(highVolumeCriteria));
        }

        if(!StringUtils.isEmpty(oversoldCriteria)){
            andCriteria.appendCriteria(new OversoldCriteria(oversoldCriteria));
        }

        if(!StringUtils.isEmpty(recentMaxVolCriteria)){
            andCriteria.appendCriteria(new RecentMaxVolCriteria(recentMaxVolCriteria));
        }

        if(!StringUtils.isEmpty(similarKAverageCriteria)){
            andCriteria.appendCriteria(new SimilarKAverageCriteria(similarKAverageCriteria));
        }
        return andCriteria;
    }

    public String getFilteredStockDetail(){
        AndCriteria andCriteria = getAndCriteria();

        StringBuffer result = new StringBuffer();
        int count = 0;

        int  notUpdated = 0;

        for(String stockId : StockNameUtil.getAllStockIds()){
            try{
                if(!StockUtil.isAlreadyUpdated(stockId)){

                    notUpdated++;
                    continue;
                }
                System.out.println("stockId " + stockId);

                YahooHistory history = new YahooHistory(stockId);
                if(andCriteria.meetCriteria(history)){
                    result.append(count ++ + "\t");
                    result.append(stockId + "\t" + StockNameUtil.getName(stockId) + andCriteria.getDetail(history)
                            + "\t" + XueQiuLink.getLink(stockId));
                    result.append("\n");
                }
            } catch (Exception e) {
            }

        }

        return result.toString();
    }

    public String getHighVolumeCriteria(){
        return  highVolumeCriteria;
    }

    public void setHighVolumeCriteria(String highVolumeCriteria){
        this.highVolumeCriteria = highVolumeCriteria;
    }

    public String getOversoldCriteria(){
        return oversoldCriteria;
    }

    public void setOversoldCriteria(String oversoldCriteria){
        this.oversoldCriteria = oversoldCriteria;
    }

    public String getRecentMaxVolCriteria(){
        return recentMaxVolCriteria;
    }

    public void setRecentMaxVolCriteria(String recentMaxVolCriteria){
        this.recentMaxVolCriteria = recentMaxVolCriteria;
    }

    public String getSimilarKAverageCriteria(){
        return similarKAverageCriteria;
    }

    public void setSimilarKAverageCriteria(String similarKAverageCriteria){
        this.similarKAverageCriteria = similarKAverageCriteria;
    }
}
