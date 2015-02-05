package com.moyang.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Scope("session")
@Controller
@RequestMapping(value = "/dailyUpdate")
public class DailyUpdateController implements Serializable {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd");

    private static final long serialVersionUID = 1L;
    private static final String VIEW_NAME = "dailyUpdate";
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ModelAndView refresh(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject("dateStr", "2000-10-11");

        return mav;
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public String get(HttpServletRequest request, String dateStr)
            throws Exception {
        return DATE_FORMAT.format(new Date());
    }
}