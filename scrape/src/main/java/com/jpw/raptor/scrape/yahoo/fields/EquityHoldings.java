package com.jpw.raptor.scrape.yahoo.fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 10/11/2017.
 */
public class EquityHoldings {


    private PriceToCashflow         priceToCashflow;
    private PriceToSales            priceToSales;
    private ThreeYearEarningsGrowth threeYearEarningsGrowth;
    private MedianMarketCap         medianMarketCap;
    private PriceToEarnings         priceToEarnings;
    private PriceToBook             priceToBook;


    public PriceToCashflow getPriceToCashflow() {
        return priceToCashflow;
    }
    public void setPriceToCashflow(PriceToCashflow priceToCashflow) {
        this.priceToCashflow = priceToCashflow;
    }

    public PriceToSales getPriceToSales() {
        return priceToSales;
    }
    public void setPriceToSales(PriceToSales priceToSales) {
        this.priceToSales = priceToSales;
    }

    public ThreeYearEarningsGrowth getThreeYearEarningsGrowth() {
        return threeYearEarningsGrowth;
    }
    public void setThreeYearEarningsGrowth(ThreeYearEarningsGrowth threeYearEarningsGrowth) {
        this.threeYearEarningsGrowth = threeYearEarningsGrowth;
    }

    public MedianMarketCap getMedianMarketCap() {
        return medianMarketCap;
    }
    public void setMedianMarketCap(MedianMarketCap medianMarketCap) {
        this.medianMarketCap = medianMarketCap;
    }

    public PriceToEarnings getPriceToEarnings() {
        return priceToEarnings;
    }
    public void setPriceToEarnings(PriceToEarnings priceToEarnings) {
        this.priceToEarnings = priceToEarnings;
    }

    public PriceToBook getPriceToBook() {
        return priceToBook;
    }
    public void setPriceToBook(PriceToBook priceToBook) {
        this.priceToBook = priceToBook;
    }


}
