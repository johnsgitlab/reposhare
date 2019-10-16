package com.jpw.raptor.jdbc.quote;

import com.jpw.raptor.model.Quote;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by John on 10/1/2017.
 */
public class QuoteRowMapper implements RowMapper<Quote>  {

    private static final Logger logger = LoggerFactory.getLogger(QuoteRowMapper.class);

	 @Override
    public Quote mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
        Quote rec = new Quote();
        rec.setSymbol(rs.getString("symbol"));
        rec.setDate (rs.getDate("date_tx"));
        rec.setOpen(rs.getDouble("open"));
        rec.setHigh(rs.getDouble("high"));
        rec.setLow(rs.getDouble("low"));
        rec.setClose(rs.getDouble("close"));
        rec.setVolume(rs.getLong("volume"));
        return rec;
    }
  

}
