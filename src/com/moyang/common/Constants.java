package com.moyang.common;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
public class Constants {
	/**
	 * Constants for MarketPlace
	 */
	public static final String SH = "sh";
    public static final String SZ = "sz";
    
    /**
     * 
     */
    public static final String LOCAL_ROOT = "/usr/local/apache-tomcat-6.0.43/bin/files/";
    public static final String STOCK_ROOT = "/usr/local/apache-tomcat-6.0.43/bin/files/stocks/";
    public static final String STOCKS_TXT = "/usr/local/apache-tomcat-6.0.43/bin/files/Stocks.txt";
    /**
     *
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("####.00");

    /**
     *
     */
    public static final int INTERVAL = 120;


    public static final String LATEST_DAY = "2015-05-08";

    public static void main(String[] args) throws Exception{
        System.out.println(DATE_FORMAT.parse("2003-01-09"));
    }
}
