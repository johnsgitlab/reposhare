package com.jpw.raptor.cmdline.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 8/13/18.
 */
@Controller
public class SearchEquityController {


    @RequestMapping("/search-equity")
    public String eventReq(
            @RequestParam(value="type", required=true) String equityType,
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="fundtype", required=true) String fundType,
            @RequestParam(value="symbol", required=false) String symbol,
            Model model) {

        String equityTypeClean  = null;
        String assetClassClean  = null;
        String fundTypeClean    = null;


        try {
            equityTypeClean  = URLDecoder.decode(equityType, "UTF-8");
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean    = URLDecoder.decode(fundType, "UTF-8");

        } catch (UnsupportedEncodingException ex) {};


        model.addAttribute("event_model", null);
        model.addAttribute("pagetitle", "Events from " );

        System.out.println( "***********");

        System.out.println( "***********");
        return "event-list";
    }

}
