package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.cmdline.web.service.SignalModelService;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.model.Etf;

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
public class EtfSignalController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    EtfDAO etfTbl;

    @Autowired
    SignalModelService signalModelService;

    @RequestMapping("/etf-signal-all")
    public String etfSignalAllReq(Model model)
    {
        logger.debug("etfSignalAllReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getAll();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "ALL ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-relevant")
    public String etfSignalRelevantReq(Model model)
    {
        logger.debug("etfSignalRelevantReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getRelevant();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "RELEVANT ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-tracked")
    public String etfSignalTrackedReq(Model model)
    {
        logger.debug("etfSignalTrackedReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getTracked();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "TRACKED ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-owned")
    public String etfSignalOwnedReq(Model model)
    {
        logger.debug("etfReturnOwnedReq {}", "");

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getOwned();

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", "TRACKED ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-class")
    public String etfSignalClassReq(
            @RequestParam(value="class", required=true) String assetClass,
            Model model)
    {
        String assetClassClean = null;

        try {
            assetClassClean  = URLDecoder.decode(assetClass, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        logger.debug("etfSignalClassReq  asset class {} ", assetClassClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClass(assetClassClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-class-type")
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

        logger.debug("etfSignalClassTypeReq  asset class {} fund type {}", assetClassClean, fundTypeClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClassFundType(assetClassClean, fundTypeClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase() + " ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-class-type-sub")
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

        logger.debug("etfSignalClassTypeSubTypeReq  asset class {} fund type {} sub type ",
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClassFundTypeSubType(
                assetClassClean, fundTypeClean, fundSubTypeClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle",
                assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                        + " - " + fundSubTypeClean.toUpperCase() + " ETF Signals");

        return "etf-signals";
    }

    @RequestMapping("/etf-signal-class-type-sub-factor")
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

        logger.debug("etfSignalClassTypeSubTypeFactorReq  asset class {} fund type {} sub type factor {} ",
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get the equities we are interested in
        List<Etf> equities = etfTbl.getByAssetClassFundTypeSubTypeFactor(
                assetClassClean, fundTypeClean, fundSubTypeClean, factorClean);

        // Get moving average signals
        List<SignalModel> signals = signalModelService.getEtfMovingAverageSignals(equities);

        // Sort and merge results
        //Collections.sort(signals, Comparator.comparing(SignalModel::getSymbol));

        model.addAttribute("signal", signals);
        model.addAttribute("pagetitle", assetClassClean.toUpperCase() + " - " + fundTypeClean.toUpperCase()
                + " - " + fundSubTypeClean.toUpperCase()
                + " - " + factorClean.toUpperCase() + " ETF Signals");

        return "etf-signals";
    }

}
