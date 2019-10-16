package com.jpw.raptor.jdbc.stock;

import com.jpw.raptor.model.Stock;
import com.jpw.raptor.jdbc.stock.StockRowMapper;

import com.jpw.raptor.model.StockListModel;
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
public class StockDAO implements StockInterface {

    private static final Logger logger = LoggerFactory.getLogger(StockDAO.class);

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
    public int addEmpty(String symbol, String name, String sp, String dow, String russell) {
        
        String sql = "INSERT INTO stock_tbl (symbol, name, sp_index, dow_index, russell_index) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, symbol, name, sp, dow, russell);
    }

    @Override
    public int update(Stock v) {

        boolean         	result      = true;
        String					dt				= null;
        String					du		    	= null;
        String					di		    	= null;
        SimpleDateFormat   formatter   = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat      df          = new DecimalFormat("#.####");

        // Format performance date
        if ( v.getDate() != null ) {
            dt = formatter.format(v.getDate());
        } else {
            dt = formatter.format(new Date(-1));
        }
        
        // Format scrape date
        if ( v.getLastUpdate() != null ) {
            du = formatter.format(v.getLastUpdate());
        } else {
            du = formatter.format(new Date(-1));
        }

        // Create SQL
        String      sql         = "UPDATE stock_tbl SET" +
                " name='" + v.getName() + "'" +
                ", sp_index='" + v.getSpIndex() + "'" +
                ", dow_index='" + v.getDowIndex() + "'" +
                ", russell_index='" + v.getRussellIndex() + "'" +
                ", sector='" + v.getSector() + "'" +
                ", industry='" + v.getIndustry() + "'" +
                ", tracks='" + v.getTracks() + "'" +
                ", exchange='" + v.getExchange() + "'" +

                ", recommendations=" + v.getRecommendations() +
                ", strong_buy=" + v.getStrongBuy() +
                ", buy=" + v.getBuy() +
                ", hold=" + v.getHold() +
				", sell=" + v.getSell() +
				", strong_sell=" + v.getStrongSell() +
                
                ", market_cap=" + v.getMarketCap() +
                ", enterprise_value=" + v.getEnterpriseValue() +
                ", operating_cashflow=" + v.getOperatingCashflow() +
                ", ebitda=" + v.getEbitda() +
                ", free_cashflow=" + v.getFreeCashflow() +
                ", total_cash=" + v.getTotalCash() +
                ", total_debt=" + v.getTotalDebt() +
                ", total_revenue=" + v.getTotalRevenue() +
                ", avg_daily_vol=" + v.getAvgDailyVol() +
                
                ", dividend_rate=" + df.format(v.getDividendRate()) +
                ", dividend_yield=" + df.format(v.getDividendYield()) +
                ", beta=" + df.format(v.getBeta()) +
                ", pe=" + df.format(v.getPe()) +
                ", pe_forward=" + df.format(v.getPeForward()) +
                ", ps=" + df.format(v.getPs()) +
                ", pb=" + df.format(v.getPb()) +
                ", trailing_eps=" + df.format(v.getTrailingEps()) +
                ", peg_ratio=" + df.format(v.getPegRatio()) +
                ", enterprise_to_revenue=" + df.format(v.getEnterpriseToRevenue()) +
                ", enterprise_to_ebitda=" + df.format(v.getEnterpriseToEbitda()) +
                ", ebitda_margins=" + df.format(v.getEbitdaMargins()) +
                ", profit_margins=" + df.format(v.getProfitMargins()) +
                ", gross_margins=" + df.format(v.getGrossMargins()) +
                ", revenue_growth=" + df.format(v.getRevenueGrowth()) +
                ", operating_margins=" + df.format(v.getOperatingMargins()) +
                ", earnings_growth=" + df.format(v.getEarningsGrowth()) +
                ", current_ratio=" + df.format(v.getCurrentRatio()) +
                ", return_on_assets=" + df.format(v.getReturnOnAssets()) +
                ", debt_to_equity=" + df.format(v.getDebtToEquity()) +
                ", return_on_equity=" + df.format(v.getReturnOnEquity()) +
                ", total_cash_per_share=" + df.format(v.getTotalCashPerShare()) +

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
        
        return jdbcTemplate.update(sql);
    }

    @Override
    public int delete(String symbol) {

    	String sql = "DELETE FROM stock_tbl where symbol=?";
        return jdbcTemplate.update(sql, symbol);
    }


    @Override
    public Stock get(String symbol) {

        try {
            String sql = "SELECT * FROM stock_tbl WHERE symbol=?";
            RowMapper<Stock> rowMapper = new StockRowMapper();
            //return this.jdbcTemplate.queryForObject(sql, rowMapper, symbol);
            List<Stock> results = this.jdbcTemplate.query(sql, rowMapper, symbol);
            if ( results.size() > 0 )
                return results.get(0);
            else
                return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Stock> getAll() {
    	String sql = "SELECT * FROM stock_tbl ORDER by symbol ASC";
    	RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public List<Stock> getOwned() {
        String sql = "SELECT * FROM stock_tbl WHERE tracks='own' ORDER BY symbol ASC";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Stock> getTracked() {
        String sql = "SELECT * FROM stock_tbl WHERE tracks='tracked' or tracks='own' ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Stock> getRelevant() {
        String sql = "SELECT * FROM stock_tbl WHERE tracks='relevant' or tracks='tracked' or tracks='own' ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    
  
    @Override
    public List<Stock> getBySpIndex(String index) {

        String sql = "SELECT * FROM stock_tbl WHERE sp_index=? ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, index);
    }

    @Override
    public List<Stock> getByDowIndex(String index) {

        String sql = "SELECT * FROM stock_tbl WHERE dow_index=? ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, index);
    }

    @Override
    public List<Stock> getByRussellIndex(String index) {

        String sql = "SELECT * FROM stock_tbl WHERE russell_index=? ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, index);
    }


    @Override
    public List<Stock> getBySector(String sector) {

        String sql = "SELECT * FROM stock_tbl WHERE sector=? ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, sector);
    }

    @Override
    public List<Stock> getByIndustry(String industry) {

        String sql = "SELECT * FROM stock_tbl WHERE industry=? ORDER BY symbol ASC ";
        RowMapper<Stock> rowMapper = new StockRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, industry);
    }

}
