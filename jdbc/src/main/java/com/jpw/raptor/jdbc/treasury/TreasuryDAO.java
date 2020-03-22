package com.jpw.raptor.jdbc.treasury;


import com.jpw.raptor.model.Treasury;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.sql.SQLException;
import java.text.DecimalFormat;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 12/6/18.
 */
@Repository
public class TreasuryDAO implements TreasuryInterface {


    private static final Logger logger = LoggerFactory.getLogger(TreasuryDAO.class);

    private DataSource      dataSource;
    private JdbcTemplate    jdbcTemplate;

    public void close() {
        try {
            jdbcTemplate.getDataSource().getConnection().close();
        } catch ( SQLException ex) {}
    }

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
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(date);
        Date				date_normal   		= new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote delete date format exception");
        }

        String sql = "DELETE FROM treasury_tbl where date_tx=?";
        jdbcTemplate.update(sql, date_normal);
    }

    @Override
    public void upsert(Treasury rec) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String              date_tx          = simpleDateFormat.format(rec.getDate());

        DecimalFormat       df               = new DecimalFormat("#.##");
        String              oneMonth         = df.format(rec.getOneMonth());
        String              twoMonths        = df.format(rec.getTwoMonths());
        String              threeMonths      = df.format(rec.getThreeMonths());
        String              sixMonths        = df.format(rec.getSixMonths());
        String              oneYear          = df.format(rec.getOneYear());
        String              twoYears         = df.format(rec.getTwoYears());
        String              threeYears       = df.format(rec.getThreeYears());
        String              fiveYears        = df.format(rec.getFiveYears());
        String              sevenYears       = df.format(rec.getSevenYears());
        String              tenYears         = df.format(rec.getTenYears());
        String              twentyYears      = df.format(rec.getTwentyYears());
        String              thirtyYears      = df.format(rec.getThirtyYears());

        String  sql =
                "INSERT INTO treasury_tbl "
                        + "(date_tx, one_month, two_months, three_months, six_months,"
                        +   " one_year, two_years, three_years, five_years, seven_years, ten_years, twenty_years, thirty_years)"
                        + " VALUES ("
                        + "'" + date_tx + "', "
                        +       oneMonth + ", "
                        +       twoMonths + ", "
                        +       threeMonths + ", "
                        +       sixMonths + ", "
                        +       oneYear + ", "
                        +       twoYears + ","
                        +       threeYears + ","
                        +       fiveYears + ","
                        +       sevenYears + ","
                        +       tenYears + ","
                        +       twentyYears + ","
                        +       thirtyYears + " ) " +
                        " ON CONFLICT (date_tx) DO UPDATE SET" +
                        "  one_month=" + oneMonth +
                        ", two_months=" + twoMonths +
                        ", three_months=" + threeMonths +
                        ", six_months=" + sixMonths +
                        ", one_year=" + oneYear +
                        ", two_years=" + twoYears +
                        ", three_years=" + threeYears +
                        ", five_years=" + fiveYears +
                        ", seven_years=" + sevenYears +
                        ", ten_years=" + tenYears +
                        ", twenty_years=" + twentyYears +
                        ", thirty_years=" + thirtyYears
                ;

        //System.out.println(sql);

        jdbcTemplate.update(sql);

    }

    @Override
    public Treasury get(Date date) {

        // Normalize date
        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = simpleDateFormat.format(date);
        Date				date_normal   		= new Date(-1);
        try {
            date_normal = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote get date format exception");
        }

        String sql = "SELECT * FROM treasury_tbl WHERE date_tx=?";
        RowMapper<Treasury> rowMapper = new TreasuryRowMapper();

        List<Treasury> results = this.jdbcTemplate.query(sql, rowMapper, date_normal);
        if ( results.size() > 0 )
            return results.get(0);
        else
            return null;
    }

    @Override
    public Treasury getLast() {
        String sql = "SELECT * FROM treasury_tbl ORDER BY date_tx DESC LIMIT 2 ";
        RowMapper<Treasury> rowMapper = new TreasuryRowMapper();
        List<Treasury> results = this.jdbcTemplate.query(sql, rowMapper);
        if ( results.size() > 0 )
            return results.get(0);
        else
            return null;
    }

    @Override
    public List<Treasury> getAll() {
        String sql = "SELECT * FROM treasury_tbl ORDER BY date_tx ASC";
        RowMapper<Treasury> rowMapper = new TreasuryRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper );
    }

}
