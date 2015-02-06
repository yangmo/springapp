package com.moyang.controllers;

import com.moyang.api.Converter;
import com.moyang.api.YahooAPI;
import com.moyang.api.YahooDatum;
import com.moyang.model.QueryParas;
import com.moyang.model.StockFilterParas;
import com.moyang.model.canvasJS.Canvas;
import com.moyang.model.canvasJS.Data;
import com.moyang.model.canvasJS.DataPoints;
import com.moyang.stockfilter.SimilarKAverageCriteria;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangmo on 15-2-6.
 */
@Scope("session")
@Controller
@RequestMapping(value = "/stockFilter")
public class StockFilterController implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String VIEW_NAME = "stockFilter";
    protected final Log logger = LogFactory.getLog(getClass());


    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ModelAndView refresh(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject("paras", new StockFilterParas());

        return mav;
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request, StockFilterParas paras)
            throws Exception {
        ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject("paras", paras);
        mav.addObject("json", URLEncoder.encode(paras.getFilteredStockDetail(), "UTF-8"));
        return mav;
    }
}
