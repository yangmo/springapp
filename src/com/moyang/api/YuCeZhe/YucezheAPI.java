package com.moyang.api.YuCeZhe;

import com.moyang.common.Constants;
import com.moyang.common.FileUtil;
import com.moyang.common.JsonSerializer;
import com.moyang.common.MarketplaceUtil;
import com.moyang.hibernate.StockDaily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by moyang on 15/8/2.
 */
public class YucezheAPI {

    private static final String BASE = "/usr/all_trading_data/stock data/";

    static String getPath(String stockId) {
        String marketPrefix = MarketplaceUtil.getMarketplace(stockId);
        return BASE + marketPrefix + stockId + ".csv";
    }

    /**
     * Fail to parse Long if it is something like 20239900.0
     * @param volume
     * @return
     */
    static Long retrieveVolume(String volume) {
        if(volume.contains(".0")) {
            String[] strs = volume.split(".");

            return Long.valueOf(volume.replace(".0", ""));
        }
        return Long.valueOf(volume);
    }

    static StockDaily getStockDailyFromLine(String line) {
        StockDaily stockDaily = new StockDaily();

        try {
            String[] components = line.split(",");

            stockDaily.setDate(Constants.DATE_FORMAT.parse(components[1]));
            stockDaily.setOpen(Double.valueOf(components[2]).doubleValue());
            stockDaily.setHigh(Double.valueOf(components[3]).doubleValue());
            stockDaily.setLow(Double.valueOf(components[4]).doubleValue());
            stockDaily.setClose(Double.valueOf(components[5]).doubleValue());
            stockDaily.setVolume(retrieveVolume(components[7]));
            stockDaily.setAdjClose(Double.valueOf(components[12]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stockDaily;
    }


    public static List<StockDaily> getStockDailies(String stockId) {
        String content = FileUtil.getContent(getPath(stockId));
        List<StockDaily> stockDailies = new ArrayList<StockDaily>();

        String[] lines = content.split("\n");
        if (lines.length < 2) {
            return stockDailies;
        }

        for (int i = 1; i < lines.length; i++) {
            stockDailies.add(getStockDailyFromLine(lines[i]));
        }


        Collections.reverse(stockDailies);

        return stockDailies;
    }

    public static void main(String[] args) {

        List<StockDaily> stockDailies = getStockDailies("600651");

        System.out.println(stockDailies.get(stockDailies.size() - 1));
    }
}