package com.jpw.raptor.scrape.marketwatch;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * Created by john on 3/24/18.
 */
public class MarketWatchPageScrapperTest {

    public void printResults(MarketWatchPageContents results) {
        System.out.println( " " );
        if ( results.isFound() ) {
            System.out.println( "Found" );
        } else {
            System.out.println( "Not found" );
        }
        System.out.println( "TotalReturn      " + results.getTotalReturn());
        System.out.println( "ConsistentReturn " + results.getConsistentReturn());
        System.out.println( "Expense          " + results.getExpense());
        System.out.println( "Preservation     " + results.getPreservation());
        System.out.println( "TaxEfficiency    " + results.getTaxEfficiency());
        System.out.println( "Exchange         " + results.getExchange());
    }

    @Test
    //
    // Risk Stats
    public void test01() {

        System.out.println("test 01 started");


        /* */
        String pathToChromeDriver = "/home/scripts/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String symbol;
        symbol = "fcntx";
        symbol = "rem";
        symbol = "xlc";
        symbol = "soxx";
        symbol = "spyg";
        //symbol = "abcdefg";

        MarketWatchPageScrapper scrapper = new MarketWatchPageScrapper();
        MarketWatchPageContents results  = scrapper.readPage(symbol, driver);

        printResults(results);

        //close chrome
        driver.close();
        /* */
    }


    @Test
    //
    // Risk Stats
    public void test02() {

        System.out.println("test 02 started");


        /* */
        String pathToChromeDriver = "/home/scripts/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String symbol;

        symbol = "aapl";
        //symbol = "abcdefg";

        MarketWatchPageScrapper scrapper = new MarketWatchPageScrapper();
        String                  exchange = scrapper.readStockExchange(symbol, driver);

        System.out.println( "Exchange         " + exchange);

        //close chrome
        driver.close();
        /* */
    }

}
