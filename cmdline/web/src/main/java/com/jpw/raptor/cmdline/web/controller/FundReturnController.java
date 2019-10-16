package com.jpw.raptor.cmdline.web.controller;


import com.jpw.raptor.cmdline.web.service.ReturnModelService;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.model.Fund;
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
public class FundReturnController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    FundDAO fundTbl;

    @Autowired
    ReturnModelService returnModelService;

    @RequestMapping("/mf-return-all")
    public String mfReturnAllReq(Model model)
    {
        logger.debug("mfReturnAllReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getAll();

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "ALL FUND Returns");

        return "mf-returns";
    }

    @RequestMapping("/mf-return-relevant")
    public String mfReturnRelevantReq(Model model)
    {
        logger.debug("mfReturnRelevantReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getRelevant();

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "RELEVANT FUND Returns");

        return "mf-returns";
    }

    @RequestMapping("/mf-return-tracked")
    public String mfReturnTrackedReq(Model model)
    {
        logger.debug("mfReturnTrackedReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getTracked();

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "TRACKED FUND Returns");

        return "mf-returns";
    }


    @RequestMapping("/mf-return-owned")
    public String mfReturnOwnedReq(Model model)
    {
        logger.debug("mfReturnOwnedReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getOwned();

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", "OWNED FUND Returns");

        return "mf-returns";
    }

    @RequestMapping("/mf-return-class")
    public String mfReturnClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfReturnClassReq  asset class {} ", assetClassClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClass(assetClassClean);

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " FUND Returns");

        return "mf-returns";
    }

    @RequestMapping("/mf-return-class-type")
    public String mfReturnClassTypeReq(
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

        logger.debug("mfReturnClassTypeReq  asset class {} fund type {}", assetClassClean, fundTypeClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClassFundType(assetClassClean, fundTypeClean);

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase() + " FUND Returns");

        return "mf-returns";
    }

    @RequestMapping("/mf-return-class-type-sub")
    public String mfReturnClassTypeSubTypeReq(
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

        logger.debug("mfReturnClassTypeSubTypeReq  asset class {} fund type {} sub type ",
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClassFundTypeSubType(
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                        + " - " + fundSubTypeClean.toUpperCase() + " FUND Returns");

        return "mf-returns";
    }

    @RequestMapping("/mf-return-class-type-sub-factor")
    public String mfReturnClassTypeSubTypeFactorReq(
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

        logger.debug("mfReturnClassTypeSubTypeFactorReq  asset class {} fund type {} sub type factor {} ",
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClassFundTypeSubTypeFactor(
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // get the returns
        List<ReturnModel> recs = returnModelService.getFundReturns(equities);

        model.addAttribute("retrn", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                        + " - " + fundSubTypeClean.toUpperCase()
                        + " - " + factorClean.toUpperCase() + " FUND Returns");

        return "mf-returns";
    }

}
