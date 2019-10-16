package com.jpw.raptor.scrape.yahoo.fields;


/**
 * Created by John on 10/12/2017.
 */
public class BondHoldings {

    private Maturity maturity;
    private Duration duration;
    private CreditQuality creditQuality;


    public Maturity getMaturity() {return maturity;}
    public void setMaturity(Maturity maturity) {this.maturity = maturity;}

    public Duration getDuration() {return duration;}
    public void setDuration(Duration duration) {this.duration = duration;}

    public CreditQuality getCreditQuality() {return creditQuality;}
    public void setCreditQuality(CreditQuality creditQuality) {this.creditQuality = creditQuality;}


}
