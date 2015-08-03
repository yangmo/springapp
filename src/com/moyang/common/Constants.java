package com.moyang.common;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
public class Constants {
    public static final String MOST_RECENT_TRADING_DAY = "2015-07-31";

    /**
	 * Constants for MarketPlace
	 */
	public static final String SH = "sh";
    public static final String SZ = "sz";
    

    /**
     *
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("####.00");

    /**
     *
     */
    public static final int INTERVAL = 120;


    public static final String OS_NAME = System.getProperty("os.name");
    // Different base USR directory for Mac and Linux
    public static final String USR = "/usr/local/apache-tomcat-6.0.43";
    /**
     *
     */
    public static String LOCAL_ROOT = USR + "/bin/files/";
    public static String STOCK_ROOT = USR + "/bin/files/stocks/";
    public static String STOCKS_TXT = USR + "/bin/files/Stocks.txt";

    public static void main(String[] args)  {
        System.out.println(System.getProperty("os.name") + LOCAL_ROOT);
    }
}
