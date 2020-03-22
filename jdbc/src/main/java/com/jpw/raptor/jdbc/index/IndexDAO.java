package com.jpw.raptor.jdbc.index;

import com.jpw.raptor.model.Index;
import com.jpw.raptor.jdbc.index.IndexRowMapper;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by John on 10/1/2017.
 */
@Repository
public class IndexDAO implements IndexInterface {

    private static final Logger logger = LoggerFactory.getLogger(IndexDAO.class);

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
    public void addEmpty(String symbol) {
        
    	String sql = "INSERT INTO index_tbl (symbol) values (?)";
        jdbcTemplate.update(sql, symbol);
    }

    @Override
    public void update(Index v) {

        boolean             result      = true;
        String				dt			= null;
        String				du		    = null;
        SimpleDateFormat    formatter   = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat       df          = new DecimalFormat("#.####");

        // Format dates
        if ( v.getDate() != null ) {
            dt = formatter.format(v.getDate());
        } else {
            dt = formatter.format(new Date(-1));
        }

        if ( v.getLastUpdate() != null ) {
            du = formatter.format(v.getLastUpdate());
        } else {
            du = formatter.format(new Date(-1));
        }

        // sql object
        String      sql         = "UPDATE index_tbl SET" +
                " name='" + v.getName() + "'" +
                ", date_tx='" + dt + "'" +
                ", last_update='" + du + "'" +
                ", ytd=" + df.format(v.getYtd()) +
                ", one_day=" + df.format(v.getOneDay()) +
                ", one_week=" + df.format(v.getOneWeek()) +
                ", two_weeks=" + df.format(v.getTwoWeeks()) +
                ", four_weeks=" + df.format(v.getFourWeeks()) +
                ", three_months=" + df.format(v.getThreeMonths()) +
                ", one_year=" + df.format(v.getOneYear()) +
                ", three_years=" + df.format(v.getThreeYears()) +
                ", overview='" + v.getOverview() + "'" +
                " WHERE symbol='" + v.getSymbol() + "'";

        jdbcTemplate.update(sql);
    }

    @Override
    public void delete(String symbol) {

    	String sql = "DELETE FROM index_tbl where symbol=?";
        jdbcTemplate.update(sql, symbol);
    }

    @Override
    public Index get(String symbol) {

        try {
    	    String sql = "SELECT * FROM index_tbl WHERE symbol=?";
    	    RowMapper<Index> rowMapper = new IndexRowMapper();
            //return this.jdbcTemplate.queryForObject(sql, rowMapper, symbol);
            List<Index> results = this.jdbcTemplate.query(sql, rowMapper, symbol);
            if ( results.size() > 0 )
                return results.get(0);
            else
                return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Index> getAll() {
    	String sql = "SELECT * FROM index_tbl";
    	RowMapper<Index> rowMapper = new IndexRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

}
