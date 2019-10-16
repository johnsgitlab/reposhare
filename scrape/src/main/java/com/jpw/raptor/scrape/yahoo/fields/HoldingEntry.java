package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by john on 3/23/18.
 */
public class HoldingEntry {

    private String symbol;
    private String holdingName;
    private double holdingPercent;

    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public String getHoldingName() {return holdingName;}
    public void setHoldingName(String holdingName) {this.holdingName = holdingName;}

    public double getHoldingPercent() {return holdingPercent;}
    public void setHoldingPercent(double holdingPercent) {this.holdingPercent = holdingPercent;}

}
