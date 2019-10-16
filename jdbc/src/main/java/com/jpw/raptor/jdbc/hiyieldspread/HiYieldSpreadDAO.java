package com.jpw.raptor.jdbc.hiyieldspread;


import com.jpw.raptor.model.HiYieldSpread;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.text.DecimalFormat;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 12/6/18.
 */
@Repository
public class HiYieldSpreadDAO implements HiYieldSpreadInterface {


    private static final Logger logger = LoggerFactory.getLogger(HiYieldSpreadDAO.class);

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
    public void delete(Date date) {

        // Normalize date
        SimpleDateFormat   simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        String             date_string       = simpleDateFormat.format(date);
        Date			   date_normal   	 = new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("HiYieldSpread delete date format exception");
        }

        String sql = "DELETE FROM hi_yield_spread_tbl where date_tx=?";
        jdbcTemplate.update(sql, date_normal);
    }

    @Override
    public void upsert(HiYieldSpread rec) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String              date_tx          = simpleDateFormat.format(rec.getDate());

        DecimalFormat       df               = new DecimalFormat("#.##");
        String              spread           = df.format(rec.getSpread());
        

        String  sql =
                "INSERT INTO hi_yield_spread_tbl "
                        + "(date_tx, spread)"
                        + " VALUES ("
                        + "'" + date_tx + "', "
                        +       spread + " ) " +
                        " ON CONFLICT (date_tx) DO UPDATE SET" +
                        " spread=" + spread
                ;

        //System.out.println(sql)

        jdbcTemplate.update(sql);

    }

    @Override
    public HiYieldSpread get(Date date) {

        // Normalize date
        SimpleDateFormat   simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        String             date_string       = simpleDateFormat.format(date);
        Date			   date_normal   	 = new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote get date format exception");
        }

        String sql = "SELECT * FROM hi_yield_spread_tbl WHERE date_tx=?";
        RowMapper<HiYieldSpread> rowMapper = new HiYieldSpreadRowMapper();

        List<HiYieldSpread> results = this.jdbcTemplate.query(sql, rowMapper, date_normal);
        if ( results.size() > 0 )
            return results.get(0);
        else
            return null;
    }

    @Override
    public HiYieldSpread getLast() {
        String sql = "SELECT * FROM hi_yield_spread_tbl ORDER BY date_tx DESC LIMIT 2 ";
        RowMapper<HiYieldSpread> rowMapper = new HiYieldSpreadRowMapper();
        List<HiYieldSpread> results = this.jdbcTemplate.query(sql, rowMapper);
        if ( results.size() > 0 )
            return results.get(0);
        else
            return null;
    }

    @Override
    public List<HiYieldSpread> getAll() {
        String sql = "SELECT * FROM hi_yield_spread_tbl ORDER BY date_tx ASC";
        RowMapper<HiYieldSpread> rowMapper = new HiYieldSpreadRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper );
    }

    @Override
    public List<HiYieldSpread> getAllDesc() {
        String sql = "SELECT * FROM hi_yield_spread_tbl ORDER BY date_tx DESC";
        RowMapper<HiYieldSpread> rowMapper = new HiYieldSpreadRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper );
    }
}
