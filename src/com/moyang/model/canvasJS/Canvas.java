package com.moyang.model.canvasJS;

public class Canvas {

	private Title title;
    private AxisY axisY;
    private ToolTip toolTip;
    private Data data;
    
    public Canvas(String title, Data data){
    	this.title = new Title(title);
    	this.setAxisY(new AxisY());
    	this.setToolTip(new ToolTip());
    	this.data = data;
    }
    @Override
    public String toString(){
    	return "{"+title + "," + axisY + "," + toolTip + "," + data +"}";
    }
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public AxisY getAxisY() {
		return axisY;
	}

	public void setAxisY(AxisY axisY) {
		this.axisY = axisY;
	}

	public ToolTip getToolTip() {
		return toolTip;
	}

	public void setToolTip(ToolTip toolTip) {
		this.toolTip = toolTip;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}
