package com.jpw.raptor.jdbc.fund;


import com.jpw.raptor.jdbc.fund.FundInterface;

import com.jpw.raptor.model.Fund;
import com.jpw.raptor.jdbc.fund.FundRowMapper;

import com.jpw.raptor.model.ListModel;
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
public class FundDAO implements FundInterface {

    private static final Logger logger = LoggerFactory.getLogger(FundDAO.class);

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
    public int addEmpty(String symbol) {
        
    	String sql = "INSERT INTO fund_tbl (symbol) values (?)";
        return jdbcTemplate.update(sql, symbol);
    }

    @Override
    public int update(Fund v) {

        boolean             result      = true;
        String				dt			= null;
        String				du		    = null;
        String				di		    = null;
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
        if ( v.getInception() != null ) {
            di = formatter.format(v.getInception());
        } else {
            di = formatter.format(new Date(-1));
        }

        // Create SQL
        String      sql         = "UPDATE fund_tbl SET" +
                " name='" + v.getName() + "'" +
                ", asset_class='" + v.getAssetClass() + "'" +
                ", fund_type='" + v.getFundType() + "'" +
                ", fund_sub_type='" + v.getFundSubType() + "'" +
                ", factor='" + v.getFactor() + "'" +
                ", category='" + v.getCategory() + "'" +
                ", tracks='" + v.getTracks() + "'" +
                ", date_tx='" + dt + "'" +
                ", last_update='" + du + "'" +
                ", family='" + v.getFamily() + "'" +
                ", exchange='" + v.getExchange() + "'" +
                ", status='" + v.getStatus() + "'" +
                ", minimum=" + df.format(v .getMinimum()) +
                ", front_load=" + df.format(v .getFrontLoad()) +
                ", back_load=" + df.format(v .getBackLoad()) +
                ", inception='" + di + "'" +
                ", market_cap=" + df.format(v.getMarketCap()) +
                ", expense_ratio=" + df.format(v.getExpenseRatio()) +
                ", dividend_yield=" + df.format(v.getDividendYield()) +
                ", morning_rating='" + v.getMorningRating() + "'" +
                ", morning_stars=" + v.getMorningStars() +
                ", lipper_total=" + v.getLipperTotal() +
                ", lipper_consistent=" + v.getLipperConsistent() +
                ", lipper_preservation=" + v.getLipperPreservation() +
                ", lipper_tax=" + v.getLipperTax() +
                ", lipper_expense=" + v.getLipperExpense() +
                ", ytd=" + df.format(v.getYtd()) +
                ", one_day=" + df.format(v.getOneDay()) +
                ", one_week=" + df.format(v.getOneWeek()) +
                ", two_weeks=" + df.format(v.getTwoWeeks()) +
                ", four_weeks=" + df.format(v.getFourWeeks()) +
                ", three_months=" + df.format(v.getThreeMonths()) +
                ", one_year=" + df.format(v.getOneYear()) +
                ", three_years=" + df.format(v.getThreeYears()) +
                ", basic_materials=" + df.format(v.getBasicMaterials()) +
                ", consumer_cyclical=" + df.format(v.getConsumerCyclical()) +
                ", financial_services=" + df.format(v.getFinancialServices()) +
                ", realestate=" + df.format(v.getRealestate()) +
                ", consumer_defensive=" + df.format(v.getConsumerDefensive()) +
                ", healthcare=" + df.format(v.getHealthcare()) +
                ", utilities=" + df.format(v.getUtilities()) +
                ", communication_services=" + df.format(v.getCommunicationServices()) +
                ", energy=" + df.format(v.getEnergy()) +
                ", industrials=" + df.format(v.getIndustrials()) +
                ", technology=" + df.format(v.getTechnology()) +
                ", alpha=" + df.format(v.getAlpha()) +
                ", beta=" + df.format(v.getBeta()) +
                ", mean_annual_return=" + df.format(v.getMeanAnnualReturn()) +
                ", r_squared=" + df.format(v.getRSquared()) +
                ", deviation=" + df.format(v.getDeviation()) +
                ", sharpe_ratio=" + df.format(v.getSharpeRatio()) +
                ", treynor_ratio=" + df.format(v.getTreynorRatio()) +
                ", pe=" + df.format(v.getPe()) +
                ", pb=" + df.format(v.getPb()) +
                ", ps=" + df.format(v.getPs()) +
                ", pc=" + df.format(v.getPc()) +
                ", earnings_growth=" + df.format(v.getEarningsGrowth()) +
                ", median_market_cap=" + df.format(v.getMedianMarketCap()) +
                ", bond_maturity=" + df.format(v.getBondMaturity()) +
                ", bond_duration=" + df.format(v.getBondDuration()) +
                ", bond_credit_quality=" + df.format(v.getBondCreditQuality()) +
                ", bond_aaa_percent=" + df.format(v.getBondAaaPercent()) +
                ", bond_aa_percent=" + df.format(v.getBondAaPercent()) +
                ", bond_a_percent=" + df.format(v.getBondAPercent()) +
                ", bond_bbb_percent=" + df.format(v.getBondBbbPercent()) +
                ", bond_bb_percent=" + df.format(v.getBondBbPercent()) +
                ", bond_b_percent=" + df.format(v.getBondBPercent()) +
                ", bond_belowb_percent=" + df.format(v.getBondBelowbPercent()) +
                ", bond_us_percent=" + df.format(v.getBondUsPercent()) +
                ", bond_other_percent=" + df.format(v.getBondOtherPercent()) +
                ", bond_position=" + df.format(v.getBondPosition()) +
                ", cash_position=" + df.format(v.getCashPosition()) +
                ", convertible_Position=" + df.format(v.getConvertiblePosition()) +
                ", other_position=" + df.format(v.getOtherPosition()) +
                ", preferred_position=" + df.format(v.getPreferredPosition()) +
                ", stock_position=" + df.format(v.getStockPosition()) +
                ", top_holdings='" + v.getTopHoldings() + "'" +
                ", overview='" + v.getOverview() + "'" +
                " WHERE symbol='" + v.getSymbol() + "'";
        
        return jdbcTemplate.update(sql);
    }

    @Override
    public int delete(String symbol) {

    	String sql = "DELETE FROM fund_tbl where symbol=?";
        return jdbcTemplate.update(sql, symbol);
    }

    @Override
    public boolean validFund(String symbol) {

        String sql = "SELECT * FROM fund_tbl WHERE symbol=?";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        //if ( this.jdbcTemplate.queryForObject(sql, rowMapper, symbol) == null )
        //    return false;
        //else
        //    return true;

        List<Fund> results = this.jdbcTemplate.query(sql, rowMapper, symbol);
        if ( results.size() > 0 )
            return true;
        else
            return false;
    }

    @Override
    public Fund get(String symbol) {

        try {
            String sql = "SELECT * FROM fund_tbl WHERE symbol=?";
            RowMapper<Fund> rowMapper = new FundRowMapper();
            //return this.jdbcTemplate.queryForObject(sql, rowMapper, symbol);
            List<Fund> results = this.jdbcTemplate.query(sql, rowMapper, symbol);
            if ( results.size() > 0 )
                return results.get(0);
            else
                return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Fund> getAll() {
    	String sql = "SELECT * FROM fund_tbl ORDER by symbol ASC";
    	RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Fund> getOwned() {
        String sql = "SELECT * FROM fund_tbl WHERE tracks='own' ORDER BY symbol ASC";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Fund> getTracked() {
        String sql = "SELECT * FROM fund_tbl WHERE tracks='tracked' or tracks='own' ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Fund> getRelevant() {
        String sql = "SELECT * FROM fund_tbl WHERE tracks='relevant' or tracks='tracked' or tracks='own' ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Fund> getByAssetClass(String assetClass) {

        String sql = "SELECT * FROM fund_tbl WHERE asset_class=?  ORDER by symbol ASC";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, assetClass);
    }

    @Override
    public List<Fund> getByAssetClassFundType(String assetClass, String fundType) {

        String sql = "SELECT * FROM fund_tbl WHERE asset_class=? and fund_type=? ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, assetClass, fundType);
    }

    @Override
    public List<Fund> getByAssetClassFundTypeSubType(String assetClass, String fundType, String subType) {

        String sql = "SELECT * FROM fund_tbl WHERE asset_class=? and fund_type=? and fund_sub_type=? ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, assetClass, fundType, subType);
    }

    @Override
    public List<Fund> getByAssetClassFundTypeSubTypeFactor(String assetClass, String fundType, String subType, String factor)  {

        String sql = "SELECT * FROM fund_tbl WHERE asset_class=? and fund_type=? and fund_sub_type=? and factor=? ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, assetClass, fundType, subType, factor);
    }

    @Override
    public List<Fund> getFactors() {
        String sql = "SELECT * FROM fund_tbl WHERE factor!='' ORDER BY symbol ASC ";
        //String sql = "SELECT * FROM fund WHERE factor is not null ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Fund> getFactorsAll() {
        String sql = "SELECT * FROM fund_tbl WHERE factor!='' or fund_sub_type='growth' or fund_sub_type='value' ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Fund> getFactorsByAssetClass(String assetClass) {

        String sql = "SELECT * FROM fund_tbl WHERE asset_class=? and factor!='' ORDER BY symbol ASC ";
        RowMapper<Fund> rowMapper = new FundRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, assetClass);
    }

}
