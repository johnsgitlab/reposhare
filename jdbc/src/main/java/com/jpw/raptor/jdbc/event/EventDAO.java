package com.jpw.raptor.jdbc.event;

import com.jpw.raptor.model.Event;
import com.jpw.raptor.jdbc.event.EventRowMapper;

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

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 5/13/18.
 */
@Repository
public class EventDAO implements EventInterface {

    private static final Logger logger = LoggerFactory.getLogger(EventDAO.class);

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
    public void add(Event rec) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(rec.getDateTx());
        Date                date                = new Date(-1);
        try {
            date = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Event add date format exception");
        }

        String sql = "INSERT INTO event_tbl (row_id, date_tx, category, sub_category, effect, description) values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rec.getRowId(), date, rec.getCategory(), rec.getSubCategory(), rec.getEffect(), rec.getDescription());
    }

    @Override
    public void delete(long rowNo) {

        String sql = "DELETE FROM event_tbl where row_id=?";
        jdbcTemplate.update(sql, rowNo);
    }

    @Override
    public Event get(long rowNo) {
        String sql = "SELECT * FROM event_tbl WHERE row_id=?";
        RowMapper<Event> rowMapper = new EventRowMapper();
        //return this.jdbcTemplate.queryForObject(sql, rowMapper, rowNo);
        List<Event> results = this.jdbcTemplate.query(sql, rowMapper, rowNo);
        if ( results.size() > 0 )
            return results.get(0);
        else
            return null;
    }


    @Override
    public List<Event> getAll() {

        String sql = "SELECT * FROM event_tbl ORDER BY date_tx ASC ";
        RowMapper<Event> rowMapper = new EventRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Event> getByDate(Date start, Date end) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String				date_start   		= simpleDateFormat.format(start);
        String				date_end     		= simpleDateFormat.format(end);

        String sql = "SELECT * FROM event_tbl WHERE date_tx >= ?::date and date_tx <= ?::date ORDER BY date_tx ASC ";
        RowMapper<Event> rowMapper = new EventRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, date_start, date_end);
        //return this.jdbcTemplate.query(sql, rowMapper, start, end);
    }

}

