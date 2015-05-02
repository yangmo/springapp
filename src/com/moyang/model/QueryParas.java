package com.moyang.model;

import com.moyang.api.Converter;
import com.moyang.api.Yahoo.YahooAPI;
import com.moyang.common.Constants;
import com.moyang.hibernate.StockDaily;
import com.moyang.model.canvasJS.Canvas;
import com.moyang.model.canvasJS.DataPoints;
import com.moyang.api.AverageUtil;
import com.moyang.common.StockNameUtil;
import com.moyang.model.canvasJS.Data;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class QueryParas {

	private int start;
	private int end;
    private String stockId;
	private String kAverages;

	public QueryParas(){
		start = 0;
		end = 5;
		kAverages = "5,10,30,89";
	}

	public List<StockDaily> getYahooDatumList() throws Exception{
		return YahooAPI.getHistoryBetween(getStockId(), getStart(), getEnd());
	}

	public Canvas toCanvas()throws Exception{
		List<StockDaily> datum = getYahooDatumList();

		ArrayList<DataPoints> list   = new ArrayList<DataPoints>();
		list.add(new DataPoints("Close", Converter.toDataPointList(datum)));

		String[] averages = kAverages.split(",");

		for(String average: averages) {
			int k = Integer.valueOf(average);
			DataPoints points = new DataPoints(k + "", Converter.averageToDataPointList(AverageUtil
				.getKAverage(getStockId(), k, datum.get(0).getDate(), datum.get(datum.size() - 1).getDate())));
			list.add(points);
		}

		Data data = new Data( list);
		Canvas canvas = new Canvas(URLEncoder.encode(StockNameUtil.getName(getStockId()), "UTF-8"), data);

		return canvas;
	}

	public int getStart(){
		return start;
	}

	public void setStart(int start){
		this.start = start;
	}

	public int getEnd(){
		return end;
	}

	public void setEnd(int end){
		this.end = end;
	}
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public void setKAverages(String kAverages){
		this.kAverages = kAverages;
	}
	public String getkAverages(){
		return kAverages;
	}

}
