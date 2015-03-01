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
    private String macdCriteria;

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

        if(!StringUtils.isEmpty(macdCriteria)){
            andCriteria.appendCriteria(new MACDCriteria(macdCriteria));
        }

        return andCriteria;
    }
    public static void main(String[] k){
        StockFilterParas paras = new StockFilterParas();
        paras.setMacdCriteria("2");
        System.out.println(paras.getFilteredStockDetail().size());
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
                throw new RuntimeException(e);
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

    public String getMacdCriteria(){
        return macdCriteria;
    }

    public void setMacdCriteria(String macdCriteria){
        this.macdCriteria = macdCriteria;
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
