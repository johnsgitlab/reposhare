package com.jpw.raptor.cmdline.rest.controller;

import com.jpw.raptor.jdbc.etf.EtfDAO;

import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by john on 6/10/17.
 */
@RestController
@Api(value="/rest/etf",produces ="application/json")
public class EtfRestController {
    private static final Logger logger = LoggerFactory.getLogger(EtfRestController.class);

    @Autowired
    public EtfDAO                   etfTbl;

    @Autowired
    public QuoteDAO quoteTbl;

    @RequestMapping(value = "/rest/etf/summary", method = RequestMethod.GET)
    @ApiOperation(value = "Return ETF Summary Information")
    @ApiResponses(value={
    @ApiResponse(code=200,message="Etf Retrieved",response=Etf.class),
    @ApiResponse(code=500,message="Internal Server Error"),
    })
    public Etf etfSummary(
            @RequestParam(value="symbol", required=true, defaultValue="") String symbol)
    {

        Etf rec = null;

        // Get the Fund value by time and create time series
        rec = etfTbl.get(symbol.toUpperCase());

        return rec;
        //return "gotit";
    }


    @RequestMapping(value = "/rest/etf/values", method = RequestMethod.GET)
    @ApiOperation(value = "Return all quotes for an ETF")
    @ApiResponses(value={
    @ApiResponse(code=200,message="Etf Quotes Retrieved",response=Quote.class),
    @ApiResponse(code=500,message="Internal Server Error"),
    })
    public Quote[] etfValuesReq(
            @RequestParam(value="symbol", required=true, defaultValue="") String symbol)
    {

        List<Quote>  valuesEtf  = null;

        // Get the Fund value by time and create time series
        valuesEtf = quoteTbl.getAllDesc(symbol.toUpperCase());

        // convert to array
        Quote[] valuesArray = valuesEtf.toArray(new Quote[valuesEtf.size()]);

        System.out.println();
        System.out.println("values found " + valuesEtf.size());
        System.out.println();

        return valuesArray;
        //return "gotit";
    }

}
