package com.jpw.raptor.scrape.marketwatch;

import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class MarketWatchSeleniumTest {


    @Test
    //
    // Risk Stats
    public void test01() throws InterruptedException {

        System.out.println("test 01 started");

/*
        String pathToChromeDriver = "/home/scripts/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String symbol = "rem";

        MarketWatchPageScrapper scrapper = new MarketWatchPageScrapper();
        MarketWatchPageContents results  = scrapper.readPage(driver, symbol);

        System.out.println( " " );
        System.out.println( "TotalReturn      " + results.getTotalReturn());
        System.out.println( "ConsistentReturn " + results.getConsistentReturn());
        System.out.println( "Expense          " + results.getExpense());
        System.out.println( "Preservation     " + results.getPreservation());
        System.out.println( "TaxEfficiency    " + results.getTaxEfficiency());

        //close chrome
        driver.close();
*/
    }

        @Test
    //
    // Risk Stats
    public void test02() throws InterruptedException {

        System.out.println("test 02 started");

/*
        String pathToChromeDriver = "/home/scripts/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String url = "https://www.marketwatch.com/investing/fund/rem";

        // launch chrome and direct it to the Base URL
        driver.get(url);

        //Thread.sleep(1000);

        List<WebElement> scores = driver.findElements(By.className("lipper__score"));
        List<WebElement> labels = driver.findElements(By.className("lipper__label"));

        for ( int i=0; i<scores.size(); i++ ) {
            System.out.println(labels.get(i).getText() + " " + scores.get(i).getText());
        }

        //close chrome
        driver.close();
*/

        /*
            Total Returns 4
            Consistent Return 2
            Preservation 5
            Tax Efficiency 1
            Expense 5
         */
    }


}
