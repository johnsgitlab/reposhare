package com.jpw.raptor.jdbc.event;

import com.jpw.raptor.model.Event;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by john on 5/13/18.
 */
public class EventRowMapper implements RowMapper<Event>  {

    private static final Logger logger = LoggerFactory.getLogger(EventRowMapper.class);

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

        Event rec = new Event();
        rec.setRowId(rs.getLong("row_id"));
        rec.setDateTx (rs.getDate("date_tx"));
        rec.setCategory(rs.getString("category"));
        rec.setSubCategory(rs.getString("sub_category"));
        rec.setEffect(rs.getString("effect"));
        rec.setDescription(rs.getString("description"));
        return rec;
    }


}