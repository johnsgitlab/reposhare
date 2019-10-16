package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/13/2017.
 */
public class SummaryDetail {

    private String          symbol;
    private TotalAssets     totalAssets;
    private Yield           yield;
    private AverageVolume   averageVolume;

    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public TotalAssets getTotalAssets() {return totalAssets;}
    public void setTotalAssets(TotalAssets totalAssets) {this.totalAssets = totalAssets;}

    public Yield getYield() {return yield;}
    public void setYield(Yield yield) {this.yield = yield;}

    public AverageVolume getAverageVolume() {return averageVolume;}
    public void setAverageVolume(AverageVolume averageVolume) {this.averageVolume = averageVolume;}
}
