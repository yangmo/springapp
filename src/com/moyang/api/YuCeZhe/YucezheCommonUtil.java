package com.moyang.api.YuCeZhe;

import com.moyang.common.Constants;
import com.moyang.hibernate.StockDaily;

import java.util.DoubleSummaryStatistics;

/**
 * Created by moyang on 15/8/3.
 */
public class YucezheCommonUtil {


    /**
     * Fail to parse Long if it is something like 20239900.0 or 2.3353e+11
     * @param volume
     * @return
     */
    static Long parseLong(String volume) {
        // For Volume
        if(volume.contains(".0")) {
            return Long.valueOf(volume.replace(".0", ""));
        }
        // For MarketValue
        if(volume.contains("e+")) {
            String[] components = volume.split("e+");
            return (long)(Math.pow(10, Double.valueOf(components[1])) * Double.valueOf(components[0]));
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
            stockDaily.setChange(Double.valueOf(components[6]).doubleValue());
            stockDaily.setVolume(parseLong(components[7]));
            stockDaily.setMoney(Double.valueOf(components[8]).longValue());
            stockDaily.setTradedMarketValue(Double.valueOf(components[9]).longValue());
            stockDaily.setMarketValue(Double.valueOf(components[10]).longValue());
            stockDaily.setAdjClose(Double.valueOf(components[12]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stockDaily;
    }

}
