package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/17/2017.
 */
public class KeyStatistics {

    private String              fundFamily;
    private FundInceptionDate   fundInceptionDate;

    public String getFundFamily() {return fundFamily;}
    public void setFundFamily(String fundFamily) {this.fundFamily = fundFamily;}

    public FundInceptionDate getFundInceptionDate() {return fundInceptionDate;}
    public void setFundInceptionDate(FundInceptionDate fundInceptionDate) {this.fundInceptionDate = fundInceptionDate;}
}
