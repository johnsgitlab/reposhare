package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.ListModelService;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class EquityListController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    ListModelService listService;

    @RequestMapping("/equity-list-by-symbol")
    public String equityListBySymbol(
            @RequestParam(value="file", required=true) String fileName,
            @RequestParam(value="title", required=true)  String pageTitle,
            Model model)
    {
        String fileNameClean  = null;
        String pageTitleClean = null;

        try {
            fileNameClean  = URLDecoder.decode(fileName,  "UTF-8");
            pageTitleClean = URLDecoder.decode(pageTitle, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        String lineSeparator = System.lineSeparator();

        logger.debug("equityListBySymbol  file name {} title {}", fileNameClean, pageTitleClean);

        //
        // Read symbols from file
        FinanceProperties   fp       = new FinanceProperties();
        Properties          prop     = fp.get();
        String              filePath = prop.getProperty("file_dir") + "/" +fileNameClean;

        List<String> lines = null;
        try {
            // Get symbols from file
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
        }

        // Convert lines read to usable symbols
        ArrayList<String> symbols = new ArrayList<>(lines.size());
        for ( String line : lines ) {
            symbols.add( line.replace(lineSeparator, "").trim().toUpperCase() );
        }

        // Generate equity list
        List<EquityListModel> recs = listService.getEquityListModel(symbols);

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",  pageTitleClean);

        return "equity-list";
    }

}
