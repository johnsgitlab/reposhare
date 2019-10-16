package com.jpw.raptor.scrape.marketwatch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by john on 3/24/18.
 */
public class MarketWatchPageScrapper {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /*

//This will get you the response.
Response res = Jsoup
    .connect("url")
    .data("loginField", "login@login.com", "passField", "pass1234")
    .method(Method.POST)
    .execute();

//This will get you cookies
Map<String, String> cookies = res.cookies();

//And this is the easieste way I've found to remain in session
Documente doc = Jsoup.connect("url").cookies(cookies).get();

// another example
Document docCustomConn = Jsoup.connect(blogUrl)
  .userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0")
  .timeout(5000)
  .cookie("cookiename", "val234")
  .cookie("anothercookie", "ilovejsoup")
  .referrer("http://google.com")
  .header("headersecurity", "xyz123")
  .get();

    */

    public String readStockExchange( String symbol, WebDriver driver) {

        System.out.println("read exchange " + symbol);

        String  ticker  = symbol.toUpperCase();

        String  url     = "https://www.marketwatch.com/investing/stock/" + ticker;

        // launch chrome and direct it to the Base URL
        driver.get(url);

        String exchange = null;

        // html
        // <meta name="exchange" content="U.S.: NYSE Arca">
        try {
            exchange = driver.findElement(By.xpath("//meta[@name='exchange']"))
                .getAttribute("content");
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("*********************");
            System.out.println("Exchange not found for " + symbol);
            System.out.println("*********************");
        }

        return exchange;
    }

    public MarketWatchPageContents readPage( String symbol, WebDriver driver) {

        System.out.println("read page " + symbol);

        String                  ticker      = symbol.toUpperCase();
        MarketWatchPageContents mwc         = new MarketWatchPageContents();

        String url = "https://www.marketwatch.com/investing/fund/" + ticker;

        // launch chrome and direct it to the Base URL
        driver.get(url);

        //Thread.sleep(1000);

        List<WebElement> scores = driver.findElements(By.className("lipper__score"));
        List<WebElement> labels = driver.findElements(By.className("lipper__label"));

        for ( int i=0; i<scores.size(); i++ ) {
            populateRating(mwc, labels.get(i).getText(), scores.get(i).getText());
            //System.out.println(labels.get(i).getText() + " " + scores.get(i).getText());
        }

        if ( scores.size() == 0 ) {
            mwc.setFound(false);
        }

        // html
        // <meta name="exchange" content="U.S.: NYSE Arca">
        try {
            String exchange = driver.findElement(By.xpath("//meta[@name='exchange']"))
                    .getAttribute("content");
            mwc.setExchange(exchange);
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            mwc.setFound(false);
            System.out.println("*********************");
            System.out.println("Exchange not found for " + symbol);
            System.out.println("*********************");
        }

        return mwc;
    }


    protected void populateRating(MarketWatchPageContents mwc, String label, String score) {

        int val;

        if ( score.chars().allMatch( Character::isDigit ) ) {
            val = Integer.parseInt(score);
        } else {
            val = 0;
        }

        if        ( label.equalsIgnoreCase("Total Returns") ) {
            mwc.setTotalReturn(val);
        } else if ( label.equalsIgnoreCase("Consistent Return") ) {
            mwc.setConsistentReturn(val);
        } else if ( label.equalsIgnoreCase("Preservation") ) {
            mwc.setPreservation(val);
        } else if ( label.equalsIgnoreCase("Tax Efficiency") ) {
            mwc.setTaxEfficiency(val);
        } else if ( label.equalsIgnoreCase("Expense") ) {
            mwc.setExpense(val);
        } else  {
        }

        mwc.setFound(true);
    }
}
