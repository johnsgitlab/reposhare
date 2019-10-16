package com.jpw.raptor.jdbc.stock;

import com.jpw.raptor.model.Stock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by john on 11/3/18.
 */
public class StockTestData {

    private String      symbol              = "symbol";
    private String      name                = "name";

    private String      spIndex             = "spIndex";
    private String      dowIndex            = "dowIndex";
    private String      russellIndex        = "russelIndex";
    private String      sector              = "sector";
    private String      industry            = "industry";
    private String      tracks              = "tracks";

    private int         strongBuy           = 1;
    private int         buy                 = 2;
    private int         hold                = 3;
    private int         sell                = 4;
    private int         strongSell          = 5;

    private long        marketCap           = 6;
    private long        enterpriseValue     = 7;
    private long        operatingCashflow   = 8;
    private long        ebitda              = 9;
    private long        freeCashflow        = 10;
    private long        totalCash           = 11;
    private long        totalDebt           = 12;
    private long        totalRevenue        = 13;
    private long        avgDailyVol         = 14;

    private double      dividendRate        = 1.1;
    private double      dividendYield       = 2.1;
    private double      beta                = 3.1;
    private double      pe                  = 4.1;
    private double      peForward           = 5.1;
    private double      ps                  = 6.1;
    private double      pb                  = 7.1;
    private double      trailingEps         = 8.1;
    private double      pegRatio            = 9.1;
    private double      enterpriseToRevenue = 10.2;
    private double      enterpriseToEbitda  = 11.2;
    private double      ebitdaMargins       = 12.2;
    private double      profitMargins       = 13.3;
    private double      grossMargins        = 14.4;
    private double      revenueGrowth       = 15.5;
    private double      operatingMargins    = 16.6;
    private double      earningsGrowth      = 17.7;
    private double      currentRatio        = 18.8;
    private double      returnOnAssets      = 19.9;
    private double      debtToEquity        = 20.0;
    private double      returnOnEquity      = 21.1;
    private double      totalCashPerShare   = 22.2;

    private String      overview            = "overview";

    private double     ytd                  = 31.1;
    private double     oneDay               = 32.2;
    private double     oneWeek              = 33.3;
    private double     twoWeeks             = 34.4;
    private double     threeWeeks           = 35.5;
    private double     fourWeeks            = 36.6;
    private double     twoMonths            = 37.7;
    private double     threeMonths          = 38.8;
    private double     fourMonths           = 39.9;
    private double     fiveMonths           = 41.1;
    private double     sixMonths            = 42.2;
    private double     oneYear              = 43.3;
    private double     threeYears           = 44.4;

    private Date       date;
    private Date       lastUpdate;

    private String     dateString           = "2017-01-01";
    private String     lastUpdateString     = "2016-12-12";

    public StockTestData () {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date        = formatter.parse(dateString);
            lastUpdate  = formatter.parse(lastUpdateString);
        } catch ( java.text.ParseException ex) {}
    }

    public Stock getData(String aSymbol) {

        Stock s = new Stock();

        s.setSymbol(aSymbol);
        s.setName(name);

        s.setSpIndex(spIndex);
        s.setDowIndex(dowIndex);
        s.setRussellIndex(russellIndex);
        s.setSector(sector);
        s.setIndustry(industry);
        s.setTracks(tracks);

        s.setStrongBuy(strongBuy);
        s.setBuy(buy);
        s.setHold(hold);
        s.setSell(sell);
        s.setStrongSell(strongSell);

        s.setMarketCap(marketCap);
        s.setEnterpriseValue(enterpriseValue);
        s.setOperatingCashflow(operatingCashflow);
        s.setEbitda(ebitda);
        s.setFreeCashflow(freeCashflow);
        s.setTotalCash(totalCash);
        s.setTotalDebt(totalDebt);
        s.setTotalRevenue(totalRevenue);
        s.setAvgDailyVol(avgDailyVol);

        s.setDividendRate(dividendRate);
        s.setDividendYield(dividendYield);
        s.setBeta(beta);
        s.setPe(pe);
        s.setPeForward(peForward);
        s.setPs(ps);
        s.setPb(pb);
        s.setTrailingEps(trailingEps);
        s.setPegRatio(pegRatio);
        s.setEnterpriseToRevenue(enterpriseToRevenue);
        s.setEnterpriseToEbitda(enterpriseToEbitda);
        s.setEbitdaMargins(ebitdaMargins);
        s.setProfitMargins(profitMargins);
        s.setGrossMargins(grossMargins);
        s.setRevenueGrowth(revenueGrowth);
        s.setOperatingMargins(operatingMargins);
        s.setEarningsGrowth(earningsGrowth);
        s.setCurrentRatio(currentRatio);
        s.setReturnOnAssets(returnOnAssets);
        s.setDebtToEquity(debtToEquity);
        s.setReturnOnEquity(returnOnEquity);
        s.setTotalCashPerShare(totalCashPerShare);

        s.setOverview(overview);

        s.setDate(date);
        s.setLastUpdate(lastUpdate);

        s.setYtd(ytd);
        s.setOneDay(oneDay);
        s.setOneWeek(oneWeek);
        s.setTwoWeeks(twoWeeks);
        s.setFourWeeks(fourWeeks);
        s.setThreeMonths(threeMonths);
        s.setOneYear(oneYear);
        s.setThreeYears(threeYears);

        return (s);
    }

    public void validateData(Stock s) {

        //assertTrue(s.getSymbol().equalsIgnoreCase(symbol));
        assertTrue(s.getName().equalsIgnoreCase(name));

        assertTrue(s.getSpIndex().equalsIgnoreCase(spIndex));
        assertTrue(s.getDowIndex().equalsIgnoreCase(dowIndex));
        assertTrue(s.getRussellIndex().equalsIgnoreCase(russellIndex));
        assertTrue(s.getSector().equalsIgnoreCase(sector));
        assertTrue(s.getIndustry().equalsIgnoreCase(industry));
        assertTrue(s.getTracks().equalsIgnoreCase(tracks));

        assertEquals(s.getStrongBuy(),strongBuy);
        assertEquals(s.getBuy(),buy);
        assertEquals(s.getHold(),hold);
        assertEquals(s.getSell(),sell);
        assertEquals(s.getStrongSell(),strongSell);

        assertEquals(s.getMarketCap(),marketCap);
        assertEquals(s.getEnterpriseValue(),enterpriseValue);
        assertEquals(s.getOperatingCashflow(),operatingCashflow);
        assertEquals(s.getEbitda(),ebitda);
        assertEquals(s.getFreeCashflow(),freeCashflow);
        assertEquals(s.getTotalCash(),totalCash);
        assertEquals(s.getTotalDebt(),totalDebt);
        assertEquals(s.getTotalRevenue(),totalRevenue);
        assertEquals(s.getAvgDailyVol(),avgDailyVol);

        assertEquals(s.getDividendRate(),dividendRate,0.001);
        assertEquals(s.getDividendYield(),dividendYield,0.001);
        assertEquals(s.getBeta(),beta,0.001);
        assertEquals(s.getPe(),pe,0.001);
        assertEquals(s.getPeForward(),peForward,0.001);
        assertEquals(s.getPs(),ps,0.001);
        assertEquals(s.getPb(),pb,0.001);
        assertEquals(s.getTrailingEps(),trailingEps,0.001);
        assertEquals(s.getPegRatio(),pegRatio,0.001);
        assertEquals(s.getEnterpriseToRevenue(),enterpriseToRevenue,0.001);
        assertEquals(s.getEnterpriseToEbitda(),enterpriseToEbitda,0.001);
        assertEquals(s.getEbitdaMargins(),ebitdaMargins,0.001);
        assertEquals(s.getProfitMargins(),profitMargins,0.001);
        assertEquals(s.getGrossMargins(),grossMargins,0.001);
        assertEquals(s.getRevenueGrowth(),revenueGrowth,0.001);
        assertEquals(s.getOperatingMargins(),operatingMargins,0.001);
        assertEquals(s.getEarningsGrowth(),earningsGrowth,0.001);
        assertEquals(s.getCurrentRatio(),currentRatio,0.001);
        assertEquals(s.getReturnOnAssets(),returnOnAssets,0.001);
        assertEquals(s.getDebtToEquity(),debtToEquity,0.001);
        assertEquals(s.getReturnOnEquity(),returnOnEquity,0.001);
        assertEquals(s.getTotalCashPerShare(),totalCashPerShare,0.001);

        assertTrue(s.getOverview().equalsIgnoreCase(overview));

        assertEquals(s.getYtd(),ytd,0.001);
        assertEquals(s.getOneDay(),oneDay,0.001);
        assertEquals(s.getOneWeek(),oneWeek,0.001);
        assertEquals(s.getTwoWeeks(),twoWeeks,0.001);
        assertEquals(s.getFourWeeks(),fourWeeks,0.001);
        assertEquals(s.getThreeMonths(),threeMonths,0.001);
        assertEquals(s.getOneYear(),oneYear,0.001);
        assertEquals(s.getThreeYears(),threeYears,0.001);

        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        String dateRead             = formatter.format(date);
        String lastUpdateRead       = formatter.format(lastUpdate);

        System.out.println(dateRead + " " + dateString);
        System.out.println(lastUpdateRead + " " + lastUpdateString);

        assertTrue(dateRead.equalsIgnoreCase(dateString));
        assertTrue(lastUpdateRead.equalsIgnoreCase(lastUpdateString));

    }
}
