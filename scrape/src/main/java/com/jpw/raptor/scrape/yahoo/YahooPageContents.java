package com.jpw.raptor.scrape.yahoo;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by John on 10/20/2017.
 */
@Getter
@Setter
public class YahooPageContents {


    private boolean    found;
    private String     symbol;

    private Date       lastUpdate;
    private String     family;

    private double     avgDailyVol;
    
    private Date       inception;
    private double     marketCap;
    private double     expenseRatio;
    private double     dividendYield;

    private double     basicMaterials;
    private double     consumerCyclical;
    private double     financialServices;
    private double     realestate;
    private double     consumerDefensive;
    private double     healthcare;
    private double     utilities;
    private double     communicationServices;
    private double     energy;
    private double     industrials;
    private double     technology;

    private double     alpha;
    private double     beta;
    private double     meanAnnualReturn;
    private double     rSquared;
    private double     deviation;
    private double     sharpeRatio;
    private double     treynorRatio;

    private double     pe;
    private double     pb;
    private double     ps;
    private double     pc;
    private double     earningsGrowth;
    private double     medianMarketCap;

    private double     bondMaturity;
    private double     bondDuration;
    private double     bondCreditQuality;
    private double     bondAaaPercent;
    private double     bondAaPercent;
    private double     bondAPercent;
    private double     bondBbbPercent;
    private double     bondBbPercent;
    private double     bondBPercent;
    private double     bondBelowbPercent;
    private double     bondUsPercent;
    private double     bondOtherPercent;

    private double     bondPosition;
    private double     cashPosition;
    private double     convertiblePosition;
    private double     otherPosition;
    private double     preferredPosition;
    private double     stockPosition;

    private String     topHoldings;
    private String     overview;

    // Constructor
    public YahooPageContents () {

        // Provide default values
        found = false;
        symbol = null;

        lastUpdate = null;
        family = null;

        avgDailyVol = -9999.0;
        
        inception = null;
        marketCap = -9999.0;
        expenseRatio = -9999.0;
        dividendYield = -9999.0;

        basicMaterials = 0.0;
        consumerCyclical = 0.0;
        financialServices = 0.0;
        realestate = 0.0;
        consumerDefensive = 0.0;
        healthcare = 0.0;
        utilities = 0.0;
        communicationServices = 0.0;
        energy = 0.0;
        industrials = 0.0;
        technology = 0.0;

        alpha = 0.0;
        beta = 0.0;
        meanAnnualReturn = 0.0;
        rSquared = 0.0;
        deviation = 0.0;
        sharpeRatio = 0.0;
        treynorRatio = 0.0;

        pe = 0.0;
        pb = 0.0;
        ps = 0.0;
        pc = 0.0;
        earningsGrowth = 0.0;
        medianMarketCap = 0.0;

        bondMaturity = 0.0;
        bondDuration = 0.0;
        bondCreditQuality = 0.0;
        bondAaaPercent = 0.0;
        bondAaPercent = 0.0;
        bondAPercent = 0.0;
        bondBbbPercent = 0.0;
        bondBbPercent = 0.0;
        bondBPercent = 0.0;
        bondBelowbPercent = 0.0;
        bondUsPercent = 0.0;
        bondOtherPercent = 0.0;

        bondPosition = 0.0;
        cashPosition = 0.0;
        convertiblePosition = 0.0;
        otherPosition = 0.0;
        preferredPosition = 0.0;
        stockPosition = 0.0;

        topHoldings = null;
        overview = null;
    }


    public void updateEtfFromYahoo(Etf rec)  {
        // manual entry
        //rec.setSymbol();
        //rec.setName();
        //rec.setAssetClass();
        //rec.setFundType();
        //rec.setFundSubType();
        //rec.setFactor();
        //rec.setCategory();
        //rec.setTracks();

        // auto generated
        //rec.setDate();

        rec.setLastUpdate(getLastUpdate());

        if ( getFamily() != null ) {
            rec.setFamily(getFamily());
        }

        // ETF only
        // manual entry
        // rec.setUnderlyingIndex();
        rec.setAvgDailyVol(getAvgDailyVol());

        // Fund only
        // manual entry
        //rec.setStatus();
        //rec.setMinimum();
        //rec.setFrontLoad();
        //rec.setBackLoad();

        rec.setInception(getInception());
        rec.setMarketCap(getMarketCap());
        rec.setExpenseRatio(getExpenseRatio());
        rec.setDividendYield(getDividendYield());

        // manual entry
        //rec.setMorningRating();
        //rec.setMorningStars();

        // Market Watch scrape
        //rec.setLipperTotal();
        //rec.setLipperConsistent();
        //rec.setLipperPreservation();
        //rec.setLipperTax();
        //rec.setLipperExpense();

        rec.setBasicMaterials(getBasicMaterials());
        rec.setConsumerCyclical(getConsumerCyclical());
        rec.setFinancialServices(getFinancialServices());
        rec.setRealestate(getRealestate());
        rec.setConsumerDefensive(getConsumerDefensive());
        rec.setHealthcare(getHealthcare());
        rec.setUtilities(getUtilities());
        rec.setCommunicationServices(getCommunicationServices());
        rec.setEnergy(getEnergy());
        rec.setIndustrials(getIndustrials());
        rec.setTechnology(getTechnology());

        rec.setAlpha(getAlpha());
        rec.setBeta(getBeta());
        rec.setMeanAnnualReturn(getMeanAnnualReturn());
        rec.setRSquared(getRSquared());
        rec.setDeviation(getDeviation());
        rec.setSharpeRatio(getSharpeRatio());
        rec.setTreynorRatio(getTreynorRatio());

        rec.setPe(getPe());
        rec.setPb(getPb());
        rec.setPs(getPs());
        rec.setPc(getPc());
        rec.setEarningsGrowth(getEarningsGrowth());
        rec.setMedianMarketCap(getMedianMarketCap());

        rec.setBondMaturity(getBondMaturity());
        rec.setBondDuration(getBondDuration());
        rec.setBondCreditQuality(getBondCreditQuality());
        rec.setBondAaaPercent(getBondAaaPercent());
        rec.setBondAaPercent(getBondAaPercent());
        rec.setBondAPercent(getBondAPercent());
        rec.setBondBbbPercent(getBondBbbPercent());
        rec.setBondBbPercent(getBondBbPercent());
        rec.setBondBPercent(getBondBPercent());
        rec.setBondBelowbPercent(getBondBelowbPercent());
        rec.setBondUsPercent(getBondUsPercent());
        rec.setBondOtherPercent(getBondOtherPercent());

        rec.setBondPosition(getBondPosition());
        rec.setCashPosition(getCashPosition());
        rec.setConvertiblePosition(getConvertiblePosition());
        rec.setOtherPosition(getOtherPosition());
        rec.setPreferredPosition(getPreferredPosition());
        rec.setStockPosition(getStockPosition());

        rec.setTopHoldings(getTopHoldings());
        rec.setOverview(getOverview());
    }


    public void updateFundFromYahoo(Fund rec)  {

        // manual entry
        //rec.setSymbol();
        //rec.setName();
        //rec.setAssetClass();
        //rec.setFundType();
        //rec.setFundSubType();
        //rec.setFactor();
        //rec.setCategory();
        //rec.setTracks();

        // auto generated
        //rec.setDate();

        rec.setLastUpdate(getLastUpdate());
        if ( getFamily() != null ) {
            rec.setFamily(getFamily());
        }

        // ETF only
        // manual entry
        // rec.setUnderlyingIndex();
        //rec.setAvgDailyVol(getAvgDailyVolume());

        // Fund only
        // manual entry
        //rec.setStatus();
        //rec.setMinimum();
        //rec.setFrontLoad();
        //rec.setBackLoad();

        rec.setInception(getInception());
        rec.setMarketCap(getMarketCap());
        rec.setExpenseRatio(getExpenseRatio());
        rec.setDividendYield(getDividendYield());

        // manual entry
        //rec.setMorningRating();
        //rec.setMorningStars();

        // Market Watch scrape
        //rec.setLipperTotal();
        //rec.setLipperConsistent();
        //rec.setLipperPreservation();
        //rec.setLipperTax();
        //rec.setLipperExpense();

        rec.setBasicMaterials(getBasicMaterials());
        rec.setConsumerCyclical(getConsumerCyclical());
        rec.setFinancialServices(getFinancialServices());
        rec.setRealestate(getRealestate());
        rec.setConsumerDefensive(getConsumerDefensive());
        rec.setHealthcare(getHealthcare());
        rec.setUtilities(getUtilities());
        rec.setCommunicationServices(getCommunicationServices());
        rec.setEnergy(getEnergy());
        rec.setIndustrials(getIndustrials());
        rec.setTechnology(getTechnology());

        rec.setAlpha(getAlpha());
        rec.setBeta(getBeta());
        rec.setMeanAnnualReturn(getMeanAnnualReturn());
        rec.setRSquared(getRSquared());
        rec.setDeviation(getDeviation());
        rec.setSharpeRatio(getSharpeRatio());
        rec.setTreynorRatio(getTreynorRatio());

        rec.setPe(getPe());
        rec.setPb(getPb());
        rec.setPs(getPs());
        rec.setPc(getPc());
        rec.setEarningsGrowth(getEarningsGrowth());
        rec.setMedianMarketCap(getMedianMarketCap());

        rec.setBondMaturity(getBondMaturity());
        rec.setBondDuration(getBondDuration());
        rec.setBondCreditQuality(getBondCreditQuality());
        rec.setBondAaaPercent(getBondAaaPercent());
        rec.setBondAaPercent(getBondAaPercent());
        rec.setBondAPercent(getBondAPercent());
        rec.setBondBbbPercent(getBondBbbPercent());
        rec.setBondBbPercent(getBondBbPercent());
        rec.setBondBPercent(getBondBPercent());
        rec.setBondBelowbPercent(getBondBelowbPercent());
        rec.setBondUsPercent(getBondUsPercent());
        rec.setBondOtherPercent(getBondOtherPercent());

        rec.setBondPosition(getBondPosition());
        rec.setCashPosition(getCashPosition());
        rec.setConvertiblePosition(getConvertiblePosition());
        rec.setOtherPosition(getOtherPosition());
        rec.setPreferredPosition(getPreferredPosition());
        rec.setStockPosition(getStockPosition());

        rec.setTopHoldings(getTopHoldings());
        rec.setOverview(getOverview());
    }

}

