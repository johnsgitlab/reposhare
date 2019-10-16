package com.jpw.raptor.jdbc.index;

import com.jpw.raptor.model.Index;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by John on 10/1/2017.
 */
public class IndexRowMapper implements RowMapper<Index>  {

    private static final Logger logger = LoggerFactory.getLogger(IndexRowMapper.class);

    @Override
    public Index mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
        Index rec = new Index();
        
        rec.setSymbol(rs.getString("symbol"));
        rec.setName(rs.getString("name"));
        rec.setDate(rs.getDate("date_tx"));
        rec.setLastUpdate(rs.getDate("last_update"));

        rec.setYtd(rs.getDouble("ytd"));
        rec.setOneDay(rs.getDouble("one_day"));
        rec.setOneWeek(rs.getDouble("one_week"));
        rec.setTwoWeeks(rs.getDouble("two_weeks"));
        rec.setFourWeeks(rs.getDouble("four_weeks"));
        rec.setThreeMonths(rs.getDouble("three_months"));
        rec.setOneYear(rs.getDouble("one_year"));
        rec.setThreeYears(rs.getDouble("three_years"));

        rec.setOverview(rs.getString("overview"));
        
        return rec;
    }
  

}
