package com.jpw.raptor.jdbc.quote;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.jdbc.quote.QuoteRowMapper;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by John on 10/1/2017.
 */
@Repository
public class QuoteDAO implements QuoteInterface {

    private static final Logger logger = LoggerFactory.getLogger(QuoteDAO.class);

    private DataSource      dataSource;
    private JdbcTemplate    jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int sqlScript(String script) {
        return jdbcTemplate.update(script);
    }

    @Override
    public void add(Quote rec) {
    	
    	// Normalize date
    	SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(rec.getDate());
        Date                date                = new Date(-1);
        try {
            date = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote add date format exception");
        }

        // Normalize double values
		double open  = Precision.round(rec.getOpen(),2);
        double high  = Precision.round(rec.getHigh(),2);
        double low   = Precision.round(rec.getLow(),2);
        double close = Precision.round(rec.getClose(),2);
        
    	String sql = "INSERT INTO quote_tbl (symbol, date_tx, open, high, low, close, volume) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, rec.getSymbol(), date, open, high, low, close, rec.getVolume());
        } catch ( org.springframework.dao.DuplicateKeyException ex ) {
            System.out.println ("Duplicate quote " + rec.getSymbol() + " " + date_string);
        }

    }

    @Override
    public void update(Quote rec) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(rec.getDate());
        Date                date                = new Date(-1);
        try {
            date = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote update date format exception");
        }
        
        // Normalize double values
		double open  = Precision.round(rec.getOpen(),2);
        double high  = Precision.round(rec.getHigh(),2);
        double low   = Precision.round(rec.getLow(),2);
        double close = Precision.round(rec.getClose(),2);
        
    	String sql = "UPDATE quote_tbl SET open=?, high=?, low=?, close=?, volume=? where symbol=? and date_tx=?";

        jdbcTemplate.update(sql, open, high, low, close, rec.getVolume(), rec.getSymbol(), date);
    }

    @Override
    public void delete(String symbol, Date date) {

    	// Normalize date
    	SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(date);
        Date				date_normal   		= new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote delete date format exception");
        }

    	String sql = "DELETE FROM quote_tbl where symbol=? and date_tx=?";
        jdbcTemplate.update(sql, symbol, date_normal);
    }

    @Override
    public void deleteAll(String symbol) {
    	  String sql = "DELETE FROM quote_tbl where symbol=?";
        jdbcTemplate.update(sql, symbol);
    }

    @Override
    public Quote get(String symbol, Date date) {
    	    	
    	// Normalize date
    	SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(date);
        Date				date_normal   		= new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote get date format exception");
        }

    	String sql = "SELECT * FROM quote_tbl WHERE symbol=? and date_tx=?";
    	RowMapper<Quote> rowMapper = new QuoteRowMapper();
        //return this.jdbcTemplate.queryForObject(sql, rowMapper, symbol, date_normal);
        List<Quote> results = this.jdbcTemplate.query(sql, rowMapper, symbol, date_normal);
        if ( results.size() > 0 )
            return results.get(0);
        else
            return null;
    }

    @Override
    public List<Quote> getAllAsc(String symbol) {
        String sql = "SELECT * FROM quote_tbl WHERE symbol=? ORDER BY date_tx ASC ";
        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);
    }


    @Override
    public List<Quote> getAllDesc(String symbol) {
        String sql = "SELECT * FROM quote_tbl WHERE symbol=? ORDER BY date_tx DESC ";
        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);
    }

    @Override
    public List<Quote> getForPerformanceDesc(String symbol, String date) {

        String sql = "SELECT * FROM quote_tbl WHERE symbol=? and date_tx>='" + date + "' ORDER BY date_tx DESC";
        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);

    }

    @Override
    public List<Quote> getForSplitDesc(String symbol, Date date) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(date);
        Date				date_normal   		= new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote get for split date format exception");
        }

        String sql = "SELECT * FROM quote_tbl WHERE symbol=? and date_tx<? ORDER by date_tx DESC";
        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol, date_normal);
    }

    @Override
    public List<Quote> getYearsWorthDesc(String symbol) {
        String sql = "SELECT * FROM quote_tbl WHERE symbol=? ORDER BY date_tx DESC";

        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);
    }

    @Override
    public List<Quote> getYearsWorthDescFromDate(String symbol, String date) {
        String sql = "SELECT * FROM quote_tbl WHERE symbol=? and date_tx<='" + date + "' ORDER BY date_tx DESC LIMIT 255";

        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);
    }

    public List<Quote> getForAnalyticsDesc(String symbol){
        String sql = "SELECT * FROM quote_tbl WHERE symbol=? ORDER BY date_tx DESC LIMIT 460";

        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);
    }

    @Override
    public List<Quote> getByYearAsc(String symbol, int year) {

        String startDate = String.valueOf(year - 1) + "-12-31";
        String endDate   = String.valueOf(year + 1) + "-01-01";
        String sql		 = "SELECT * FROM quote_tbl WHERE symbol=? and date_tx>'" + startDate +
                "' and date_tx<'" + endDate + "' ORDER BY date_tx ASC";

        RowMapper<Quote> rowMapper = new QuoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, symbol);
    }

}
