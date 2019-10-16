package com.jpw.raptor.scrape.yahoo.fields;

import com.jpw.raptor.scrape.yahoo.YahooPageContents;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
/**
 * Created by john on 3/23/18.
 */

public class YahooPageContentsTest {

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


    public void setValues(YahooPageContents rec) {

        rec.setSymbol(symbol);

        rec.setLastUpdate(lastUpdate);
        rec.setFamily(family);

        rec.setAvgDailyVol(avgDailyVol);
        rec.setInception(inception);
        rec.setMarketCap(marketCap);
        rec.setExpenseRatio(expenseRatio);
        rec.setDividendYield(dividendYield);

        rec.setBasicMaterials(basicMaterials);
        rec.setConsumerCyclical(consumerCyclical);
        rec.setFinancialServices(financialServices);
        rec.setRealestate(realestate);
        rec.setConsumerDefensive(consumerDefensive);
        rec.setHealthcare(healthcare);
        rec.setUtilities(utilities);
        rec.setCommunicationServices(communicationServices);
        rec.setEnergy(energy);
        rec.setIndustrials(industrials);
        rec.setTechnology(technology);

        rec.setAlpha(alpha);
        rec.setBeta(beta);
        rec.setMeanAnnualReturn(meanAnnualReturn);
        rec.setRSquared(rSquared);
        rec.setDeviation(deviation);
        rec.setSharpeRatio(sharpeRatio);
        rec.setTreynorRatio(treynorRatio);

        rec.setPe(pe);
        rec.setPb(pb);
        rec.setPs(ps);
        rec.setPc(pc);
        rec.setEarningsGrowth(earningsGrowth);
        rec.setMedianMarketCap(medianMarketCap);

        rec.setBondMaturity(bondMaturity);
        rec.setBondDuration(bondDuration);
        rec.setBondCreditQuality(bondCreditQuality);
        rec.setBondAaaPercent(bondAaaPercent);
        rec.setBondAaPercent(bondAaPercent);
        rec.setBondAPercent(bondAPercent);
        rec.setBondBbbPercent(bondBbbPercent);
        rec.setBondBbPercent(bondBbPercent);
        rec.setBondBPercent(bondBPercent);
        rec.setBondBelowbPercent(bondBelowbPercent);
        rec.setBondUsPercent(bondUsPercent);
        rec.setBondOtherPercent(bondOtherPercent);

        rec.setBondPosition(bondPosition);
        rec.setCashPosition(cashPosition);
        rec.setConvertiblePosition(convertiblePosition);
        rec.setOtherPosition(otherPosition);
        rec.setPreferredPosition(preferredPosition);
        rec.setStockPosition(stockPosition);

        rec.setTopHoldings(topHoldings);
        rec.setOverview(overview);
    }


    public void validateValues(YahooPageContents rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));

        assertTrue(rec.getFamily().equalsIgnoreCase(family));

        assertEquals(rec.getAvgDailyVol(),avgDailyVol,0.0001);

        assertTrue(rec.getInception().equals(inception));

        assertEquals(rec.getMarketCap(),marketCap,0.0001);
        assertEquals(rec.getExpenseRatio(),expenseRatio,0.0001);
        assertEquals(rec.getDividendYield(),dividendYield,0.0001);

        assertEquals(rec.getBasicMaterials(),basicMaterials,0.0001);
        assertEquals(rec.getConsumerCyclical(),consumerCyclical,0.0001);
        assertEquals(rec.getFinancialServices(),financialServices,0.0001);
        assertEquals(rec.getRealestate(),realestate,0.0001);
        assertEquals(rec.getConsumerDefensive(),consumerDefensive,0.0001);
        assertEquals(rec.getHealthcare(),healthcare,0.0001);
        assertEquals(rec.getUtilities(),utilities,0.0001);
        assertEquals(rec.getCommunicationServices(),communicationServices,0.0001);
        assertEquals(rec.getEnergy(),energy,0.0001);
        assertEquals(rec.getIndustrials(),industrials,0.0001);
        assertEquals(rec.getTechnology(),technology,0.0001);

        assertEquals(rec.getAlpha(),alpha,0.0001);
        assertEquals(rec.getBeta(),beta,0.0001);
        assertEquals(rec.getMeanAnnualReturn(),meanAnnualReturn,0.0001);
        assertEquals(rec.getRSquared(), rSquared,0.0001);
        assertEquals(rec.getDeviation(),deviation,0.0001);
        assertEquals(rec.getSharpeRatio(),sharpeRatio,0.0001);
        assertEquals(rec.getTreynorRatio(),treynorRatio,0.0001);

        assertEquals(rec.getPe(),pe,0.0001);
        assertEquals(rec.getPb(),pb,0.0001);
        assertEquals(rec.getPs(),ps,0.0001);
        assertEquals(rec.getPc(),pc,0.0001);
        assertEquals(rec.getEarningsGrowth(),earningsGrowth,0.0001);
        assertEquals(rec.getMedianMarketCap(),medianMarketCap,0.0001);

        assertEquals(rec.getBondMaturity(),bondMaturity,0.0001);
        assertEquals(rec.getBondDuration(),bondDuration,0.0001);
        assertEquals(rec.getBondCreditQuality(),bondCreditQuality,0.0001);
        assertEquals(rec.getBondAaaPercent(),bondAaaPercent,0.0001);
        assertEquals(rec.getBondAaPercent(),bondAaPercent,0.0001);
        assertEquals(rec.getBondAPercent(),bondAPercent,0.0001);
        assertEquals(rec.getBondBbbPercent(),bondBbbPercent,0.0001);
        assertEquals(rec.getBondBbPercent(),bondBbPercent,0.0001);
        assertEquals(rec.getBondBPercent(),bondBPercent,0.0001);
        assertEquals(rec.getBondBelowbPercent(),bondBelowbPercent,0.0001);
        assertEquals(rec.getBondUsPercent(),bondUsPercent,0.0001);
        assertEquals(rec.getBondOtherPercent(),bondOtherPercent,0.0001);

        assertEquals(rec.getBondPosition(),bondPosition,0.0001);
        assertEquals(rec.getCashPosition(),cashPosition,0.0001);
        assertEquals(rec.getConvertiblePosition(),convertiblePosition,0.0001);
        assertEquals(rec.getOtherPosition(),otherPosition,0.0001);
        assertEquals(rec.getPreferredPosition(),preferredPosition,0.0001);
        assertEquals(rec.getStockPosition(),stockPosition,0.0001);

        assertTrue(rec.getTopHoldings().equalsIgnoreCase(topHoldings));
        assertTrue(rec.getOverview().equalsIgnoreCase(overview));
    }


    @Before
    public void init() {

        // Provide default values
        symbol = "aTestSymbol";

        lastUpdate = new Date(-2);
        family = "family";

        avgDailyVol = 2.0;
        inception = new Date(-3);
        marketCap = 3.0;
        expenseRatio = 4.0;
        dividendYield = 5.0;

        basicMaterials = 150.0;
        consumerCyclical = 160.0;
        financialServices = 170.0;
        realestate = 180.0;
        consumerDefensive = 190.0;
        healthcare = 200.0;
        utilities = 210.0;
        communicationServices = 220.0;
        energy = 230.0;
        industrials = 240.0;
        technology = 250.0;

        alpha = 260.0;
        beta = 270.0;
        meanAnnualReturn = 280.0;
        rSquared = 290.0;
        deviation = 300.0;
        sharpeRatio = 310.0;
        treynorRatio = 320.0;

        pe = 330.0;
        pb = 340.0;
        ps = 350.0;
        pc = 360.0;
        earningsGrowth = 370.0;
        medianMarketCap = 380.0;

        bondMaturity = 390.0;
        bondDuration = 400.0;
        bondCreditQuality = 410.0;
        bondAaaPercent = 420.0;
        bondAaPercent = 430.0;
        bondAPercent = 440.0;
        bondBbbPercent = 450.0;
        bondBbPercent = 460.0;
        bondBPercent = 470.0;
        bondBelowbPercent = 480.0;
        bondUsPercent = 490.0;
        bondOtherPercent = 500.0;

        bondPosition = 510.0;
        cashPosition = 520.0;
        convertiblePosition = 530.0;
        otherPosition = 540.0;
        preferredPosition = 550.0;
        stockPosition = 560.0;

        topHoldings = "top_holdings";
        overview = "overview";
    }

    @Test
    public void test01() {
        System.out.println("YahooPageContents test01");

        YahooPageContents ypc = new YahooPageContents();

        setValues(ypc);
        validateValues(ypc);

        System.out.println("YahooPageContents test01");
    }

}
