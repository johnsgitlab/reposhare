package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.ReturnModelService;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.ReturnModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by john on 4/8/18.
 */
@Controller
public class EtfReturnController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    EtfDAO etfTbl;

    @Autowired
    ReturnModelService returnModelService;

    @RequestMapping("/etf-return-all")
    public String etfReturnAllReq(Model model)
    {
        logger.debug("etfReturnAllReq {}", "");

        // Get the Etfs to display
        List<Etf> equities = etfTbl.getAll();

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "ALL ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-relevant")
    public String etfReturnRelevantReq(Model model)
    {
        logger.debug("etfReturnRelevantReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getRelevant();

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "RELEVANT ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-tracked")
    public String etfReturnTrackedReq(Model model)
    {
        logger.debug("etfReturnTrackedReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getTracked();

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "TRACKED ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-owned")
    public String etfReturnOwneddReq(Model model)
    {
        logger.debug("etfReturnOwneddReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getOwned();

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "OWNED ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-class")
    public String etfReturnClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfReturnClassReq  asset class {} ", assetClassClean);

        // Generate return model
        List<Etf> equities = etfTbl.getByAssetClass(assetClassClean);

        // get the returns
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-class-type")
    public String etfReturnClassTypeReq(
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

        logger.debug("etfReturnClassTypeReq  asset class {} fund type {}", assetClassClean, fundTypeClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClassFundType(assetClassClean, fundTypeClean);

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase() + " ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-class-type-sub")
    public String etfReturnClassTypeSubTypeReq(
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

        logger.debug("etfReturnClassTypeSubTypeReq  asset class {} fund type {} sub type ",
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClassFundTypeSubType(
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                        + " - " + fundSubTypeClean.toUpperCase() + " ETF Returns");

        return "etf-returns";
    }

    @RequestMapping("/etf-return-class-type-sub-factor")
    public String etfReturnClassTypeSubTypeFactorReq(
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

        logger.debug("etfReturnClassTypeSubTypeFactorReq  asset class {} fund type {} sub type factor {} ",
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClassFundTypeSubTypeFactor(
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Generate return model
        List<ReturnModel> recs = returnModelService.getEtfReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                        + " - " + fundSubTypeClean.toUpperCase()
                        + " - " + factorClean.toUpperCase() + " ETF Returns");

        return "etf-returns";
    }

}
