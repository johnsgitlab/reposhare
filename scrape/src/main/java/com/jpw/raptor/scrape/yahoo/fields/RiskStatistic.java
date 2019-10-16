package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/12/2017.
 */
public class RiskStatistic {

    private String year;
    private Alpha alpha;
    private Beta beta;
    private MeanAnnualReturn meanAnnualReturn;
    private RSquared rSquared;
    private StdDev stdDev;
    private SharpeRatio sharpeRatio;
    private TreynorRatio treynorRatio;

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public Alpha getAlpha() {return alpha;}
    public void setAlpha(Alpha alpha) {this.alpha = alpha;}

    public Beta getBeta() {return beta;}
    public void setBeta(Beta beta) {this.beta = beta;}

    public MeanAnnualReturn getMeanAnnualReturn() {return meanAnnualReturn;}
    public void setMeanAnnualReturn(MeanAnnualReturn meanAnnualReturn) {this.meanAnnualReturn = meanAnnualReturn;}

    public RSquared getRSquared() {return rSquared;}
    public void setRSquared(RSquared rSquared) {this.rSquared = rSquared;}

    public StdDev getStdDev() {return stdDev;}
    public void setStdDev(StdDev stdDev) {this.stdDev = stdDev;}

    public SharpeRatio getSharpeRatio() {
        return sharpeRatio;
    }
    public void setSharpeRatio(SharpeRatio sharpeRatio) {this.sharpeRatio = sharpeRatio;}

    public TreynorRatio getTreynorRatio() {return treynorRatio;}
    public void setTreynorRatio(TreynorRatio treynorRatio) {this.treynorRatio = treynorRatio;}
}
