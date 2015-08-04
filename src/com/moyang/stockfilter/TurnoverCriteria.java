package com.moyang.stockfilter;

import java.util.List;

import com.moyang.common.Constants;
import com.moyang.hibernate.StockDaily;

public class TurnoverCriteria extends Criteria{

	public TurnoverCriteria(String context) {
		super(context);
	}

	@Override
	public boolean meetCriteria(List<StockDaily> stockDailies) {
		StockDaily stockDaily = stockDailies.get(stockDailies.size() - 1);
		return stockDaily.getTurnover() >= Double.valueOf(context) && stockDaily.getTurnover() > 0;
	}

	@Override
	public String getDetail(List<StockDaily> stockDailies) {
		StockDaily stockDaily = stockDailies.get(stockDailies.size() - 1);
		return "\tTurnover:" + Constants.DOUBLE_FORMAT.format(stockDaily.getTurnover()); 
	}
	
	
}
