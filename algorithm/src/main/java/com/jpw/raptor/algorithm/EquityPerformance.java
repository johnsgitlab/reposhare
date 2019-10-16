package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Ported to new design 5/16/2019
 *
 * Compute the performance of a equity from a start time which is the last entry in the list
 *
 * Compute the relative  performance of two equities from a start time which is the last entry in the list
 * The resultant list will only have entries the given date has a value for both equities
 *
 */
public class EquityPerformance {

    public static final int     ONE_DAY         = 1;
    public static final int     ONE_WEEK        = 5;
    public static final int     TWO_WEEKS       = 10;
    public static final int     THREE_WEEKS     = 15;
    public static final int     FOUR_WEEKS      = 21;
    public static final int     TWO_MONTHS      = 42;
    public static final int     THREE_MONTHS    = 63;
    public static final int     FOUR_MONTHS     = 84;
    public static final int     FIVE_MONTHS     = 105;
    public static final int     SIX_MONTHS      = 126;
    public static final int     ONE_YEAR        = 253;
    public static final int     THREE_YEARS     = 759;


    // Quotes are in descending order by dates
    public Performance computePerformance(String symbol, List<Quote> quotes) {

        // results
        Performance rec = new Performance();

        // Working variables to compute performance
        double              r;
        DecimalFormat       df          = new DecimalFormat("#.##");

        // get most recent quote
        Quote               today       = quotes.get(0);

        // compute the prior year
        SimpleDateFormat    formatter    = new SimpleDateFormat("yyyy");
        String              formatedDate = formatter.format(today.getDate());
        int                 priorYearInt = Integer.parseInt(formatedDate) - 1;

        // number of quotes
        int quoteCount = quotes.size();

        // Used to hold last quote from prior year
        Quote priorYearQuote = null;

        // note the first close is today so start processing at 1
        for ( int i=1; i<quoteCount; i++ ) {

            // we are processing the dates in descending order
            // so the first quote in the prior year is the
            // last quote of that year
            if ( priorYearQuote == null ) {
                // get the year from the quote
                int parsedYearInt = Integer.parseInt(formatter.format(quotes.get(i).getDate()));
                if ( parsedYearInt == priorYearInt ) {
                    // last quote of prior year
                    priorYearQuote = quotes.get(i);
                }
            }

            // Process 1 day return
            if ( i == ONE_DAY ) {
                r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
                rec.setOneDay(Double.valueOf(df.format(r)));
                rec.setDate(today.getDate());
            }

            weeks(rec, quotes, today, i);
            months(rec, quotes, today, i);
            years(rec, quotes, today, i);

        }

        // compute ytd return
        if (priorYearQuote != null) {
            r = ( (today.getClose() - priorYearQuote.getClose()) / priorYearQuote.getClose() ) * 100.0;
            rec.setYtd(Double.valueOf(df.format(r)));
        } else {
            rec.setYtd(9999.0);
        }

        // Reset three year return
        rec.setThreeYears(9999.0);

        // Update date_tx
        rec.setDate(today.getDate());

        return rec;
    }



    private void weeks(Performance rec, List<Quote> quotes, Quote today, int i) {

        // Working variables to compute performance
        double              r;
        DecimalFormat       df          = new DecimalFormat("#.##");

        // Process One week return
        if ( i == ONE_WEEK ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setOneWeek(Double.valueOf(df.format(r)));
        }

        // Process two week return
        if ( i == TWO_WEEKS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setTwoWeeks(Double.valueOf(df.format(r)));
        }

        // Process three week return
        if ( i == THREE_WEEKS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setThreeWeeks(Double.valueOf(df.format(r)));
        }

        // Process four week return
        if ( i == FOUR_WEEKS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setFourWeeks(Double.valueOf(df.format(r)));
        }
    }


    private void months(Performance rec, List<Quote> quotes, Quote today, int i) {

        // Working variables to compute performance
        double              r;
        DecimalFormat       df          = new DecimalFormat("#.##");

        // Process two month return
        if ( i == TWO_MONTHS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setTwoMonths(Double.valueOf(df.format(r)));
        }

        // Process three month return
        if ( i == THREE_MONTHS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setThreeMonths(Double.valueOf(df.format(r)));
        }

        // Process four month return
        if ( i == FOUR_MONTHS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setFourMonths(Double.valueOf(df.format(r)));
        }

        // Process five month return
        if ( i == FIVE_MONTHS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setFiveMonths(Double.valueOf(df.format(r)));
        }

        // Process six month return
        if ( i == SIX_MONTHS ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setSixMonths(Double.valueOf(df.format(r)));
        }
    }


    private void years(Performance rec, List<Quote> quotes, Quote today, int i) {

        // Working variables to compute performance
        double              r;
        DecimalFormat       df = new DecimalFormat("#.##");

        // Process One year return
        if ( i == ONE_YEAR ) {
            r = ((today.getClose() - quotes.get(i).getClose()) / quotes.get(i).getClose()) * 100.0;
            rec.setOneYear(Double.valueOf(df.format(r)));
        }
    }

}
