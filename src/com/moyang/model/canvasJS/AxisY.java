package com.moyang.model.canvasJS;

public class AxisY {

	private boolean includeZero;
	
	public AxisY(){
		includeZero = true;
	}
	
	public void setIncludeZero(boolean includeZero){
		this.includeZero = includeZero;
	}
	
	public boolean getIncludeZero(){
		return includeZero;
	}
	
	@Override
	public String toString(){
		return "axisY:{includeZero:" + includeZero + "}";
	}
}
