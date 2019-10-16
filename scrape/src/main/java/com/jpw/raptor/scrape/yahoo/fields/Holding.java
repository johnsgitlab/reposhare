package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/12/2017.
 */
public class Holding {

    private String symbol;
    private String holdingName;
    private HoldingPercent holdingPercent;

    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public String getHoldingName() {return holdingName;}
    public void setHoldingName(String holdingName) {this.holdingName = holdingName;}

    public HoldingPercent getHoldingPercent() {return holdingPercent;}
    public void setHoldingPercent(HoldingPercent holdingPercent) {this.holdingPercent = holdingPercent;}
}
