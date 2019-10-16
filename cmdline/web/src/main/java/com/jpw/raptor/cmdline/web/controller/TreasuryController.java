package com.jpw.raptor.cmdline.web.controller;


import com.jpw.raptor.cmdline.web.service.TreasuryModelService;
import com.jpw.raptor.jdbc.treasury.TreasuryDAO;
import com.jpw.raptor.model.Treasury;

import com.jpw.raptor.model.TreasuryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by john on 12/9/18.
 */
@Controller
public class TreasuryController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // this controller displays the treasury yield difference between different length treasuries
    // The data for all treasuries are sent to the web page and it decides what to display

    @Autowired
    TreasuryDAO treasuryTbl;

    @Autowired
    TreasuryModelService treasuryModelService;

    @RequestMapping("/treasury3-30")
    public String treasury3_30Req(Model model)
    {
        logger.debug("treasury3_30Req ");

        // terms used are hard code into web page

        // Get the data in ascending order by dates
        // note this is different from the quotes
        List<Treasury> toConvert = treasuryTbl.getAll();

        // convert data to Treasury model
        List<TreasuryModel> quotes = treasuryModelService.getTreasuryModel(toConvert);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( quotes.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", quotes.subList(251, quotes.size()) );
        } else {
            model.addAttribute("data", quotes);
        }

        return "treasury3-30";
    }


    @RequestMapping("/treasury-short")
    public String treasuryShortDetailReq(Model model)
    {
        logger.debug("treasuryShortDetailReq ");

        /// terms used are hard code into web page

        // Get the data in ascending order by dates
        // note this is different from the quotes
        List<Treasury> toConvert = treasuryTbl.getAll();

        // convert data to Treasury model
        List<TreasuryModel> quotes = treasuryModelService.getTreasuryModel(toConvert);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( quotes.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", quotes.subList(251, quotes.size()) );
        } else {
            model.addAttribute("data", quotes);
        }

        //System.out.println();
        //System.out.println("values found " + quotes.size());
        //System.out.println();

        return "treasury-short";
    }

    @RequestMapping("/treasury-long")
    public String treasuryLongDetailReq(Model model)
    {
        logger.debug("treasuryLongDetailReq ");

        // terms used are hard code into web page

        // Get the data in ascending order by dates
        // note this is different from the quotes
        List<Treasury> toConvert = treasuryTbl.getAll();

        // convert data to Treasury model
        List<TreasuryModel> quotes = treasuryModelService.getTreasuryModel(toConvert);

        // 260 week days - 9 holidays equals 251 trading days in a year
        // 3 years equals 753 trading days
        if ( quotes.size() > 251 ) {
            // skip the first years worth
            model.addAttribute("data", quotes.subList(251, quotes.size()) );
        } else {
            model.addAttribute("data", quotes);
        }

        //System.out.println();
        //System.out.println("values found " + quotes.size());
        //System.out.println();

        return "treasury-long";
    }
}
