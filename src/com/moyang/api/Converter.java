package com.moyang.api;
import java.text.SimpleDateFormat;
import java.util.*;

import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.model.canvasJS.DataPoint;
import com.moyang.model.AverageDatum;

public class Converter {

	static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
	public static ArrayList<DataPoint> toDataPointList(List<YahooDatum> data){
		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
		int count = 0;
		for(YahooDatum datum : data){
			result.add(new DataPoint(datum.getDateStr(), datum.getAdjClose()));
		}
		
		return result;
	}

	public static ArrayList<DataPoint> averageToDataPointList(List<AverageDatum> data){
		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
		int count = 0;
		for(AverageDatum datum : data){
			result.add(new DataPoint(datum.getDateStr(), datum.getVal()));
		}

		return result;
	}
}
