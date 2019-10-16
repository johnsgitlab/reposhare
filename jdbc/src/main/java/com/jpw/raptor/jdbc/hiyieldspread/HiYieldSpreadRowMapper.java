package com.jpw.raptor.jdbc.hiyieldspread;

import com.jpw.raptor.model.HiYieldSpread;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by john on 12/6/18.
 */
public class HiYieldSpreadRowMapper implements RowMapper<HiYieldSpread> {

    private static final Logger logger = LoggerFactory.getLogger(HiYieldSpreadRowMapper.class);

    @Override
    public HiYieldSpread mapRow(ResultSet rs, int rowNum) throws SQLException {

        HiYieldSpread rec = new HiYieldSpread();
        rec.setDate (rs.getDate("date_tx"));
        rec.setSpread(rs.getDouble("spread"));
        return rec;
    }
}
