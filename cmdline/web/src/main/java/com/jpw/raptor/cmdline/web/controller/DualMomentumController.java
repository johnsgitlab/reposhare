package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.DualMomentumService;
import com.jpw.raptor.cmdline.web.service.ListModelService;

import com.jpw.raptor.jdbc.etf.EtfDAO;

import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.DualMomentumModel;
import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.ListModel;

import com.jpw.raptor.model.Quote;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by john on 4/5/18.
 */
@Controller
public class DualMomentumController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    QuoteDAO tbl;

    @Autowired
    DualMomentumService service;


    @RequestMapping("/dual-momentum")
    public String dualMomentum(
            @RequestParam(value="ref", required=true)  String refSymbol,
            @RequestParam(value="file", required=true) String fileName,
            @RequestParam(value="date", required=false) String date,
            Model model)
    {
        //
        // Compute absolute return for reference equity
        List<Quote> refQuotes;
        if ( date != null ) {
            refQuotes = tbl.getYearsWorthDescFromDate(refSymbol.toUpperCase(), date);
        } else {
            refQuotes = tbl.getYearsWorthDesc(refSymbol.toUpperCase());
        }
        DualMomentumModel   refModel    = new DualMomentumModel();
        service.computeAbsoluteReturn(refModel, refQuotes);

        //
        // process file with symbols
        String fileNameClean  = null;
        try {
            fileNameClean  = URLDecoder.decode(fileName,  "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        // Get symbols from file
        FinanceProperties   fp   = new FinanceProperties();
        Properties          prop = fp.get();
        String              filePath = prop.getProperty("file_dir") + "/" +fileNameClean;

        List<DualMomentumModel> modelList = service.getSymbolsFromFile(filePath);

        //
        // process each symbol generating relative performance
        List<Quote> quotes;
        for ( DualMomentumModel dmm : modelList ) {
            if ( date != null ) {
                quotes = tbl.getYearsWorthDescFromDate(dmm.getSymbol().toUpperCase(), date);
            } else {
                quotes = tbl.getYearsWorthDesc(dmm.getSymbol().toUpperCase());
            }
            service.computeAbsoluteReturn(dmm, quotes);
            service.computeRelativeReturn(dmm, refModel);
        }

        // Generate page title
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(refQuotes.get(0).getDate());

        String pageTitle = "Relative performance to " + refSymbol.toUpperCase() + " from " + date_string;

        model.addAttribute("list_model", modelList);
        model.addAttribute("pagetitle",  pageTitle);

        return "dual-momentum";
    }

}
