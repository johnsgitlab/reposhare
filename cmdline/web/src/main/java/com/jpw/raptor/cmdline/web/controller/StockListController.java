package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.ListModelService;

import com.jpw.raptor.jdbc.stock.StockDAO;

import com.jpw.raptor.model.Stock;
import com.jpw.raptor.model.StockListModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by john on 11/13/18.
 */
@Controller
public class StockListController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    StockDAO stockTbl;

    @Autowired
    ListModelService listService;

    @RequestMapping("/stock-list-all")
    public String stockListAllReq(Model model)
    {
        logger.debug("stockListAllReq {}", "");

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getAll();

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "ALL Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-relevant")
    public String stockListRelevantReq(Model model)
    {
        logger.debug("stockListRelevantReq {}", "");

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getRelevant();

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "RELEVANT Stocks as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/stock-list-tracked")
    public String stockListTrackedReq(Model model)
    {
        logger.debug("stockListTrackedReq {}", "");

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getTracked();

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "TRACKED Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-owned")
    public String stockListOwnedReq(Model model)
    {
        logger.debug("stockListOwnedReq {}", "");

        // Get the Stocks to display
        List<Stock>          toConvert  = stockTbl.getOwned();

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "OWNED Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-sp")
    public String stockListSpReq(
            @RequestParam(value="index", required=true) String index,
            Model model)
    {
        String indexClean = index.replace('+', ' ');

        logger.debug("stockListSpReq  asset class {} ", indexClean);

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getBySpIndex(indexClean);

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "S&P " + indexClean.toUpperCase() + " Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-dow")
    public String stockListDowReq(
            @RequestParam(value="index", required=true) String index,
            Model model)
    {
        String indexClean = index.replace('+', ' ');

        logger.debug("stockListDowReq  asset class {} ", indexClean);

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getByDowIndex(indexClean);

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "DOW " + indexClean.toUpperCase() + " Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-russell")
    public String stockListRussellReq(
            @RequestParam(value="index", required=true) String index,
            Model model)
    {
        String indexClean = index.replace('+', ' ');

        logger.debug("stockListRussellReq  asset class {} ", indexClean);

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getByRussellIndex(indexClean);

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "RUSSELL " + indexClean.toUpperCase() + " Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-sector")
    public String stockListSectorReq(
            @RequestParam(value="sector", required=true) String sector,
            Model model) {

        String sectorClean = sector.replace('+', ' ');

        try {
            sectorClean  = URLDecoder.decode(sector, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("stockListSectorReq  asset class {} ", sectorClean);

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getBySector(sectorClean);

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "Sector " + sectorClean.toUpperCase() + " Stocks as of " + formattedDate);

        return "stock-list";
    }


    @RequestMapping("/stock-list-industry")
    public String stockListIndustryReq(
            @RequestParam(value="industry", required=true) String industry,
            Model model) {

        String industryClean = industry.replace('+', ' ');

        try {
            industryClean  = URLDecoder.decode(industry, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("stockListSectorReq  asset class {} ", industryClean);

        // Get the Stocks to display
        List<Stock> toConvert = stockTbl.getByIndustry(industryClean);

        // Convert to list format
        List<StockListModel> recs = listService.getStockListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 ) {
            if ( recs.get(0).getDate() != null )
                formattedDate = formatter.format(recs.get(0).getDate());
        }

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "Industry " + industryClean.toUpperCase() + " Stocks as of " + formattedDate);

        return "stock-list";
    }

}
