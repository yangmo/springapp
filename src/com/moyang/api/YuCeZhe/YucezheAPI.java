package com.moyang.api.YuCeZhe;

import com.moyang.common.Constants;
import com.moyang.common.FileUtil;
import com.moyang.common.JsonSerializer;
import com.moyang.common.MarketplaceUtil;
import com.moyang.hibernate.StockDaily;

import java.util.*;

/**
 * Created by moyang on 15/8/2.
 */
public class YucezheAPI {

    private static final String BASE = "/usr/all_trading_data/stock data/";
    static String getPath(String stockId) {
        String marketPrefix = MarketplaceUtil.getMarketplace(stockId);
        return BASE + marketPrefix + stockId + ".csv";
    }


    public static List<StockDaily> getStockDailies(String stockId) {
        String content = FileUtil.getContent(getPath(stockId));
        List<StockDaily> stockDailies = new ArrayList<StockDaily>();

        String[] lines = content.split("\n");
        if (lines.length < 2) {
            return stockDailies;
        }

        for (int i = 1; i < lines.length; i++) {
            stockDailies.add(YucezheCommonUtil.getStockDailyFromLine(lines[i]));
        }


        Collections.reverse(stockDailies);

        return stockDailies;
    }

    /**
     *
     * @param stockId
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public static List<StockDaily> getHistoryBetween(String stockId, Date start, Date end) throws Exception{

        if(end.before(start)) {
            throw new RuntimeException("end date before start date!");
        }

        List<StockDaily> data = getStockDailies(stockId);
        int length = data.size();
        if(data == null || length == 0){
            return null;
        }

        List<StockDaily> result = new ArrayList<StockDaily>();
        for(StockDaily stockDaily : data) {
            if(!stockDaily.getDate().after(start) && !stockDaily.getDate().before(end)) {
                result.add(stockDaily);
            }
        }

        return result;
    }


    public static void main(String[] args) {

        System.out.println(JsonSerializer.serialize(getStockDailies("002322").get(0)));
    }
}