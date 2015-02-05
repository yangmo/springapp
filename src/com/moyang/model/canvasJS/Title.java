package com.moyang.model.canvasJS;

public class Title {

	private String text;
	public Title(String title){
		text = title;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	@Override
	public String  toString(){
		return "title:{text:\"" + text + "\"}";
	}
}
