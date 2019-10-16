package com.jpw.raptor.cmdline.web.controller;


import com.jpw.raptor.cmdline.web.service.AveragesModelService;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

/**
 * Created by john on 4/10/18.
 */
@Controller
public class IndexDetailController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    IndexDAO indexTbl;

    @Autowired
    QuoteDAO quoteTbl;

    @Autowired
    private AveragesModelService avgService;


    @RequestMapping("/indexes-performance")
    public String indexPerfomanceDetailReq(
            @RequestParam(value="symbol", required=true) String symbol,
            Model model)
    {
        logger.debug("indexPerfomanceDetailReq {}", symbol);

        // Obtain details
        Index rec = indexTbl.get(symbol.toUpperCase());

        // Get quotes and compute the moving averages
        List<AveragesModel> valuesIndex = avgService.generateList(symbol.toUpperCase());

        model.addAttribute("symbol", symbol);
        model.addAttribute("index_info", rec);

        // reverse the list from descending dates to ascending dates
        Collections.reverse(valuesIndex);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( valuesIndex.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", valuesIndex.subList(251, valuesIndex.size()) );
        } else {
            model.addAttribute("data", valuesIndex);
        }

        return "indexes-performance";
    }


    @RequestMapping("/indexes-data")
    public String indexDataDetailReq(
            @RequestParam(value="symbol", required=true) String symbol,
            Model model)
    {
        logger.debug("indexDataDetailReq {}", symbol);

        // Obtain details
        Index rec = indexTbl.get(symbol.toUpperCase());

        // Get all the quotes
        List<Quote> quotes  = quoteTbl.getAllDesc(symbol.toUpperCase());

        // reverse the list from descending dates to ascending dates
        Collections.reverse(quotes);

        model.addAttribute("symbol", symbol);
        model.addAttribute("index_info", rec);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( quotes.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", quotes.subList(251, quotes.size()) );
        } else {
            model.addAttribute("data", quotes);
        }

        return "indexes-data";
    }


    @RequestMapping("/indexes-datalog")
    public String indexDataDetailLogReq(
            @RequestParam(value="symbol", required=true) String symbol,
            Model model)
    {
        logger.debug("indexDataDetailReq {}", symbol);

        // Obtain details
        Index rec = indexTbl.get(symbol.toUpperCase());

        // Get all the quotes
        List<Quote> quotes  = quoteTbl.getAllDesc(symbol.toUpperCase());

        // reverse the list from descending dates to ascending dates
        Collections.reverse(quotes);

        model.addAttribute("symbol", symbol);
        model.addAttribute("index_info", rec);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( quotes.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", quotes.subList(251, quotes.size()) );
        } else {
            model.addAttribute("data", quotes);
        }

        return "indexes-datalog";
    }

    @RequestMapping("/indexes-datama")
    public String indexDataMaReq(
            @RequestParam(value="symbol", required=true) String symbol,
            Model model)
    {
        logger.debug("indexPerfomanceDetailReq {}", symbol);

        // Obtain details
        Index rec = indexTbl.get(symbol.toUpperCase());

        // Get quotes and compute the moving averages
        List<AveragesModel> valuesIndex = avgService.generateList(symbol.toUpperCase());

        // reverse the list from descending dates to ascending dates
        Collections.reverse(valuesIndex);

        model.addAttribute("symbol", symbol);
        model.addAttribute("index_info", rec);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( valuesIndex.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", valuesIndex.subList(251, valuesIndex.size()) );
        } else {
            model.addAttribute("data", valuesIndex);
        }

        return "indexes-datama";
    }

}
