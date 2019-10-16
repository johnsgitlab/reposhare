package com.jpw.raptor.model;

/**
 * Created by john on 4/5/18.
 */
public class HoldingModel {

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
