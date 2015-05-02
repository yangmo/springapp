package com.moyang.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.JsonSerializer;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moyang.model.canvasJS.Canvas;
import com.moyang.model.canvasJS.Data;
import com.moyang.model.canvasJS.DataPoints;

import com.moyang.api.Yahoo.YahooAPI;
import com.moyang.hibernate.StockDaily;
import com.moyang.api.Converter;
import com.moyang.model.QueryParas;
@Scope("session")
@Controller
@RequestMapping(value = "/stockView")
public class StockViewController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String VIEW_NAME = "stockView";
	protected final Log logger = LogFactory.getLog(getClass());
   
    
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ModelAndView refresh(HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);

        
        ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject("paras", new QueryParas());
        List<StockDaily> datum = YahooAPI.getRecentClose("600030", 2350);
        ArrayList<DataPoints> list   = new ArrayList<DataPoints>();
        list.add(new DataPoints("Close", Converter.toDataPointList(datum)));
        Data data = new Data( list);
        Canvas canvas = new Canvas("www", data);
        mav.addObject("json",canvas.toString() );
        return mav;
    }
    
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request, QueryParas paras)
            throws Exception {
        ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject("paras", paras);
        mav.addObject("json", paras.toCanvas());
        return mav;
    }

    public static void main(String[] args){
        QueryParas paras = new QueryParas();
        paras.setEnd(111);
        paras.setKAverages("5");
        paras.setStart(0);
        paras.setStockId("600030");
        YahooHistory history = new YahooHistory("600030");
        for(StockDaily daily : history.getYahooHistory()){
            System.out.println(daily.getDate().toString());
        }
        System.out.println(JsonSerializer.serialize(new YahooHistory("600030")));
       // System.out.print(JsonSerializer.serialize(paras));
    }
}