package com.moyang.api;

import com.moyang.common.MarketplaceUtil;

/**
 * Created by yangmo on 15-2-6.
 */
public class XueQiuLink {
    public static final String XUE_QIU_BASE="http://xueqiu.com/S/";
    public static String getLink(String stockId){
        String url = XUE_QIU_BASE + MarketplaceUtil.getMarketplace(stockId).toUpperCase() + stockId;


        return "<a href=\"" + url + "\">雪球</a>";
    }

    public static void main(String[] args){
        System.out.println(getLink("600030"));
    }

}
