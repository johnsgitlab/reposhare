package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.SummaryModelService;
import com.jpw.raptor.jdbc.stock.StockDAO;

import com.jpw.raptor.model.Stock;
import com.jpw.raptor.model.StockSummaryModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



/**
 * Created by john on 11/14/18.
 */
@Controller
public class StockSummaryController {

    @Autowired
    public StockDAO stockTbl;

    @Autowired
    SummaryModelService summaryModelService;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/stock-summary-sector")
    public String stockSectorSummary ( Model model ) {

        logger.debug("stockSectorSummary ");

        // Get all stocks
        List<Stock> allStocks = stockTbl.getAll();

        // Envoke service to format data
        List<StockSummaryModel> recs = summaryModelService.getStockSectorSummary(allStocks);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle", " SECTOR - SUMMARY");

        return "stock-summary";
    }


    @RequestMapping("/stock-summary-industry")
    public String stockIndustrySummary ( Model model ) {

        logger.debug("stockIndustrySummary ");

        // Get all stocks
        List<Stock> allStocks = stockTbl.getAll();

        // Envoke service to format data
        List<StockSummaryModel> recs = summaryModelService.getStockIndustrySummary(allStocks);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle", " INDUSTRY - SUMMARY");

        return "stock-summary";
    }

}
