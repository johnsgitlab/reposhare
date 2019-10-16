package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.ListModelService;

import com.jpw.raptor.jdbc.etf.EtfDAO;

import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.Etf;
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
public class EtfListController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    EtfDAO etfTbl;

    @Autowired
    ListModelService listService;

    @RequestMapping("/etf-list-all")
    public String etfListAllReq(Model model)
    {
        logger.debug("etfListAllReq {}", "");

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getAll();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "ALL ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-relevant")
    public String etfListRelevantReq(Model model)
    {
        logger.debug("etfListRelevantReq {}", "");

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getRelevant();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "RELEVANT ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-tracked")
    public String etfListTrackedReq(Model model)
    {
        logger.debug("etfListTrackedReq {}", "");

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getTracked();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "TRACKED ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-owned")
    public String etfListOwnedReq(Model model)
    {
        logger.debug("etfListOwnedReq {}", "");

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getOwned();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "OWNED ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-class")
    public String etfListClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfListClassReq  asset class {} ", assetClassClean);

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getOwned();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-class-type")
    public String etfListClassTypeReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="type", required=true)  String fundType,
            Model model)
    {
        String assetClassClean = null;
        String fundTypeClean   = null;

        try {
            assetClassClean = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean   = URLDecoder.decode(fundType, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfListClassTypeReq  asset class {} fund type {}", assetClassClean, fundTypeClean);

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getByAssetClassFundType(assetClassClean, fundTypeClean);

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",
                fundTypeClean.toUpperCase() + " - " +
                        assetClassClean.toUpperCase() +
                        " ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-class-type-sub")
    public String etfListClassTypeSubTypeReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="type", required=true)  String fundType,
            @RequestParam(value="sub", required=true)  String fundSubType,
            Model model)
    {
        String assetClassClean  = assetClass.replace('+', ' ');
        String fundTypeClean    = fundType.replace('+', ' ');
        String fundSubTypeClean = fundSubType.replace('+', ' ');

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean    = URLDecoder.decode(fundType, "UTF-8");
            fundSubTypeClean = URLDecoder.decode(fundSubType, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfListClassTypeSubTypeReq  asset class {} fund type {} sub type {} ",
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getByAssetClassFundTypeSubType(
                                                        assetClassClean, fundTypeClean, fundSubTypeClean);

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",
                fundSubTypeClean.toUpperCase() + " - " +
                        fundTypeClean.toUpperCase() + " - " +
                        assetClassClean.toUpperCase() +
                        " ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-class-type-sub-factor")
    public String etfListClassTypeSubTypeFactorReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="type", required=true)  String fundType,
            @RequestParam(value="sub", required=true)  String fundSubType,
            @RequestParam(value="factor", required=true)  String factor,
            Model model)
    {
        String assetClassClean  = assetClass.replace('+', ' ');
        String fundTypeClean    = fundType.replace('+', ' ');
        String fundSubTypeClean = fundSubType.replace('+', ' ');
        String factorClean      = factor.replace('+', ' ');

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
            fundTypeClean    = URLDecoder.decode(fundType, "UTF-8");
            fundSubTypeClean = URLDecoder.decode(fundSubType, "UTF-8");
            factorClean      = URLDecoder.decode(factor, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        // Get the Etfs to display
        List<Etf>  toConvert = etfTbl.getByAssetClassFundTypeSubTypeFactor(
                                            assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

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
                        " ETFs as of " + formattedDate);

        return "etf-list";
    }



    @RequestMapping("/etf-list-factors-all")
    public String etfListFactorsAllReq(
            Model model)
    {
        logger.debug("etfListFactorsAllReq  ");

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getFactorsAll();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModelFactors(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "FACTOR ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-domestic-factors-all")
    public String etfListDomesticFactorsAllReq(
            Model model)
    {
        logger.debug("etfListFactorsAllReq  ");

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getFactorsAll();

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModelDomesticFactors(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", "DOMESTIC FACTOR ETFs as of " + formattedDate);

        return "etf-list";
    }

    @RequestMapping("/etf-list-factors-class")
    public String etfListFactorClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            @RequestParam(value="factor", required=true) String assetFactor,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        String assetFactorClean = null;

        try {
            assetFactorClean  = URLDecoder.decode(assetFactor, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};


        logger.debug("etfListFactorClassReq  asset class {} ", assetClassClean);

        // Get the Etfs to display
        List<Etf> toConvert = etfTbl.getFactorAndAssetClass(assetClassClean, assetFactorClean);

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModelDomesticFactors(toConvert);

        SimpleDateFormat formatter    = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = "No Date Found";
        if ( recs.size() > 0 )
            formattedDate = formatter.format(recs.get(0).getDate());

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " FACTORS ETFs as of " + formattedDate);

        return "etf-list";
    }


    @RequestMapping("/etf-list-by-symbol")
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

        logger.debug("etfListBySymbol  asset class {} fund type {}", fileNameClean, pageTitleClean);

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

        // Get the Etfs to display
        String lineSeparator=System.lineSeparator();

        ArrayList<Etf> toConvert = new ArrayList<>(symbols.size());
        for ( String symbol : symbols) {
            Etf etf = etfTbl.get(symbol.replace(lineSeparator, "").trim().toUpperCase());
            if ( etf != null ) {
                toConvert.add(etf);
            }
        }

        // Convert to list format
        List<ListModel> recs = listService.getEtfListModel(toConvert);

        model.addAttribute("list_model", recs);
        model.addAttribute("pagetitle",  pageTitleClean);

        return "etf-list";
    }

}
