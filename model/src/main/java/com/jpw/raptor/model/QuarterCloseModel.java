package com.jpw.raptor.model;

import java.util.Date;
import java.util.List;

/**
 * Created by john on 4/7/18.
 */
public class QuarterCloseModel {
    private int year;
    private Date lastDate;

    private double yearStart;
    private double ytdClose;

    private double qtr1Close;
    private double qtr2Close;
    private double qtr3Close;
    private double qtr4Close;

    public QuarterCloseModel(List<Quote> recs, int year) {

        lastDate            = null;

        yearStart           = 0.0;
        ytdClose            = 0.0;

        qtr1Close           = 0.0;
        qtr2Close           = 0.0;
        qtr3Close           = 0.0;
        qtr4Close           = 0.0;

        DateConstants dc    = new DateConstants();
        Date priorYearEnd   = dc.getYearEnd(year - 1);
        Date nextYearStart  = dc.getYearEnd(year + 1);
        Date qtr1End        = dc.getQuarterEndDate(year, 1);
        Date qtr2End        = dc.getQuarterEndDate(year, 2);
        Date qtr3End        = dc.getQuarterEndDate(year, 3);

        // Start of unix time
        lastDate = new Date(-1);

        // Extract data from quote records
        for (Quote rec : recs) {

            // Records must de in ascending order
            if (rec.getDate().after(lastDate)) {
                Date dt = rec.getDate();

                // Make sure date is within the year we are interested in
                if (dt.after(priorYearEnd) && dt.before(nextYearStart)) {
                    // YTD close is the last one we process
                    ytdClose = rec.getClose();

                    if (dt.compareTo(qtr1End) <= 0) {
                        // date within qtr 1
                        qtr1Close = rec.getClose();

                        // Check to see if this is the first trade of the year
                        if (yearStart == 0.0)
                            yearStart = rec.getOpen();
                    } else if (dt.compareTo(qtr2End) <= 0) {
                        // date within qtr 2
                        qtr2Close = rec.getClose();
                    } else if (dt.compareTo(qtr3End) <= 0) {
                        // date within qtr 3
                        qtr3Close = rec.getClose();
                    } else {
                        // date within qtr 4
                        qtr4Close = rec.getClose();
                    }
                }

                lastDate = dt;
            }
        }

    }

    public int    getYear()         { return year; }
    public Date   getLastDate()     { return lastDate; }
    public double getYearStart()    { return yearStart; }
    public double getYtdClose()     { return ytdClose; }
    public double getQtr1Close()    { return qtr1Close; }
    public double getQtr2Close()    { return qtr2Close; }
    public double getQtr3Close()    { return qtr3Close; }
    public double getQtr4Close()    { return qtr4Close; }

}