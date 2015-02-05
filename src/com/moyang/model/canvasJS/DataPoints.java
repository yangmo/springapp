package com.moyang.model.canvasJS;

import java.util.ArrayList;
public class DataPoints {
	private String type;
	private boolean showInLegend;
	private String name;
	private int markerSize;
	private ArrayList<DataPoint> datapoints;
	
    @Override
    public String toString(){
		StringBuffer sb = new StringBuffer();

		sb.append("{");
		sb.append("type:\"" + type + "\",");
		sb.append("showInLegend:" + showInLegend + ",");
		sb.append("name:\"" + name + "\",");
		sb.append("markerSizer:" + String.valueOf(markerSize) + ",dataPoints:[");

    	for(DataPoint dp : datapoints){
    		sb.append(dp).append(",");
    	}
    	String result = "";
    	if(datapoints.size()!=0){
     		 result += sb.toString().substring(0, sb.toString().length() - 1);
    	}
    	
    	return  ""+result + "]}";
    }
	
	public DataPoints(String name, ArrayList<DataPoint> datapoints){
		type = "spline";
		showInLegend = true;
		this.setName(name);
		markerSize = 0;
		this.setDatapoints(datapoints);
	}
	public ArrayList<DataPoint> getDatapoints() {
		return datapoints;
	}
	public void setDatapoints(ArrayList<DataPoint> datapoints) {
		this.datapoints = datapoints;
	}
	public void addDataPoints(DataPoints points){
		datapoints.addAll(points.getDatapoints());
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isShowInLegend() {
		return showInLegend;
	}
	public void setShowInLegend(boolean showInLegend) {
		this.showInLegend = showInLegend;
	}
	public int getMarkerSize() {
		return markerSize;
	}
	public void setMarkerSize(int markerSize) {
		this.markerSize = markerSize;
	}
}
