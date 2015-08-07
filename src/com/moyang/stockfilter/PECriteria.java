package com.moyang.stockfilter;

import java.util.List;

import com.moyang.common.Constants;
import com.moyang.model.StockDaily;

public class PECriteria extends Criteria{

	public PECriteria(String context) {
		super(context);
	}

	@Override
	public boolean meetCriteria(List<StockDaily> stockDailies) {
		StockDaily stockDaily = stockDailies.get(stockDailies.size() - 1);
		return stockDaily.getPE() <= Double.valueOf(context) && stockDaily.getPE() > 0;
	}

	@Override
	public String getDetail(List<StockDaily> stockDailies) {
		StockDaily stockDaily = stockDailies.get(stockDailies.size() - 1);
		return "\tPE:" + Constants.DOUBLE_FORMAT.format(stockDaily.getPE()); 
	}
	
	
}
