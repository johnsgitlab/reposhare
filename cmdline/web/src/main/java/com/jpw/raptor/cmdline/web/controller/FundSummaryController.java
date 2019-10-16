package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.SummaryModelService;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.model.Fund;
import com.jpw.raptor.model.SummaryModel;

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
 * Created by john on 4/9/18.
 */
@Controller
public class FundSummaryController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    FundDAO fundTbl;

    @Autowired
    SummaryModelService summaryModelService;

    @RequestMapping("/mf-summary-all")
    public String mfSummaryAllReq (
            Model model ) {

        logger.debug("mfSummaryAllReq {}", "");

        // Get data to summarize
        List<Fund> fundRecs = fundTbl.getAll();

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getFundTypeSummary(fundRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle",
                " ALL FUNDS SUMMARY");

        return "mf-summary";
    }


    @RequestMapping("/mf-summary-type")
    public String mfSummaryByTypeReq (
            @RequestParam(value="class", required=true) String assetClass,
            Model model ) {

        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfSummaryByTypeReq {}", assetClassClean);

        // Get data to summarize
        List<Fund> fundRecs = fundTbl.getByAssetClass(assetClassClean);

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getFundTypeSummary(fundRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - SUMMARY");

        return "mf-summary";
    }


    @RequestMapping("/mf-summary-subtype")
    public String mfSummaryBySubtypeReq (
            @RequestParam(value="class", required=true) String assetClass,
            Model model ) {

        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfSummaryBySubtypeReq {}", assetClassClean);

        // Get data to summarize
        List<Fund> fundRecs = fundTbl.getByAssetClass(assetClassClean);

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getFundSubTypeSummary(fundRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - SUMMARY");

        return "mf-summary";
    }

    @RequestMapping("/mf-summary-factor")
    public String mfSummaryFactorReq (
            @RequestParam(value="class", required=true) String assetClass,
            Model model ) {

        String assetClassClean    = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfSummaryFactorReq {}", assetClassClean);

        // Get data to summarize
        List<Fund> fundRecs = fundTbl.getFactorsByAssetClass(assetClassClean);

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getFundFactorSummary(fundRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - FACTOR SUMMARY");

        return "mf-summary-factor";
    }

}
