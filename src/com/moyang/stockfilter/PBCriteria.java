package com.moyang.stockfilter;

import java.util.List;

import com.moyang.common.Constants;
import com.moyang.hibernate.StockDaily;

public class PBCriteria extends Criteria{

	public PBCriteria(String context) {
		super(context);
	}

	@Override
	public boolean meetCriteria(List<StockDaily> stockDailies) {
		StockDaily stockDaily = stockDailies.get(stockDailies.size() - 1);
		return stockDaily.getPB() <= Double.valueOf(context) && stockDaily.getPB() > 0;
	}

	@Override
	public String getDetail(List<StockDaily> stockDailies) {
		StockDaily stockDaily = stockDailies.get(stockDailies.size() - 1);
		return "\tPB:" + Constants.DOUBLE_FORMAT.format(stockDaily.getPB()); 
	}
	
	
}
