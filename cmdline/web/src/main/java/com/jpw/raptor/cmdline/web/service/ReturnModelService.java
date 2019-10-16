package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReturnModelService {

    @Autowired
    QuoteDAO quoteTbl;


    protected int getCurrentYear() {
        // compute current and prior year
        SimpleDateFormat formatter    = new SimpleDateFormat("yyyy");
        String           formatedDate = formatter.format(new Date());

        return Integer.parseInt(formatedDate);
    }

    protected int getPriorYear() {
        return getCurrentYear() -1;
    }

    public List<ReturnModel> getEtfReturns(List<Etf> equities) {

        // create an arrary for the data
        int         equityCount = 0;
        ArrayList<ReturnModel> returns   = new ArrayList<ReturnModel>(equities.size());

        for (Etf equity: equities){
            List<Quote> currYearQuotes  = quoteTbl.getByYearAsc(equity.getSymbol(), getCurrentYear());
            List<Quote> priorYearQuotes = quoteTbl.getByYearAsc(equity.getSymbol(), getPriorYear());

            String category = equity.getFundType().toUpperCase();

            returns.add(buildLine(equity.getSymbol(),
                    equity.getAssetClass(), equity.getFundType(), equity.getFundSubType(), equity.getFactor(),
                    currYearQuotes, getCurrentYear(), priorYearQuotes, getPriorYear() ));
        }

        return returns;
    }


    public List<ReturnModel> getFundReturns(List<Fund> equities) {

        // create an arrary for the data
        int         equityCount = 0;
        ArrayList<ReturnModel> returns   = new ArrayList<ReturnModel>(equities.size());

        for (Fund equity: equities){
            List<Quote> currYearQuotes  = quoteTbl.getByYearAsc(equity.getSymbol(), getCurrentYear());
            List<Quote> priorYearQuotes = quoteTbl.getByYearAsc(equity.getSymbol(), getPriorYear());

            String category = equity.getFundType().toUpperCase();

            returns.add(buildLine(equity.getSymbol(),
                    equity.getAssetClass(), equity.getFundType(), equity.getFundSubType(), equity.getFactor(),
                    currYearQuotes, getCurrentYear(), priorYearQuotes, getPriorYear() ));
        }

        return returns;
    }


    public ReturnModel buildLine(String symbol,
                                 String assetClass, String fundType, String fundSubType, String factor,
                                 List<Quote> currYearQuotes, int currYear, List<Quote> priorYearQuotes, int priorYear ) {

        ReturnModel         rm  = new ReturnModel();
        QuarterCloseModel   cyc = new QuarterCloseModel(currYearQuotes, currYear);
        QuarterCloseModel   pyc = new QuarterCloseModel(priorYearQuotes, priorYear);

        rm.setSymbol(symbol);
        rm.setAssetClass(assetClass);
        rm.setFundType(fundType);
        rm.setFundSubType(fundSubType);
        rm.setFactor(factor);

        // current year
        rm.setDate(cyc.getLastDate());
        if ( cyc.getYtdClose() > 0.0 && cyc.getYearStart() > 0.0 )
            rm.setCurrYtd( ((cyc.getYtdClose() - cyc.getYearStart()) /  cyc.getYearStart()) * 100.0 );

        if ( cyc.getQtr4Close() > 0.0 &&  cyc.getQtr3Close() > 0.0 )
            rm.setCurrQtr4( ((cyc.getQtr4Close() - cyc.getQtr3Close()) /  cyc.getQtr3Close()) * 100.0 );

        if ( cyc.getQtr3Close() > 0.0 && cyc.getQtr2Close() > 0.0 )
            rm.setCurrQtr3( ((cyc.getQtr3Close() - cyc.getQtr2Close()) /  cyc.getQtr2Close()) * 100.0 );

        if ( cyc.getQtr2Close() > 0.0 && cyc.getQtr1Close() > 0.0 )
            rm.setCurrQtr2( ((cyc.getQtr2Close() - cyc.getQtr1Close()) /  cyc.getQtr1Close() * 100.0) );

        if ( cyc.getQtr1Close() > 0.0 && cyc.getYearStart() > 0.0 )
            rm.setCurrQtr1( ((cyc.getQtr1Close() - cyc.getYearStart()) /  cyc.getYearStart()) * 100.0 );

        // prior year
        if ( pyc.getYtdClose() > 0.0 && pyc.getYearStart() > 0.0 )
            rm.setPriorYtd( ((pyc.getYtdClose() - pyc.getYearStart()) /  pyc.getYearStart()) * 100.0 );

        if ( pyc.getQtr4Close() > 0.0 && pyc.getQtr3Close() > 0.0 )
            rm.setPriorQtr4( ((pyc.getQtr4Close() - pyc.getQtr3Close()) /  pyc.getQtr3Close()) * 100.0 );

        if ( pyc.getQtr3Close() > 0.0 && pyc.getQtr2Close() > 0.0 )
            rm.setPriorQtr3( ((pyc.getQtr3Close() - pyc.getQtr2Close()) /  pyc.getQtr2Close()) * 100.0 );

        if ( pyc.getQtr2Close() > 0.0 && pyc.getQtr1Close() > 0.0 )
            rm.setPriorQtr2( ((pyc.getQtr2Close() - pyc.getQtr1Close()) /  pyc.getQtr1Close()) * 100.0 );

        if ( pyc.getQtr1Close() > 0.0 && pyc.getYearStart() > 0.0 )
            rm.setPriorQtr1( ((pyc.getQtr1Close() - pyc.getYearStart()) /  pyc.getYearStart()) * 100.0 );

        return rm;
    }

}
