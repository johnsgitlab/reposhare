package com.jpw.raptor.jdbc.quote;

import com.jpw.raptor.model.Quote;


import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface QuoteInterface {

    public int sqlScript(String script);

    public void add(Quote rec);

    public void update(Quote rec);
    
    public void delete(String symbol, Date date);
    
    public void deleteAll(String symbol);
    
    public Quote get(String symbol, Date date);

    public List<Quote> getAllAsc(String symbol);

    public List<Quote> getAllDesc(String symbol);

    public List<Quote> getForPerformanceDesc(String symbol, String date);

    public List<Quote> getForSplitDesc(String symbol, Date date);

    public List<Quote> getYearsWorthDesc(String symbol);

    public List<Quote> getYearsWorthDescFromDate(String symbol, String date);

    public List<Quote> getForAnalyticsDesc(String symbol);

    public List<Quote> getByYearAsc(String symbol, int year);

}
