package com.moyang.dataparser;

import com.moyang.common.FileUtil;

/**
 * Created by yangmo on 15-5-10.
 */
public class ZhongxiaobanParser {
    public static void main(String[] args){

        String content = FileUtil.getContent("src/com/moyang/dataparser/zhongxiaoban");

        String[] lines = content.split("\n");
        for(String line : lines){
            System.out.println("00" + line);
        }
    }
}
