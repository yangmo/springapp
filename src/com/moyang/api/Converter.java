package com.moyang.api;
import java.text.SimpleDateFormat;
import java.util.*;

import com.moyang.hibernate.StockDaily;
import com.moyang.model.canvasJS.DataPoint;
import com.moyang.model.AverageDatum;

public class Converter {

	static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
	public static ArrayList<DataPoint> toDataPointList(List<StockDaily> data){
		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
		int count = 0;
		for(StockDaily datum : data){
			result.add(new DataPoint(datum.getDate(), datum.getAdjClose()));
		}
		
		return result;
	}

	public static ArrayList<DataPoint> averageToDataPointList(List<AverageDatum> data){
		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
		int count = 0;
		for(AverageDatum datum : data){
			result.add(new DataPoint(datum.getDate(), datum.getVal()));
		}

		return result;
	}
}
