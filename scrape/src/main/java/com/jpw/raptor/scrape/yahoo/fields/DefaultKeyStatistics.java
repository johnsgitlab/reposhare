package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/15/2017.
 */
public class DefaultKeyStatistics {

    private FundInceptionDate fundInceptionDate;
    private String fundFamily;
    private Yield yield;
    private String legalType;

    public FundInceptionDate getFundInceptionDate() {return fundInceptionDate;}
    public void setFundInceptionDate(FundInceptionDate fundInceptionDate) {this.fundInceptionDate = fundInceptionDate;}

    public String getFundFamily() {return fundFamily;}
    public void setFundFamily(String fundFamily) {this.fundFamily = fundFamily;}

    public Yield getYield() {return yield;}
    public void setYield(Yield yield) {this.yield = yield;}

    public String getLegalType() {return legalType;}
    public void setLegalType(String legalType) {this.legalType = legalType;}
}
