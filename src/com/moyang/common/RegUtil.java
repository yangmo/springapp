package com.moyang.common;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
    private static final Pattern PRICE_PATTERN = Pattern.compile("[0-9]{1,2}\\.[0-9]{2}");
    private static final Pattern HANZI_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");
    
    public static ArrayList<String> getPrices(String msg){
    	ArrayList<String> result = new ArrayList<String>();
        Matcher m = PRICE_PATTERN.matcher(msg);
        while (m.find()) {
            result.add(m.group());
        }

        return result;
    }
    
    public static String getName(String stockId){

    	 Matcher m = HANZI_PATTERN.matcher(stockId);
    	 StringBuffer sb = new StringBuffer();
         while (m.find()) {
             sb.append(m.group());
         }
         return sb.toString();
    }
    
    public static void main(String[] args) throws Exception{
    	System.out.println(getName("sdfsdfsdfsdf˫����"));
    }
}
