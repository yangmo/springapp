package com.moyang.model.canvasJS;

public class DataPoint {
	private Object x;
	private Object y;

	public DataPoint(Object x, Object y){
		this.setX(x);
		this.setY(y);
	}

	@Override
	public String toString(){
		return "{x:\"" + x.toString() + "\"," + "y:" + y.toString() +"}";
	}
	
	public Object getX() {
		return x;
	}

	public void setX(Object x) {
		this.x = x;
	}

	public Object getY() {
		return y;
	}

	public void setY(Object y) {
		this.y = y;
	}
	
}
