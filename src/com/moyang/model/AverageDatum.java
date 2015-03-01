package com.moyang.model;

import com.moyang.api.Datum;

/**
 * Created by yangmo on 15-1-21.
 */
public class AverageDatum  extends Datum {

    public AverageDatum(double val, String dateStr){
        super(val, dateStr);
    }

    public int compareTo(AverageDatum obj){
        return compareDateStr(getDateStr(), obj.getDateStr());
    }

    public static int compareDateStr(String a, String b){
        String[] aArr = a.split("-");
        String[] bArr = b.split("-");
        int aSum = Integer.valueOf(aArr[0])*10000 + Integer.valueOf(aArr[1]) * 100 +Integer.valueOf(aArr[2]);
        int bSum = Integer.valueOf(bArr[0])*10000 + Integer.valueOf(bArr[1]) * 100 +Integer.valueOf(bArr[2]);

        return aSum - bSum;
    }

    @Override
    public String toString(){
        return getDateStr() + "\t" + getVal();
    }
}
