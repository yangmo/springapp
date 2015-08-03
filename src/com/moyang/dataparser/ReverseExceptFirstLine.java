package com.moyang.dataparser;

import java.io.File;
import java.io.IOException;

import com.moyang.common.FileUtil;

public class ReverseExceptFirstLine {
	private static final String BASE = "data/stock data/";
	public static void reverse(String path) throws IOException {
		StringBuffer sb = new StringBuffer();
		String contents = FileUtil.getContent(path);
		String[] lines = contents.split("\n");
		sb.append(lines[0]+"\n");
		for(int i = lines.length - 1; i>= 1; i--) {
			sb.append(lines[i]+"\n");
		}
		
		FileUtil.writeToFile(path, sb.toString());
	}

	public static void main(String[] args) throws IOException {
        File[] files = FileUtil.getFilesUnderDir(BASE);
        for(File file : files) {
        	String path = file.getAbsolutePath();
        	System.out.println(path);
        	reverse(path);
        }
        
	}
}
