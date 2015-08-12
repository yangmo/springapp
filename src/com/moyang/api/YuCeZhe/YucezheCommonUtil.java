package com.moyang.api.YuCeZhe;

import com.moyang.common.Constants;
import com.moyang.common.MarketplaceUtil;
import com.moyang.model.StockDaily;

import org.springframework.util.StringUtils;

/**
 * Created by moyang on 15/8/3.
 */
public class YucezheCommonUtil {
    static String getDataPath(String stockId) {
        String marketPrefix = MarketplaceUtil.getMarketplace(stockId);
        return YucezheConstants.DATA_BASE + marketPrefix + stockId + ".csv";
    }

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

    static String getStockIdFromLine(String line) {
    	
        String[] components = line.split(",");
        return components[0].substring(2);
    }

    static StockDaily getStockDailyFromLine(String line) {
    	if(StringUtils.isEmpty(line)) {
    		return null;
    	}
        StockDaily stockDaily = new StockDaily();

        try {
            String[] components = line.split(",");
            stockDaily.setId(getStockIdFromLine(line));
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
            stockDaily.setTurnover(StringUtils.isEmpty(components[11])? 0.0 : Double.valueOf(components[11]));
            stockDaily.setAdjClose(Double.valueOf(components[12]));
            if(components.length < 16) {
            	return stockDaily;
            }
            stockDaily.setPE(Double.valueOf(components[15]));
            if(components.length < 19) {
                return stockDaily;
            }
            stockDaily.setPB(Double.valueOf(components[18]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stockDaily;
    }

}
