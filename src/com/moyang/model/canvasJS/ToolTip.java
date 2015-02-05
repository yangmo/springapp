package com.moyang.model.canvasJS;

public class ToolTip {

	private String shared;
	
	public ToolTip(){
		shared = "true";
	}
	
	public void setShared(String shared){
		this.shared = shared;
	}
	
	public String getShared(){
		return shared;
	}
	
	@Override
	public String toString(){
		return "toolTip:{shared:\"" + shared+"\"}";
	}
}
