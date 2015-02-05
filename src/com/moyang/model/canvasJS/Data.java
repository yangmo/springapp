package com.moyang.model.canvasJS;

import java.util.ArrayList;

public class Data {

    private ArrayList<DataPoints> dataPoints;
    
    public Data( ArrayList<DataPoints> dataPoints){

    	this.setDataPoints(dataPoints);
    }
    
    @Override
    public String toString(){
    	StringBuffer sb = new StringBuffer();
    	
        for(DataPoints points : dataPoints){
			sb.append(points.toString()).append(",");
		}
    	String result  = sb.toString();
		result = result.substring(0, result.length() - 1);

		System.out.println( result );

    	return "data:[" + result + "]";
    }
    


	public ArrayList<DataPoints> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(ArrayList<DataPoints> dataPoints) {
		this.dataPoints = dataPoints;
	}


}
