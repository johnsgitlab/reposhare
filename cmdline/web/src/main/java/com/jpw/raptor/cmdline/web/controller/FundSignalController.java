package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.SignalModelService;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.model.Fund;

import com.jpw.raptor.model.SignalModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 4/9/18.
 */
@Controller
public class FundSignalController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    FundDAO fundTbl;

    @Autowired
    SignalModelService signalModelService;

    @RequestMapping("/mf-signal-all")
    public String mfSignalAllReq(Model model)
    {
        logger.debug("mfSignalAllReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getAll();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "ALL FUND Signals");

        return "mf-signals";
    }

    @RequestMapping("/mf-signal-relevant")
    public String mfSignalRelevantReq(Model model)
    {
        logger.debug("mfSignalRelevantReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getRelevant();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "RELEVANT FUND Signals");

        return "mf-signals";
    }

    @RequestMapping("/mf-signal-tracked")
    public String mfSignalTrackedReq(Model model)
    {
        logger.debug("mfSignalTrackedReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getTracked();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "TRACKED FUND Signals");

        return "mf-signals";
    }


    @RequestMapping("/mf-signal-owned")
    public String mfSignalOwnedReq(Model model)
    {
        logger.debug("mfSignalTrackedReq {}", "");

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getOwned();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "OWNED FUND Signals");

        return "mf-signals";
    }

    @RequestMapping("/mf-signal-class")
    public String etfSignalClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = assetClass.replace('+', ' ');

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("mfSignalClassReq  asset class {} ", assetClassClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClass(assetClassClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " FUND Signals");

        return "mf-signals";
    }

    @RequestMapping("/mf-signal-class-type")
    public String etfSignalClassTypeReq(
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

        logger.debug("mfSignalClassTypeReq  asset class {} fund type {}", assetClassClean, fundTypeClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClassFundType(assetClassClean, fundTypeClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase() + " FUND Signals");

        return "mf-signals";
    }

    @RequestMapping("/mf-signal-class-type-sub")
    public String etfSignalClassTypeSubTypeReq(
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

        logger.debug("mfSignalClassTypeSubTypeReq  asset class {} fund type {} sub type ",
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClassFundTypeSubType(
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                        + " - " + fundSubTypeClean.toUpperCase() + " FUND Signals");

        return "mf-signals";
    }

    @RequestMapping("/mf-signal-class-type-sub-factor")
    public String etfSignalClassTypeSubTypeFactorReq(
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

        logger.debug("mfSignalClassTypeSubTypeFactorReq  asset class {} fund type {} sub type factor {} ",
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get the equities we are interested in
        List<Fund> equities = fundTbl.getByAssetClassFundTypeSubTypeFactor(
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getFundMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                + " - " + fundSubTypeClean.toUpperCase()
                + " - " + factorClean.toUpperCase() + " FUND Signals");

        return "mf-signals";
    }

}
