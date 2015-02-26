package com.moyang.model;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.MarketplaceUtil;
import com.moyang.common.StockNameUtil;
import com.moyang.common.StockUtil;
import com.moyang.stockfilter.*;
import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.util.ArrayList;

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

    public ArrayList<FilterResult> getFilteredStockDetail(){
        AndCriteria andCriteria = getAndCriteria();

        int count = 0;

        ArrayList<FilterResult> list = new ArrayList<FilterResult>();

        for(String stockId : StockNameUtil.getAllStockIds()){
            try{
                if(!StockUtil.isAlreadyUpdated(stockId)){
                    continue;
                }
                System.out.println("stockId " + stockId);

                YahooHistory history = new YahooHistory(stockId);
                if(andCriteria.meetCriteria(history)){
                    FilterResult filterResult = new FilterResult();
                    filterResult.setSerialNo(count++);
                    filterResult.setStockId(stockId);
                    filterResult.setStockName(URLEncoder.encode(StockNameUtil.getName(stockId), "UTF-8"));
                    filterResult.setDetail(andCriteria.getDetail(history));
                    filterResult.setLink("http://xueqiu.com/S/"
                            + MarketplaceUtil.getMarketplace(stockId).toUpperCase() + stockId);
                    list.add(filterResult);
                }
            } catch (Exception e) {
            }

        }

        return list;
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
