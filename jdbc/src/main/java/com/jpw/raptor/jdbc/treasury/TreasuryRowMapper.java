package com.jpw.raptor.jdbc.treasury;

import com.jpw.raptor.model.Treasury;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by john on 12/6/18.
 */
public class TreasuryRowMapper implements RowMapper<Treasury> {

    private static final Logger logger = LoggerFactory.getLogger(TreasuryRowMapper.class);

    @Override
    public Treasury mapRow(ResultSet rs, int rowNum) throws SQLException {

        Treasury rec = new Treasury();
        rec.setDate (rs.getDate("date_tx"));
        rec.setOneMonth(rs.getDouble("one_month"));
        rec.setTwoMonths(rs.getDouble("two_months"));
        rec.setThreeMonths(rs.getDouble("three_months"));
        rec.setSixMonths(rs.getDouble("six_months"));
        rec.setOneYear(rs.getDouble("one_year"));
        rec.setTwoYears(rs.getDouble("two_years"));
        rec.setThreeYears(rs.getDouble("three_years"));
        rec.setFiveYears(rs.getDouble("five_years"));
        rec.setSevenYears(rs.getDouble("seven_years"));
        rec.setTenYears(rs.getDouble("ten_years"));
        rec.setTwentyYears(rs.getDouble("twenty_years"));
        rec.setThirtyYears(rs.getDouble("thirty_years"));
        return rec;
    }
}
