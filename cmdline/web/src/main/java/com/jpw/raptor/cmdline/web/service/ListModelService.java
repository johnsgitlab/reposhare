package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListModelService {

    @Autowired
    EtfDAO etfTbl;

    @Autowired
    FundDAO fundTbl;

    @Autowired
    StockDAO stockTbl;

    public List<ListModel> getEtfListModel(List<Etf> etfs) {

        List<ListModel> result = new ArrayList<>(etfs.size() );

        for ( Etf val : etfs ) {
            ListModel model = new ListModel(
                    val.getSymbol(), val.getDate(),
                    val.getAssetClass(), val.getFundType(), val.getFundSubType(), val.getFactor(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            result.add(model);
        }

        return result;
    }


    public List<ListModel> getEtfListModelFactors(List<Etf> etfs)
    {
        List<ListModel> result = new ArrayList<>(etfs.size() );


        for ( Etf val : etfs ) {
            ListModel rec = new ListModel(
                    val.getSymbol(), val.getDate(),
                    val.getAssetClass(), val.getFundType(), val.getFundSubType(), val.getFactor(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            if ( rec.getFactor() == null || rec.getFactor().equalsIgnoreCase("") )
                rec.setFactor(new String (rec.getFundSubType()) );

            result.add(rec);
        }

        return result;
    }



    public List<ListModel> getEtfListModelDomesticFactors(List<Etf> etfs)
    {
        List<ListModel> result = new ArrayList<>(etfs.size());

        for ( Etf val : etfs ) {
            ListModel rec = new ListModel(
                    val.getSymbol(), val.getDate(),
                    val.getAssetClass(), val.getFundType(), val.getFundSubType(), val.getFactor(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            if ( rec.getFactor() == null || rec.getFactor().equalsIgnoreCase("") )
                rec.setFactor(new String (rec.getFundSubType()) );

            if ( rec.getFactor() == null || rec.getFactor().equalsIgnoreCase("") )
                rec.setFactor(new String (rec.getFundSubType()) );
            if ( rec.getAssetClass().equalsIgnoreCase("domestic") || rec.getAssetClass().equalsIgnoreCase("sector") ) {
                result.add(rec);
            }
        }

        return result;
    }

    /*   */

    public List<ListModel> getFundListModel(List<Fund> funds) {

        List<ListModel> result = new ArrayList<>(funds.size() );

        for ( Fund val : funds ) {
            ListModel model = new ListModel(
                    val.getSymbol(), val.getDate(),
                    val.getAssetClass(), val.getFundType(), val.getFundSubType(), val.getFactor(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            result.add(model);
        }

        return result;
    }

    public List<ListModel> getFundListModelFactors(List<Fund> funds)
    {
        List<ListModel> result = new ArrayList<>(funds.size() );


        for ( Fund val : funds ) {
            ListModel rec = new ListModel(
                    val.getSymbol(), val.getDate(),
                    val.getAssetClass(), val.getFundType(), val.getFundSubType(), val.getFactor(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            if ( rec.getFactor() == null || rec.getFactor().equalsIgnoreCase("") )
                rec.setFactor(new String (rec.getFundSubType()) );

            result.add(rec);
        }

        return result;
    }

    public List<ListModel> getFundListModelDomesticFactors(List<Fund> funds)
    {
        List<ListModel> result = new ArrayList<>(funds.size());

        for ( Fund val : funds ) {
            ListModel rec = new ListModel(
                    val.getSymbol(), val.getDate(),
                    val.getAssetClass(), val.getFundType(), val.getFundSubType(), val.getFactor(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            if ( rec.getFactor() == null || rec.getFactor().equalsIgnoreCase("") )
                rec.setFactor(new String (rec.getFundSubType()) );

            if ( rec.getFactor() == null || rec.getFactor().equalsIgnoreCase("") )
                rec.setFactor(new String (rec.getFundSubType()) );
            if ( rec.getAssetClass().equalsIgnoreCase("domestic") || rec.getAssetClass().equalsIgnoreCase("sector") ) {
                result.add(rec);
            }
        }

        return result;
    }

    /*  */

    public List<StockListModel> getStockListModel(List<Stock> stocks) {

        List<StockListModel> result = new ArrayList<>(stocks.size() );

        for ( Stock val : stocks ) {
            StockListModel model = new StockListModel(
                    val.getSymbol(), val.getName(), val.getSector(), val.getIndustry(),
                    val.getDate(), val.getSpIndex(), val.getDowIndex(), val.getRussellIndex(),
                    val.getYtd(), val.getOneDay(), val.getOneWeek(),
                    val.getTwoWeeks(), val.getFourWeeks(), val.getThreeMonths(),
                    val.getOneYear(), val.getMarketCap());

            result.add(model);
        }

        return result;
    }


    public List<EquityListModel> getEquityListModel(List<String> symbols) {

        List<EquityListModel> result = new ArrayList<>(symbols.size() );

        for ( String s : symbols ) {
            // Check for etf
            Etf etf = etfTbl.get(s);
            if (etf != null) {
                EquityListModel equity = new EquityListModel (
                        etf.getSymbol(), etf.getName(), etf.getDate(), etf.getYtd(),
                        etf.getOneDay(), etf.getOneWeek(), etf.getTwoWeeks(),
                        etf.getFourWeeks(), etf.getThreeMonths(), etf.getOneYear(),
                        etf.getMarketCap()
                );
                result.add(equity);
            } else {
                // check for a stock
                Stock stock = stockTbl.get(s);
                if (stock != null) {
                    EquityListModel equity = new EquityListModel (
                            stock.getSymbol(), stock.getName(), stock.getDate(), stock.getYtd(),
                            stock.getOneDay(), stock.getOneWeek(), stock.getTwoWeeks(),
                            stock.getFourWeeks(), stock.getThreeMonths(), stock.getOneYear(),
                            stock.getMarketCap()
                    );
                    result.add(equity);
                } else {
                    // check for a mutual fund
                    Fund fund = fundTbl.get(s);
                    if (fund != null) {
                        EquityListModel equity = new EquityListModel (
                                fund.getSymbol(), fund.getName(), fund.getDate(), fund.getYtd(),
                                fund.getOneDay(), fund.getOneWeek(), fund.getTwoWeeks(),
                                fund.getFourWeeks(), fund.getThreeMonths(), fund.getOneYear(),
                                fund.getMarketCap()
                        );
                        result.add(equity);
                    } else {
                        // unknown equity type
                    }
                }
            }
        }

        return result;
    }


}
