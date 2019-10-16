package com.jpw.raptor.cmdline.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpw.raptor.algorithm.PivotPoint;
import com.jpw.raptor.cmdline.web.service.AveragesModelService;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.*;

import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by john on 11/13/18.
 */
@Controller
public class DetailController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EtfDAO etfTbl;

    @Autowired
    private FundDAO fundTbl;

    @Autowired
    private StockDAO stockTbl;

    @Autowired
    private AveragesModelService avgService;

    @Autowired
    private QuoteDAO quoteTbl;


    @RequestMapping("/equity-detail")
    public String eventReq(
            @RequestParam(value="symbol", required=true) String symbol,
            Model model) {

        // Obtain ETF details
        Etf etfRec = etfTbl.get(symbol.toUpperCase());
        if ( etfRec != null ) {
            return etfReq( symbol, etfRec, model);
        }

        Stock stockRec = stockTbl.get(symbol.toUpperCase());
        if ( stockRec != null ) {
            // call stock request
            return stockReq( symbol, stockRec, model);
        }

        Fund fundRec = fundTbl.get(symbol.toUpperCase());
        if ( fundRec != null ) {
            return fundReq( symbol, fundRec, model);
        } else {
            return "error";
        }
    }


    public String etfReq( String symbol, Etf rec, Model model)
    {

        // Get quotes and compute the moving averages
        List<AveragesModel> valuesEtf = avgService.generateList(symbol.toUpperCase());

        // Get the holdings
        Gson gson = new GsonBuilder().setLenient().create();
        HoldingModel[] topHoldings = gson.fromJson(rec.getTopHoldings(), HoldingModel[].class);

        // Convert to list
        List<HoldingModel> list = Arrays.asList(topHoldings);

        // Format percent
        DecimalFormat df = new DecimalFormat("#.##");
        for ( HoldingModel holding : topHoldings ) {
            holding.setHoldingPercent( Double.valueOf(df.format( holding.getHoldingPercent() * 100.0 )));
        }

        model.addAttribute("symbol", symbol);
        model.addAttribute("nasdaq_info", rec);
        model.addAttribute("holding_list", list);

        // reverse the list from descending dates to ascending dates
        Collections.reverse(valuesEtf);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( valuesEtf.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", valuesEtf.subList(251, valuesEtf.size()) );
        } else {
            model.addAttribute("data", valuesEtf);
        }

        /*
            The following code is for testing only
         */
        PivotPoint ppFactory = new PivotPoint();

        //
        // get the quotes
        List<Quote> quotes      = quoteTbl.getAllDesc(symbol.toUpperCase());
        List<Pp>    pivotList   = ppFactory.generatePivotPoints(quotes);

        // revese the results
        Collections.reverse(pivotList);

        if ( pivotList.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("testdata", pivotList.subList(251, pivotList.size()) );
        } else {
            model.addAttribute("testdata", pivotList);
        }

        return "etf-detail";
    }


    public String fundReq( String symbol, Fund rec, Model model)
    {

        // Get quotes and compute the moving averages
        List<AveragesModel> valuesFund = avgService.generateList(symbol.toUpperCase());

        // Get the holdings
        Gson gson = new GsonBuilder().setLenient().create();;
        HoldingModel[] topHoldings = gson.fromJson(rec.getTopHoldings(), HoldingModel[].class);

        // Convert to list
        List<HoldingModel> list = Arrays.asList(topHoldings);

        // Format percent
        DecimalFormat df = new DecimalFormat("#.##");
        for ( HoldingModel holding : topHoldings ) {
            holding.setHoldingPercent( Double.valueOf(df.format( holding.getHoldingPercent() * 100.0 )));
        }

        model.addAttribute("symbol", symbol);
        model.addAttribute("equity_info", rec);
        model.addAttribute("holding_list", list);

        // reverse the list from descending dates to ascending dates
        Collections.reverse(valuesFund);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( valuesFund.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", valuesFund.subList(251, valuesFund.size()) );
        } else {
            model.addAttribute("data", valuesFund);
        }

        return "mf-detail";
    }


    public String stockReq( String symbol, Stock rec, Model model)
    {

        // Get quotes and compute the moving averages
        List<AveragesModel> valuesStock = avgService.generateList(symbol.toUpperCase());

        StringBuilder sb = new StringBuilder();
        if ( rec.getSpIndex() != null && !rec.getSpIndex().isEmpty() ) {
            sb.append("S&P").append(rec.getSpIndex());
        }

        if ( rec.getDowIndex() != null && !rec.getDowIndex().isEmpty() ) {
            if ( sb.length() > 0 )
                sb.append("; ");
            sb.append("Dow ").append(WordUtils.capitalize(rec.getDowIndex())).append(' ');
        }

        if ( rec.getRussellIndex() != null && !rec.getRussellIndex().isEmpty() ) {
            if ( sb.length() > 0 )
                sb.append("; ");
            sb.append("Russell ").append(rec.getRussellIndex());
        }

        model.addAttribute("symbol", symbol);
        model.addAttribute("stock_info", rec);
        model.addAttribute("indexes", sb.toString());

        // reverse the list from descending dates to ascending dates
        Collections.reverse(valuesStock);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( valuesStock.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", valuesStock.subList(251, valuesStock.size()) );
        } else {
            model.addAttribute("data", valuesStock);
        }

        return "stock-detail";
    }

}
