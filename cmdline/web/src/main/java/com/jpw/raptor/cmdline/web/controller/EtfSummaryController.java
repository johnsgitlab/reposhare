package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.SummaryModelService;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.model.Etf;
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
 * Created by john on 4/8/18.
 */
@Controller
public class EtfSummaryController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    EtfDAO etfTbl;

    @Autowired
    SummaryModelService summaryModelService;

    @RequestMapping("/etf-summary-all")
    public String etfSummaryAllReq (
            Model model ) {

        logger.debug("etfSummaryAllReq {}", "");

        // Get data to summarize
        List<Etf> etfRecs = etfTbl.getAll();

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getEtfTypeSummary(etfRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle", " ALL ETFS SUMMARY");

        return "etf-summary";
    }


    @RequestMapping("/etf-summary-type")
    public String etfSummaryByTypeReq (
            @RequestParam(value="class", required=true) String assetClass,
            Model model ) {

        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfSummaryClassReq {}", assetClassClean);

        // Get data to summarize
        List<Etf> etfRecs = etfTbl.getByAssetClass(assetClassClean);

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getEtfTypeSummary(etfRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " - SUMMARY");

        return "etf-summary";
    }


    @RequestMapping("/etf-summary-subtype")
    public String etfSummaryBySubtypeReq (
            @RequestParam(value="class", required=true) String assetClass,
            Model model ) {

        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfSummaryClassReq {}", assetClassClean);

        // Get data to summarize
        List<Etf> etfRecs = etfTbl.getByAssetClass(assetClassClean);

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getEtfSubTypeSummary(etfRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " - SUMMARY");

        return "etf-summary";
    }

    @RequestMapping("/etf-summary-factor")
    public String etfSummaryFactorReq (
            @RequestParam(value="class", required=true) String assetClass,
            Model model ) {

        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("SummaryFactorReq {}", assetClassClean);

        // Get data to summarize
        List<Etf> etfRecs = etfTbl.getFactorsByAssetClass(assetClassClean);

        // Envoke service to format data
        List<SummaryModel> recs = summaryModelService.getEtfFactorSummary(etfRecs);

        model.addAttribute("summ_model", recs);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " - FACTOR SUMMARY");

        return "etf-summary-factor";
    }

}
