package com.jpw.raptor.scrape.yahoo;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.jpw.raptor.scrape.yahoo.fields.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by john on 3/23/18.
 */
public class YahooPageScrapper {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public YahooPageContents readPage(String symbol) {

        boolean          riskStats;
        boolean          equityHoldingStats;
        boolean          bondHoldingStats;
        boolean          sectorWeighting;
        boolean          positionSummary;
        boolean          equityProfile;
        boolean          found;
    
        String            ticker = symbol.toUpperCase();
		YahooPageContents ypc    = new YahooPageContents();
		ypc.setSymbol(ticker);
		  
        try {
            Document          doc  = Jsoup.connect("https://finance.yahoo.com/quote/" + ticker).get();
            String            page = doc.toString();

            if ( page.contains("\"statusText\":\"Not Found\"")) {
                found = false;
            } else {
             	 getHoldings(ticker, page, ypc);
            	
                bondHoldingStats   = getBondHoldingStats(ticker, page, ypc);
                equityHoldingStats = getEquityHoldingStats(ticker, page, ypc);
                equityProfile      = getEquityProfile(ticker, page, ypc);
                positionSummary    = getPositionSummary(ticker, page, ypc);
                riskStats          = getRiskStatistics(ticker, page, ypc);
                sectorWeighting    = getSectorWeightings(ticker, page, ypc);

                if ( bondHoldingStats == true ||
                        equityHoldingStats == true ||
                        equityProfile == true ||
                        positionSummary == true ||
                        riskStats == true ||
                        sectorWeighting == true
                        )
                    ypc.setFound(true);
                else
                    ypc.setFound(false);
            }

            return ypc;
        } catch (IOException e) {
            //System.out.println("ERROR IOException");
            System.out.println(e.toString());
            return null;
        }

    }

    /**********************************************************************************************************/

    protected boolean getRiskStatistics (String ticker, String page, YahooPageContents ypc) {

        int                     dataStart  = 0;
        int                     dataEnd    = 0;
        RiskOverviewStatistics  risk       = null;
        List<RiskStatistic>     list       = null;
        boolean                 ret        = false;

        dataStart = page.indexOf("\"riskOverviewStatistics\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}}]},", dataStart);
            if ( dataEnd >= 0 ) {
                String str = page.substring(dataStart+25, dataEnd+4);

                try {
                    Gson gson = new GsonBuilder().setLenient().create();
                    risk = gson.fromJson(str, RiskOverviewStatistics.class);
                    list = risk.getRiskStatistics();
                    ret = true;

                    for (RiskStatistic stat : list) {
                        if (stat.getYear().equalsIgnoreCase("3y")) {
                            ypc.setAlpha(stat.getAlpha().getRaw());
                            ypc.setBeta(stat.getBeta().getRaw());
                            ypc.setMeanAnnualReturn(stat.getMeanAnnualReturn().getRaw());
                            ypc.setRSquared(stat.getRSquared().getRaw());
                            ypc.setDeviation(stat.getStdDev().getRaw());
                            ypc.setSharpeRatio(stat.getSharpeRatio().getRaw());
                            ypc.setTreynorRatio(stat.getTreynorRatio().getRaw());
                        }
                    }
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Risk Statistics : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }
        return ret;
    }


    protected boolean getEquityHoldingStats(String ticker, String page, YahooPageContents ypc) {

        int                 dataStart   = 0;
        int                 dataEnd     = 0;
        boolean             ret         = false;
        EquityHoldings      eh          = null;

        ret = false;
        dataStart = page.indexOf("\"equityHoldings\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}},", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    String equityStr = page.substring(dataStart+17, dataEnd+2);

                    Gson gson = new GsonBuilder().setLenient().create();
                    eh = gson.fromJson(equityStr, EquityHoldings.class);
                    ret = true;

                    if ( eh.getMedianMarketCap().getFmt() != null )
                        ypc.setMedianMarketCap(eh.getMedianMarketCap().getRaw());

                    if ( eh.getPriceToBook().getFmt() != null )
                        ypc.setPb(eh.getPriceToBook().getRaw());

                    if ( eh.getPriceToCashflow().getFmt() != null )
                        ypc.setPc(eh.getPriceToCashflow().getRaw());

                    if ( eh.getPriceToEarnings().getFmt() != null )
                        ypc.setPe(eh.getPriceToEarnings().getRaw());

                    if ( eh.getPriceToSales().getFmt() != null )
                        ypc.setPs(eh.getPriceToSales().getRaw());

                    if ( eh.getThreeYearEarningsGrowth().getFmt() != null )
                        ypc.setEarningsGrowth(eh.getThreeYearEarningsGrowth().getRaw());
                        
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Holding Statistics : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        return ret;
    }


    protected boolean getBondHoldingStats(String ticker, String page, YahooPageContents ypc) {

        int                 dataStart   = 0;
        int                 dataEnd     = 0;
        boolean             ret         = false;
        BondHoldings        bh          = null;
       
        dataStart = page.indexOf("\"bondHoldings\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}},", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    String bondStr = page.substring(dataStart + 15, dataEnd + 2);

                    Gson gson = new GsonBuilder().setLenient().create();
                    bh = gson.fromJson(bondStr, BondHoldings.class);
                    ret = true;

                    if (bh.getMaturity().getFmt() != null)
                        ypc.setBondMaturity(bh.getMaturity().getRaw());
                    if (bh.getDuration().getFmt() != null) ;
                        ypc.setBondDuration(bh.getDuration().getRaw());
                    if (bh.getCreditQuality().getFmt() != null) ;
                        ypc.setBondCreditQuality(bh.getCreditQuality().getRaw());
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Bond Holding Stat / holdings : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        BondRating[]    array      = null;

        dataStart = page.indexOf("\"bondRatings\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("],", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    String str = page.substring(dataStart + 14, dataEnd + 1);

                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();
                    array = gson.fromJson(str, BondRating[].class);
                    ret = true;
                    for (BondRating b : array) {
                        if (b.getBb() != null) ypc.setBondBbPercent(b.getBb().getRaw() * 100.0);
                        if (b.getAa() != null) ypc.setBondAaPercent(b.getAa().getRaw() * 100.0);
                        if (b.getAaa() != null) ypc.setBondAaaPercent(b.getAaa().getRaw() * 100.0);
                        if (b.getA() != null) ypc.setBondAPercent(b.getA().getRaw() * 100.0);
                        if (b.getOther() != null) ypc.setBondOtherPercent(b.getOther().getRaw() * 100.0);
                        if (b.getB() != null) ypc.setBondBPercent(b.getB().getRaw() * 100.0);
                        if (b.getBbb() != null) ypc.setBondBbbPercent(b.getBbb().getRaw() * 100.0);
                        if (b.getBelowB() != null) ypc.setBondBelowbPercent(b.getBelowB().getRaw() * 100.0);
                        if (b.getUsGovernment() != null) ypc.setBondUsPercent(b.getUsGovernment().getRaw() * 100.0);
                    }
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Bond Holding Stat / Ratings : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        return ret;
    }

    protected void getHoldings(String ticker, String page, YahooPageContents ypc) {

        int             dataStart  = 0;
        int             dataEnd    = 0;
        boolean         ret        = false;
        Holding[]  		hArray     = null;
        HoldingEntry[]  result     = null;

        dataStart = page.indexOf("\"holdings\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("],", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    String holdingStr = page.substring(dataStart + 11, dataEnd + 1);

                    Gson gson = new GsonBuilder().setLenient().create();
                    hArray = gson.fromJson(holdingStr, Holding[].class);
                    result = new HoldingEntry[hArray.length];
                    for (int i = 0; i < hArray.length; i++) {
                        HoldingEntry h = new HoldingEntry();
                        h.setHoldingName(hArray[i].getHoldingName());
                        //h.setHoldingPercent(hArray[i].getHoldingPercent().getRaw() * 100.0);
                        h.setHoldingPercent(hArray[i].getHoldingPercent().getRaw());
                        h.setSymbol(hArray[i].getSymbol());
                        result[i] = h;
                    }
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    result = null;
                    System.out.println("ERROR JSON PARSE Holdings : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }

        }

        if ( result == null )
            result = new HoldingEntry[0];

        Gson gson = new Gson();
        ypc.setTopHoldings(gson.toJson(result));
        
    }


    protected boolean getSectorWeightings(String ticker, String page, YahooPageContents ypc) {

        int                dataStart  = 0;
        int                dataEnd    = 0;
        boolean            ret        = false;
        SectorWeighting[]  array      = null;

        ret = false;
        dataStart = page.indexOf("\"sectorWeightings\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("],", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    String sectorStr = page.substring(dataStart + 19, dataEnd + 1);

                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();
                    array = gson.fromJson(sectorStr, SectorWeighting[].class);
                    ret = true;

                    for (SectorWeighting b : array) {
                        if (b.getRealestate() != null) ypc.setRealestate(b.getRealestate().getRaw() * 100.0);
                        if (b.getConsumerCyclical() != null) ypc.setConsumerCyclical(b.getConsumerCyclical().getRaw() * 100.0);
                        if (b.getBasicMaterials() != null) ypc.setBasicMaterials(b.getBasicMaterials().getRaw() * 100.0);
                        if (b.getConsumerDefensive() != null)
                            ypc.setConsumerDefensive(b.getConsumerDefensive().getRaw() * 100.0);
                        if (b.getTechnology() != null) ypc.setTechnology(b.getTechnology().getRaw() * 100.0);
                        if (b.getCommunicationServices() != null)
                            ypc.setCommunicationServices(b.getCommunicationServices().getRaw() * 100.0);
                        if (b.getFinancialServices() != null)
                            ypc.setFinancialServices(b.getFinancialServices().getRaw() * 100.0);
                        if (b.getUtilities() != null) ypc.setUtilities(b.getUtilities().getRaw() * 100.0);
                        if (b.getIndustrials() != null) ypc.setIndustrials(b.getIndustrials().getRaw() * 100.0);
                        if (b.getEnergy() != null) ypc.setEnergy(b.getEnergy().getRaw() * 100.0);
                        if (b.getHealthcare() != null) ypc.setHealthcare(b.getHealthcare().getRaw() * 100.0);
                    }
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Sector weightings : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        return ret;
    }

    protected boolean getPositionSummary(String ticker, String page, YahooPageContents ypc) {

        int                 dataStart   = 0;
        int                 dataEnd     = 0;
        boolean             ret         = false;
        String              str         = null;
        
        BondPosition        bond        = null;
        CashPosition        cash        = null;
        ConvertiblePosition conv        = null;
        OtherPosition       oth         = null;
        PreferredPosition   pref        = null;
        StockPosition       stock       = null;
        
        Gson                gson        = new GsonBuilder().setLenient().create();

        dataStart = page.indexOf("\"bondPosition\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(dataStart + 15, dataEnd + 1);
                    bond = gson.fromJson(str, BondPosition.class);
                    if (bond.getFmt() != null)
                        ypc.setBondPosition(bond.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Bond Position : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        dataStart = page.indexOf("\"cashPosition\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(dataStart + 15, dataEnd + 1);
                    cash = gson.fromJson(str, CashPosition.class);
                    if (cash.getFmt() != null)
                        ypc.setCashPosition(cash.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Cash Position : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        dataStart = page.indexOf("\"convertiblePosition\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(dataStart + 22, dataEnd + 1);
                    conv = gson.fromJson(str, ConvertiblePosition.class);
                    if (conv.getFmt() != null)
                        ypc.setConvertiblePosition(conv.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Convertible Position : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        dataStart = page.indexOf("\"otherPosition\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(dataStart + 16, dataEnd + 1);
                    oth = gson.fromJson(str, OtherPosition.class);
                    if (oth.getFmt() != null)
                        ypc.setOtherPosition(oth.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Other Position : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        dataStart = page.indexOf("\"preferredPosition\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(dataStart + 20, dataEnd + 1);
                    pref = gson.fromJson(str, PreferredPosition.class);
                    if (pref.getFmt() != null)
                        ypc.setPreferredPosition(pref.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Preferred Position : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        dataStart = page.indexOf("\"stockPosition\":");
        if ( dataStart >= 0 ) {
            dataEnd   = page.indexOf("}", dataStart);
            if ( dataEnd >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(dataStart + 16, dataEnd + 1);
                    stock = gson.fromJson(str, StockPosition.class);
                    if (stock.getFmt() != null)
                        ypc.setStockPosition(stock.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Stock Position : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        return ret;
    }

    protected boolean getEquityProfile(String ticker, String page, YahooPageContents ypg) {

        int                     start       = 0;
        int                     startSumm   = 0;
        int                     startFees   = 0;
        int                     end         = 0;
        boolean                 ret         = false;
        
        String                  str         = null;
        Yield                   yld         = null;

        AnnualReportExpenseRatio are        = null;
        NetExpRatio              ner        = null;
        
        Gson                     gson       = new GsonBuilder().setLenient().create();

        startSumm = page.indexOf("\"summaryDetail\":");
        start = page.indexOf("\"yield\":", startSumm);
        if ( start >= 0 ) {
            end = page.indexOf("}", start);
            if (end >= 0) {
                try {
                    ret = true;
                    str = page.substring(start + 8, end + 1);
                    yld = gson.fromJson(str, Yield.class);

                    if (yld.getFmt() != null)
                        ypg.setDividendYield(yld.getRaw() * 100.0);
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Yield : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        double exp1 = 0.0;
        double exp2 = 0.0;
        startFees = page.indexOf("\"feesExpensesInvestment\":");
        start = page.indexOf("\"annualReportExpenseRatio\":", startFees);
        if ( start >= 0 ) {
            end = page.indexOf("}", start);
            if (end >= 0) {
                try {
                    ret = true;
                    str = page.substring(start + 27, end + 1);
                    are = gson.fromJson(str, AnnualReportExpenseRatio.class);

                    if (are.getFmt() != null)
                        exp1 = are.getRaw();
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Annual Report Expense Ration : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        start = page.indexOf("\"netExpRatio\":", startFees);
        if ( start >= 0 ) {
            end = page.indexOf("}", start);
            if (end >= 0) {
                try {
                    ret = true;
                    str = page.substring(start + 14, end + 1);
                    ner = gson.fromJson(str, NetExpRatio.class);

                    if (ner.getFmt() != null)
                        exp2 = ner.getRaw();
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Net Expense Ratio : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        if ( exp1 > exp2 )
            ypg.setExpenseRatio(exp1 * 100.0);
        else
            ypg.setExpenseRatio(exp2 * 100.0);

        PerfomanceOverview  obj        = null;
        start = page.indexOf("\"performanceOverview\":");
        if ( start >= 0 ) {
            ret = true;
            end   = page.indexOf("}}", start);
            if ( end >= 0 ) {
                try {
                    str = page.substring(start + 22, end + 2);
                    obj = gson.fromJson(str, PerfomanceOverview.class);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    if ( obj.getAsOfDate().getFmt() != null )
                        ypg.setLastUpdate(formatter.parse(obj.getAsOfDate().getFmt()));
                    else
                        ypg.setLastUpdate(formatter.parse("1969-12-31"));
                } catch (com.google.gson.JsonSyntaxException | ParseException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Performance Overview : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        AssetProfile ap         = null;
        start = page.indexOf("\"assetProfile\":");
        if ( start >= 0 ) {
            end   = page.indexOf("}", start);
            if ( end >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(start + 15, end + 1);
                    ap = gson.fromJson(str, AssetProfile.class);
                    if ( ap.getLongBusinessSummary() != null ) {
                        String text = ap.getLongBusinessSummary()
                                .replace('\n', ' ')
                                .replace("\"", "")
                                .replace("'","");
                        ypg.setOverview(text);
                    } else {
                        ypg.setOverview("");
                    }
                } catch (com.google.gson.JsonSyntaxException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Asset Profile : " + ticker);
                    System.out.println(ex.getMessage());
                }
            }
        }

        SummaryDetail sd        = null;
        start = page.indexOf("\"summaryDetail\":");
        if ( start >= 0 ) {
            ret = true;
            end   = page.indexOf("}}", start);
            if ( end >= 0 ) {
                try {
                    str = page.substring(start + 16, end + 2);
                    sd = gson.fromJson(str, SummaryDetail.class);

                    if (sd.getTotalAssets().getFmt() != null)
                        ypg.setMarketCap(sd.getTotalAssets().getRaw());
                    if (sd.getAverageVolume().getFmt() != null)
                        ypg.setAvgDailyVol(sd.getAverageVolume().getRaw());
                } catch (com.google.gson.JsonSyntaxException ex1 ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Summary Detail : " + ticker);
                    System.out.println(ex1.getMessage());
                } catch (NullPointerException ex2) {
                    ret = false;
                    System.out.println("ERROR Summary Detail not found : " + ticker);
                    System.out.println(ex2.getMessage());
                }
            }
        }

        KeyStatistics  keyStats        = null;
        start = page.indexOf("\"defaultKeyStatistics\":");
        if ( start >= 0 ) {
            end   = page.indexOf("}}", start);
            if ( end >= 0 ) {
                try {
                    ret = true;
                    str = page.substring(start + 23, end + 2);
                    keyStats = gson.fromJson(str, KeyStatistics.class);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    if ( keyStats.getFundInceptionDate().getFmt() != null )
                        ypg.setInception(formatter.parse(keyStats.getFundInceptionDate().getFmt()));
                    else
                        ypg.setInception(formatter.parse("1969-12-31"));
                    ypg.setFamily(keyStats.getFundFamily());
                } catch (com.google.gson.JsonSyntaxException | ParseException ex ) {
                    ret = false;
                    System.out.println("ERROR JSON PARSE Equity Profile for Default Key Statistics : " + ticker);
                    System.out.println(ex.getMessage());
                }

            }
        }
        
        return ret;
    }

}
