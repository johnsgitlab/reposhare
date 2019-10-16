package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.algorithm.EquityPerformance;
import com.jpw.raptor.cmdline.web.service.RelativePerformanceModelService;
import com.jpw.raptor.jdbc.quote.QuoteDAO;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Rp;
import com.jpw.raptor.model.RelativePerformanceModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 7/28/18.
 */
@Controller
public class PerformanceController {

    @Autowired
    QuoteDAO quoteTbl;

    @Autowired
    RelativePerformanceModelService rpms;

    @RequestMapping("/relative-performance")
    public String relativePerformanceReq(
            @RequestParam(value="ref", required=true)       String ref,
            @RequestParam(value="symbol", required=true)    String symbol,
            @RequestParam(value="date", required=false)     String date,
            Model model)
    {
        String validDate;

        // generate a valide date if none is provided
        if ( date.equalsIgnoreCase("") ) {
            // todays date formatted as string
            Date                today               = new Date();
            SimpleDateFormat    formatDate          = new SimpleDateFormat("yyyy-MM-dd");
            String              dateString          = formatDate.format(today);

            // todays MM-dd formatted as string
            int                 indexToSplit        = dateString.indexOf('-');
            String              monthDay            = dateString.substring(indexToSplit);

            // prior year as int
            SimpleDateFormat    formatYear          = new SimpleDateFormat("yyyy");
            String              formatedYear        = formatYear.format(today);
            int                 currentYear         = Integer.parseInt(formatedYear);
            int                 priorYear           = currentYear - 1;

            // format the result
            validDate =  String.valueOf(priorYear) + monthDay;
        } else {
            validDate = date;
        }

        List<Quote>                     refQuotes       = quoteTbl.getForPerformanceDesc(ref.toUpperCase(), validDate);
        List<Quote>                     equityQuotes    = quoteTbl.getForPerformanceDesc(symbol.toUpperCase(), validDate);

        List<RelativePerformanceModel>  relativeModel   = rpms.computeRelativePerformanceModel(refQuotes, equityQuotes);

        model.addAttribute("ref", ref);
        model.addAttribute("symbol", symbol);
        model.addAttribute("data", relativeModel);

        return "relative-performance";
    }
}
