package com.moyang.dataparser;

import com.moyang.common.FileUtil;

import java.io.File;

/**
 * Get raw data from
 * http://www.szse.cn/szseWeb/FrontController.szse?ACTIONID=8&CATALOGID=1743_zb&tab1PAGENUM=11&tab1PAGECOUNT=48&tab1RECORDCOUNT=480&ENCODE=1&TABKEY=tab1
 */
public class ShenZhenParser {
    public static void main(String[] args){


        System.out.println(convertTo(FileUtil.getContent("src/com/moyang/dataparser/sz_original")));
    }

    static String convertTo(String content){
        StringBuffer bf = new StringBuffer("");

        for(String tmp : content.split("\n")){
            int split = tmp.indexOf("\t");
            if(split==-1){
                continue;
            }
            System.out.println(tmp + "\t" );
            String id = tmp.substring(0, split);
            bf.append(getId(tmp, split) + "\t" + getName(tmp, split) + "\n");
        }

        return bf.toString();
    }

    static String getName(String tmp, int split){
        String name = tmp.substring(split, tmp.length());
        name = name.trim();
        return name.replaceAll(" ", "");
    }

    static String getId(String tmp, int split){
        String id = tmp.substring(0, split);
        int length = id.length();
        for(int i = length; i < 6; i ++){
            id = "0" + id;
        }
        return id;
    }
}
