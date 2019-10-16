package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SummaryModelService {

    public List<StockSummaryModel> getStockSectorSummary(List<Stock> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, StockSummaryModel> map = new HashMap<>();

        // Iterate over the database records and update the summary results
        for (Stock rec : recs) {

            // get the key for the record
            String name = rec.getSector();

            // if key not found generate a new record
            StockSummaryModel s = map.get(name);
            if ( s == null ) {
                s = new StockSummaryModel();
                s.setDate(rec.getDate());
                s.setSector(rec.getSector());
                //s.setIndustry(rec.getIndustry());
                s.setIndustry("");
            }

            // Update the record
            //if ( s.getDate().before( rec.getDate() ) )
            //    s.setDate(rec.getDate());
            s.setYtd(s.getYtd() + rec.getYtd());
            s.setOneDay(s.getOneDay() + rec.getOneDay());
            s.setOneWeek(s.getOneWeek() + rec.getOneWeek());
            s.setTwoWeeks(s.getTwoWeeks() + rec.getTwoWeeks());
            s.setFourWeeks(s.getFourWeeks() + rec.getFourWeeks());
            s.setThreeMonths(s.getThreeMonths() + rec.getThreeMonths());
            s.setOneYear(s.getOneYear() + rec.getOneYear());
            s.setCount(s.getCount() + 1);
            map.put(name, s);
        }


        // Create a list to hold the records
        ArrayList<StockSummaryModel> list = new ArrayList<>(map.size());

        // Iterate over the map
        for (Map.Entry<String, StockSummaryModel> entry : map.entrySet()) {

            StockSummaryModel s = (StockSummaryModel) entry.getValue();

            // normalize the values
            double count = (double) s.getCount();
            s.setYtd(Math.round( ((s.getYtd() / count) * 100.0 ) / 100.0));
            s.setOneDay(Math.round( ((s.getOneDay() / count) * 100.0 ) / 100.0));
            s.setOneWeek(Math.round( ((s.getOneWeek() / count) * 100.0 ) / 100.0));
            s.setTwoWeeks(Math.round( ((s.getTwoWeeks() / count) * 100.0 ) / 100.0));
            s.setFourWeeks(Math.round( ((s.getFourWeeks() / count) * 100.0 ) / 100.0));
            s.setThreeMonths(Math.round( ((s.getThreeMonths() / count) * 100.0 ) / 100.0));
            s.setOneYear(Math.round( ((s.getOneYear() / count) * 100.0 ) / 100.0));

            list.add(s);
        }

        return list;
    }

    public List<StockSummaryModel> getStockIndustrySummary(List<Stock> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, StockSummaryModel> map = new HashMap<>();

        // Iterate over the database records and update the summary results
        for (Stock rec : recs) {

            // get the key for the record
            String name = rec.getIndustry();

            // if key not found generate a new record
            StockSummaryModel s = map.get(name);
            if ( s == null ) {
                s = new StockSummaryModel();
                s.setDate(rec.getDate());
                //s.setSector(rec.getSector());
                s.setSector("");
                s.setIndustry(rec.getIndustry());
            }

            // Update the record
            //if ( s.getDate().before( rec.getDate() ) )
            //    s.setDate(rec.getDate());
            s.setYtd(s.getYtd() + rec.getYtd());
            s.setOneDay(s.getOneDay() + rec.getOneDay());
            s.setOneWeek(s.getOneWeek() + rec.getOneWeek());
            s.setTwoWeeks(s.getTwoWeeks() + rec.getTwoWeeks());
            s.setFourWeeks(s.getFourWeeks() + rec.getFourWeeks());
            s.setThreeMonths(s.getThreeMonths() + rec.getThreeMonths());
            s.setOneYear(s.getOneYear() + rec.getOneYear());
            s.setCount(s.getCount() + 1);
            map.put(name, s);
        }


        // Create a list to hold the records
        ArrayList<StockSummaryModel> list = new ArrayList<>(map.size());

        // Iterate over the map
        for (Map.Entry<String, StockSummaryModel> entry : map.entrySet()) {

            StockSummaryModel s = (StockSummaryModel) entry.getValue();

            // normalize the values
            double count = (double) s.getCount();
            s.setYtd(Math.round( ((s.getYtd() / count) * 100.0 ) / 100.0));
            s.setOneDay(Math.round( ((s.getOneDay() / count) * 100.0 ) / 100.0));
            s.setOneWeek(Math.round( ((s.getOneWeek() / count) * 100.0 ) / 100.0));
            s.setTwoWeeks(Math.round( ((s.getTwoWeeks() / count) * 100.0 ) / 100.0));
            s.setFourWeeks(Math.round( ((s.getFourWeeks() / count) * 100.0 ) / 100.0));
            s.setThreeMonths(Math.round( ((s.getThreeMonths() / count) * 100.0 ) / 100.0));
            s.setOneYear(Math.round( ((s.getOneYear() / count) * 100.0 ) / 100.0));

            list.add(s);
        }

        return list;
    }

    /******************/


    protected String generateTypeKey(String assetClass, String fundType, String fundSubType, String factor) {

        // Build the composite key
        StringBuilder sb = new StringBuilder();

        if ( assetClass != null ) sb.append(assetClass);
        sb.append(";");
        if ( fundType != null ) sb.append(fundType);

        return sb.toString();
    }


    protected String generateSubTypeKey(String assetClass, String fundType, String fundSubType, String factor) {

        // Build the composite key
        StringBuilder sb = new StringBuilder();

        if ( assetClass != null ) sb.append(assetClass);
        sb.append(";");
        if ( fundType != null ) sb.append(fundType);
        sb.append(";");
        if ( fundSubType != null ) sb.append(fundSubType);

        return sb.toString();
    }


    protected String generateFactorKey(String assetClass, String fundType, String fundSubType, String factor) {

        // Build the composite key
        StringBuilder sb = new StringBuilder();
/*
        if ( assetClass != null ) sb.append(assetClass);
        sb.append(";");
        if ( fundType != null ) sb.append(fundType);
        sb.append(";");
        if ( fundSubType != null ) sb.append(fundSubType);
        sb.append(";");
        if ( factor != null ) sb.append(factor);
*/
        if ( assetClass != null ) sb.append(assetClass);
        sb.append(";");
        if ( factor != null ) sb.append(factor);
        return sb.toString();
    }

    public List<SummaryModel> generateSummaryModel(HashMap<String, SummaryData> map) {

        // Create a list to hold the the model results
        ArrayList<SummaryModel> list = new ArrayList<>(map.size());

        // Iterate over the map
        for (Map.Entry<String, SummaryData> entry : map.entrySet()) {
            SummaryData rec = (SummaryData) entry.getValue();

            SummaryModel s = new SummaryModel();
            s.setDate(rec.getDate());
            s.setAssetClass(rec.getAssetClass());
            s.setFundType(rec.getFundTpye());
            s.setFundSubType(rec.getFundSubTpye());
            s.setFactor(rec.getFactor());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            s.setCount(rec.getCount());

            list.add(s);
        }

        return list;
    }

    /**********************************/

    public List<SummaryModel> getEtfTypeSummary(List<Etf> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, SummaryData> map = new HashMap<String, SummaryData>();

        // Iterate over the database records and update the summary results
        for (Etf rec : recs) {

            // get the key for the record
            String name = generateTypeKey(rec.getAssetClass(), rec.getFundType(), rec.getFundSubType(), rec.getFactor());

            // if key not found generate a new record
            SummaryData s = map.get(name);
            if ( s == null ) {
                s = new SummaryData();
                s.setDate(rec.getDate());
                s.setAssetClass(rec.getAssetClass());
                s.setFundType(rec.getFundType());
                s.setFundSubType("");
                s.setFactor("");
            }

            // Update the record
            if ( s.getDate().before( rec.getDate() ) )
                s.setDate(rec.getDate());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            map.put(name, s);
        }

        return generateSummaryModel(map);
    }


    public List<SummaryModel> getEtfSubTypeSummary(List<Etf> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, SummaryData> map = new HashMap<String, SummaryData>();

        // Iterate over the database records and update the summary results
        for (Etf rec : recs) {

            // get the key for the record
            String name = generateSubTypeKey(rec.getAssetClass(), rec.getFundType(), rec.getFundSubType(), rec.getFactor());

            // if key not found generate a new record
            SummaryData s = map.get(name);
            if ( s == null ) {
                s = new SummaryData();
                s.setDate(rec.getDate());
                s.setAssetClass(rec.getAssetClass());
                s.setFundType(rec.getFundType());
                s.setFundSubType(rec.getFundSubType());
                s.setFactor("");
            }

            // Update the record
            if ( s.getDate().before( rec.getDate() ) )
                s.setDate(rec.getDate());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            map.put(name, s);
        }

        return generateSummaryModel(map);
    }


    public List<SummaryModel> getEtfFactorSummary(List<Etf> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, SummaryData> map = new HashMap<String, SummaryData>();

        // Iterate over the database records and update the summary results
        for (Etf rec : recs) {

            // get the key for the record
            String name = generateFactorKey(rec.getAssetClass(), rec.getFundType(), rec.getFundSubType(), rec.getFactor());

            // if key not found generate a new record
            SummaryData s = map.get(name);
            if ( s == null ) {
                s = new SummaryData();
                s.setDate(rec.getDate());
                s.setAssetClass(rec.getAssetClass());
                s.setFundType("");
                s.setFundSubType("");
                s.setFactor(rec.getFactor());
            }

            // Update the record
            if ( s.getDate().before( rec.getDate() ) )
                s.setDate(rec.getDate());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            map.put(name, s);
        }

        return generateSummaryModel(map);
    }


    /**********************************/


    public List<SummaryModel> getFundTypeSummary(List<Fund> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, SummaryData> map = new HashMap<String, SummaryData>();

        // Iterate over the database records and update the summary results
        for (Fund rec : recs) {

            // get the key for the record
            String name = generateTypeKey(rec.getAssetClass(), rec.getFundType(), rec.getFundSubType(), rec.getFactor());

            // if key not found generate a new record
            SummaryData s = map.get(name);
            if ( s == null ) {
                s = new SummaryData();
                s.setDate(rec.getDate());
                s.setAssetClass(rec.getAssetClass());
                s.setFundType(rec.getFundType());
                s.setFundSubType("");
                s.setFactor("");
            }

            // Update the record
            if ( s.getDate().before( rec.getDate() ) )
                s.setDate(rec.getDate());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            map.put(name, s);
        }

        return generateSummaryModel(map);
    }


    public List<SummaryModel> getFundSubTypeSummary(List<Fund> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, SummaryData> map = new HashMap<String, SummaryData>();

        // Iterate over the database records and update the summary results
        for (Fund rec : recs) {

            // get the key for the record
            String name = generateSubTypeKey(rec.getAssetClass(), rec.getFundType(), rec.getFundSubType(), rec.getFactor());

            // if key not found generate a new record
            SummaryData s = map.get(name);
            if ( s == null ) {
                s = new SummaryData();
                s.setDate(rec.getDate());
                s.setAssetClass(rec.getAssetClass());
                s.setFundType(rec.getFundType());
                s.setFundSubType(rec.getFundSubType());
                s.setFactor("");
            }

            // Update the record
            if ( s.getDate().before( rec.getDate() ) )
                s.setDate(rec.getDate());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            map.put(name, s);
        }

        return generateSummaryModel(map);
    }


    public List<SummaryModel> getFundFactorSummary(List<Fund> recs) {

        // Create a Data structure to hold the summary results
        HashMap<String, SummaryData> map = new HashMap<String, SummaryData>();

        // Iterate over the database records and update the summary results
        for (Fund rec : recs) {

            // get the key for the record
            String name = generateFactorKey(rec.getAssetClass(), rec.getFundType(), rec.getFundSubType(), rec.getFactor());

            // if key not found generate a new record
            SummaryData s = map.get(name);
            if ( s == null ) {
                s = new SummaryData();
                s.setDate(rec.getDate());
                s.setAssetClass(rec.getAssetClass());
                s.setFundType("");
                s.setFundSubType("");
                s.setFactor(rec.getFactor());
            }

            // Update the record
            if ( s.getDate().before( rec.getDate() ) )
                s.setDate(rec.getDate());
            s.setYtd(rec.getYtd());
            s.setOneDay(rec.getOneDay());
            s.setOneWeek(rec.getOneWeek());
            s.setTwoWeeks(rec.getTwoWeeks());
            s.setFourWeeks(rec.getFourWeeks());
            s.setThreeMonths(rec.getThreeMonths());
            s.setOneYear(rec.getOneYear());
            map.put(name, s);
        }

        return generateSummaryModel(map);
    }

}
