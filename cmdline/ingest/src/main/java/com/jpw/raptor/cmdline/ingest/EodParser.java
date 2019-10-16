package com.jpw.raptor.cmdline.ingest;

import com.jpw.raptor.model.Quote;

import java.text.SimpleDateFormat;

/**
 * Created by john on 3/29/18.
 */
public class EodParser {

    public EodParser() {}

    public Quote parseCsvLine (String line) {

        // Parse the string into an array of fields
        // Validate number of fields parsed
        String[] str = line.split(",");
        if ( str.length >= 7 ) {
            // Valid number of columns
        } else {
            // invalid number of fields
            return null;
        }

        // Create the quote
        Quote quote = new Quote();

        // Symbol
        quote.setSymbol(new String(str[0]));

        // validate date
        try {
            // Convert string to date object
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            quote.setDate(format.parse(str[1]));
        }
        catch(Exception e) {
            System.out.println("Quote parse date error " + line);
            return null;
        }

        // Validate open, high, low and close
        try {
            quote.setOpen(Double.parseDouble(str[2]));
            quote.setHigh(Double.parseDouble(str[3]));
            quote.setLow(Double.parseDouble(str[4]));
            quote.setClose(Double.parseDouble(str[5]));
        }
        catch(NumberFormatException e) {
            System.out.println("Quote parse open/high/low/close error " + line);
            return null;
        }

        // Validate volume
        try {
            quote.setVolume(Long.parseLong(str[6]));
        }
        catch(NumberFormatException e) {
            System.out.println("Quote parse volume error " + line);
            return null;
        }

        return quote;
    }
}
