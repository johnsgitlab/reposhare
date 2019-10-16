package com.jpw.raptor.scrape.yahoo.fields;

import java.util.List;

/**
 * Created by John on 10/12/2017.
 */
public class RiskOverviewStatistics {

    private RiskRating riskRating;
    private List<RiskStatistic> riskStatistics = null;

    public RiskRating getRiskRating() {return riskRating;}
    public void setRiskRating(RiskRating riskRating) {this.riskRating = riskRating;}

    public List<RiskStatistic> getRiskStatistics() {return riskStatistics;}
    public void setRiskStatistics(List<RiskStatistic> riskStatistics) {this.riskStatistics = riskStatistics;}
}
