package com.jpw.raptor.cmdline.rest.controller;

import com.jpw.raptor.jdbc.fund.FundDAO;

import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by john on 6/10/17.
 */
@RestController
public class FundRestController {

    private static final Logger logger = LoggerFactory.getLogger(FundRestController.class);

    @Autowired
    public FundDAO fundTbl;

    @Autowired
    public QuoteDAO quoteTbl;


    @RequestMapping(value = "/rest/fund/summary", method = RequestMethod.GET)
    @ApiOperation(value = "Return Mutual Fund Summary Information")
    public Fund fundSummary(
            @RequestParam(value="symbol", required=true, defaultValue="") String symbol)
    {

        Fund mf      = null;

        // Get the Fund value by time and create time series
        mf = fundTbl.get(symbol.toUpperCase());

        return mf;
        //return "gotit";
    }


    @RequestMapping(value = "/rest/fund/values", method = RequestMethod.GET)
    @ApiOperation(value = "Return all values for a Mutual Fund")
    public Quote[] fundValuesReq(
            @RequestParam(value="symbol", required=true, defaultValue="") String symbol)
    {

        List<Quote>  	valuesMf      = null;

        // Get the Fund value by time and create time series
        valuesMf = quoteTbl.getAllDesc(symbol.toUpperCase());

        // convert to array
        Quote[] valuesArray = valuesMf.toArray(new Quote[valuesMf.size()]);

        System.out.println();
        System.out.println("values found " + valuesMf.size());
        System.out.println();

        return valuesArray;
        //return "gotit";
    }

}
