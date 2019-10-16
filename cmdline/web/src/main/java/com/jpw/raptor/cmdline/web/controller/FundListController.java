package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.ListModelService;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.Fund;
import com.jpw.raptor.model.ListModel;

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
public class FundListController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    FundDAO fundTbl;

    @Autowired
    ListModelService listService;

    @RequestMapping("/mf-list-all")
    public String mfListAllReq(Model model)
    {
        logger.debug("mfListAllReq {}", "");

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getAll();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "ALL FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-relevant")
    public String mfListRelevantReq(Model model)
    {
        logger.debug("mfListRelevantReq {}", "");

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getRelevant();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "RELEVANT FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-tracked")
    public String mfListTrackedReq(Model model)
    {
        logger.debug("mfListTrackedReq {}", "");

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getTracked();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "TRACKED FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-owned")
    public String mfListOwnedReq(Model model)
    {
        logger.debug("mfListOwnedReq {}", "");

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getOwned();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "OWNED FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-class")
    public String mfListClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfListClassReq  asset class {} ", assetClassClean);

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getByAssetClass(assetClassClean);

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-class-type")
    public String mfListClassTypeReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="type", required=true)  String fundType,
            Model model)
    {
        String assetClassClean = null;
        String fundTypeClean   = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean    = URLDecoder.decode(fundType, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfListClassTypeReq  asset class {} fund type {}", assetClassClean, fundTypeClean);

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getByAssetClassFundType(assetClassClean, fundTypeClean);

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",
                fundTypeClean.toUpperCase() + " - " +
                        assetClassClean.toUpperCase() +
                        " FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-class-type-sub")
    public String mfListClassTypeSubTypeReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="type", required=true)  String fundType,
            @RequestParam(value="sub", required=true)  String fundSubType,
            Model model)
    {
        String assetClassClean  = null;
        String fundTypeClean    = null;
        String fundSubTypeClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean    = URLDecoder.decode(fundType, "UTF-8");
            fundSubTypeClean = URLDecoder.decode(fundSubType, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfListClassTypeSubTypeReq  asset class {} fund type {} sub type ",
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getByAssetClassFundTypeSubType(assetClassClean, fundTypeClean, fundSubTypeClean);

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",
                fundSubTypeClean.toUpperCase() + " - " +
                        fundTypeClean.toUpperCase() + " - " +
                        assetClassClean.toUpperCase() +
                        " FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-class-type-sub-factor")
    public String mfListClassTypeSubTypeFactorReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="type", required=true)  String fundType,
            @RequestParam(value="sub", required=true)  String fundSubType,
            @RequestParam(value="factor", required=true)  String factor,
            Model model)
    {
        String assetClassClean  = null;
        String fundTypeClean    = null;
        String fundSubTypeClean = null;
        String factorClean      = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean    = URLDecoder.decode(fundType, "UTF-8");
            fundSubTypeClean = URLDecoder.decode(fundSubType, "UTF-8");
            factorClean      = URLDecoder.decode(factor, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfListClassTypeSubTypeFactorReq  asset class {} fund type {} sub type factor {}",
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getByAssetClassFundTypeSubTypeFactor(assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",
                fundSubTypeClean.toUpperCase() + " - " +
                        fundTypeClean.toUpperCase() + " - " +
                        assetClassClean.toUpperCase() + " - " +
                        factorClean.toUpperCase() +
                        " FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-factors-all")
    public String etfListFactorsAllReq(
            Model model)
    {
        logger.debug("mfListFactorsAllReq  ");

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getFactorsAll();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModelFactors(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "FACTOR Funds as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/fund-list-domestic-factors-all")
    public String fundListDomesticFactorsAllReq(
            Model model)
    {
        logger.debug("fundListFactorsAllReq  ");

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getFactorsAll();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModelDomesticFactors(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "DOMESTIC FACTOR Funds as of " + formattedDate);

        return "mf-list";
    }

    @RequestMapping("/mf-list-factors-class")
    public String mfListFactorClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfListFactorClassReq  asset class {} ", assetClassClean);

        // Get the Funds to display
        List<Fund>      toConvert   = fundTbl.getFactorsAll();

        // Convert to list format
        List<ListModel> recs = listService.getFundListModelDomesticFactors(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " FACTORS FUNDS as of " + formattedDate);

        return "mf-list";
    }


    @RequestMapping("/mf-list-by-symbol")
    public String etfListBySymbol(
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

        logger.debug("mfListBySymbol  asset class {} fund type {}", fileNameClean, pageTitleClean);

        System.out.println("*********************************************************");
        System.out.println("mfListClassTypeReq |" + fileNameClean + "| |" + pageTitleClean + "|");
        System.out.println("*********************************************************");

        //
        // Read symbols from file
        FinanceProperties   fp       = new FinanceProperties();
        Properties          prop     = fp.get();
        String              filePath = prop.getProperty("file_dir") + "/" +fileNameClean;

        List<String> symbols = null;
        try {
            symbols = Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
        }

        // Get the Funds to display
        String lineSeparator=System.lineSeparator();

        ArrayList<Fund> toConvert = new ArrayList<>(symbols.size());
        for ( String symbol : symbols) {
            Fund fund = fundTbl.get(symbol.replace(lineSeparator, "").trim().toUpperCase());
            if ( fund != null ) {
                toConvert.add(fund);
            }
        }

        // Convert to list format
        List<ListModel> recs = listService.getFundListModel(toConvert);

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",  pageTitleClean);

        return "mf-list";
    }

}
