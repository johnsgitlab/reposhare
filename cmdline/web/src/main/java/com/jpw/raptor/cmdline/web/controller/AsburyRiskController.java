package com.jpw.raptor.cmdline.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.jpw.raptor.algorithm.AdvanceDeclineLine;
import com.jpw.raptor.algorithm.OnBalanceVolume;
import com.jpw.raptor.algorithm.PivotPoint;
import com.jpw.raptor.algorithm.RateOfChange;

import com.jpw.raptor.cmdline.web.service.AsburyRiskService;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.hiyieldspread.HiYieldSpreadDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.*;

import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by john on 11/13/18.
 */
@Controller
public class AsburyRiskController {

    public static final int DAYS = 21;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private HiYieldSpreadDAO spreadTbl;

    @Autowired
    private FundDAO fundTbl;

    @Autowired
    private StockDAO stockTbl;

    @Autowired
    private AsburyRiskService service;

    @Autowired
    private QuoteDAO quoteTbl;


    @RequestMapping("/asbury-risk")
    public String asburyHealthReq(Model model) {

        Roc                 rocRec;
        Obv                 obvRec;
        Adl                 adlRec;
        AsburyListModel     listRec;
        AsburySummaryModel  summary = new AsburySummaryModel();

        // get SPY quotes
        List<Quote> spyQuotes = quoteTbl.getAllDesc("SPY");

        //
        // Generate SPY performance
        //
        List<AsburyListModel> spyPerf = service.getQuoteList("SPY", spyQuotes, DAYS);

        // Summarize results
        listRec = spyPerf.get(0);
        if ( listRec.getVal() >= listRec.getSma() ) {
            summary.setSpyPerf("Positive");
        } else {
            summary.setSpyPerf("Negative");
        }

        // Truncate the results to one year and reverse the order
        if ( spyPerf.size() > 252 ) {
            List<AsburyListModel> spyPerfModel = new ArrayList<AsburyListModel>(spyPerf.subList(0, 251));
            Collections.reverse(spyPerfModel);
            model.addAttribute("spyperf", spyPerfModel);
        } else {
            Collections.reverse(spyPerf);
            model.addAttribute("spyperf", spyPerf);
        }

        //
        // Generate SPY rate of change
        //
        RateOfChange roc = new RateOfChange();
        List<Roc> spyRoc = roc.generateRocList(spyQuotes, DAYS);

        // Summarize results
        rocRec = spyRoc.get(0);
        if ( rocRec.getValue() >= 0 ) {
            summary.setSpyRoc("Positive");
        } else {
            summary.setSpyRoc("Negative");
        }

        // Truncate the results to one year and reverse the order
        if ( spyRoc.size() > 252 ) {
            List<Roc> spyRocModel = new ArrayList<Roc>(spyRoc.subList(0, 251));
            Collections.reverse(spyRocModel);
            model.addAttribute("spyroc", spyRocModel);
        } else {
            Collections.reverse(spyPerf);
            model.addAttribute("spyroc", spyRoc);
        }

        //
        // Generate SPY JNK relative performance
        //
        List<Quote> jnkQuotes = quoteTbl.getAllDesc("JNK");
        List<AsburyListModel> spyJnk = service.getRelativePerformanceList(spyQuotes, jnkQuotes, DAYS);

        // Summarize results
        listRec = spyJnk.get(0);
        if (listRec.getVal() >= listRec.getSma() ) {
            summary.setSpyJnk("Positive");
        } else {
            summary.setSpyJnk("Negative");
        }

        // Truncate the results to one year and reverse the order
        if ( spyJnk.size() > 252 ) {
            List<AsburyListModel> spyJnkModel = new ArrayList<AsburyListModel>(spyJnk.subList(0, 251));
            Collections.reverse(spyJnkModel);
            model.addAttribute("spyjnk", spyJnkModel);
        } else {
            Collections.reverse(spyJnk);
            model.addAttribute("spyjnk", spyJnk);
        }

        //
        // Generate SPY on balance volume
        //
        OnBalanceVolume obv = new OnBalanceVolume();
        List<Obv> spyObv = obv.generateObv(spyQuotes, DAYS);

        // Summarize results
        obvRec = spyObv.get(0);
        if (obvRec.getVal() >= obvRec.getSma() ) {
            summary.setSpyObv("Positive");
        } else {
            summary.setSpyObv("Negative");
        }

        // Truncate the results to one year and reverse the order
        if ( spyObv.size() > 252 ) {
            List<Obv> spyObvModel = new ArrayList<Obv>(spyObv.subList(0, 251));
            Collections.reverse(spyObvModel);
            model.addAttribute("spyobv", spyObvModel);
        } else {
            Collections.reverse(spyObv);
            model.addAttribute("spyobv", spyObv);
        }

        //
        // Generate Advance Decline
        //
        List<Quote> addnQuotes = quoteTbl.getAllDesc("ADDN");
        AdvanceDeclineLine adl = new AdvanceDeclineLine();
        List<Adl> nyseAdl = adl.generateAdl(addnQuotes, DAYS);

        // Summarize results
        adlRec = nyseAdl.get(0);
        if (adlRec.getVal() >= adlRec.getSma() ) {
            summary.setAdvdecl("Positive");
        } else {
            summary.setAdvdecl("Negative");
        }

        // Truncate the results to one year and reverse the order
        if ( nyseAdl.size() > 252 ) {
            List<Adl> nyseAdlModel = new ArrayList<Adl>(nyseAdl.subList(0, 251));
            Collections.reverse(nyseAdlModel);
            model.addAttribute("advdecl", nyseAdlModel);
        } else {
            Collections.reverse(nyseAdl);
            model.addAttribute("advdecl", nyseAdl);
        }

        // Generate high yield spreads
        List<HiYieldSpread> spreadQuotes = spreadTbl.getAllDesc();
        List<AsburyListModel> spread = service.getHiYieldSpreadList(spreadQuotes, DAYS);

        // Summarize results
        listRec = spread.get(0);
        if (listRec.getVal() < listRec.getSma() ) {
            summary.setSpread("Positive");
        } else {
            summary.setSpread("Negative");
        }

        // Truncate the results to one year and reverse the order
        if ( spread.size() > 252 ) {
            List<AsburyListModel> spreadsModel = new ArrayList<AsburyListModel>(spread.subList(0, 251));
            Collections.reverse(spreadsModel);
            model.addAttribute("spread", spreadsModel);
        } else {
            Collections.reverse(spyJnk);
            model.addAttribute("spread", spread);
        }

        //
        // Generate VIX
        //
        List<Quote> vixQuotes         = quoteTbl.getAllDesc("VIX");
        List<AsburyListModel> vixPerf = service.getQuoteList("VIX", vixQuotes, DAYS);

        // Summarize results
        listRec = vixPerf.get(0);
        if ( listRec.getVal() >= listRec.getSma() ) {
            summary.setVixPerf("Negative");
        } else {
            summary.setVixPerf("Positive");
        }

        // Truncate the results to one year and reverse the order
        if ( vixPerf.size() > 252 ) {
            List<AsburyListModel> vixPerfModel = new ArrayList<AsburyListModel>(vixPerf.subList(0, 251));
            Collections.reverse(vixPerfModel);
            model.addAttribute("vix", vixPerfModel);
        } else {
            Collections.reverse(vixPerf);
            model.addAttribute("vix", vixPerf);
        }

        // Add summary to the model
        model.addAttribute("summary", summary);

        return "asbury-risk";
    }

}
