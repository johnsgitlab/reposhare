package com.jpw.raptor.jdbc.etf;

import com.jpw.raptor.model.Etf;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by John on 10/1/2017.
 */
public class EtfRowMapper implements RowMapper<Etf>  {

    private static final Logger logger = LoggerFactory.getLogger(EtfRowMapper.class);

    @Override
    public Etf mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
        Etf rec = new Etf();
        rec.setSymbol(rs.getString("symbol"));
        rec.setName(rs.getString("name"));
        rec.setAssetClass(rs.getString("asset_class"));
        rec.setFundType(rs.getString("fund_type"));
        rec.setFundSubType(rs.getString("fund_sub_type"));
        rec.setFactor(rs.getString("factor"));
        rec.setCategory(rs.getString("category"));
        rec.setTracks(rs.getString("tracks"));
        rec.setDate(rs.getDate("date_tx"));
        rec.setLastUpdate(rs.getDate("last_update"));
        rec.setFamily(rs.getString("family"));
        rec.setExchange(rs.getString("exchange"));

        rec.setUnderlyingIndex(rs.getString("underlying_index"));
        rec.setAvgDailyVol(rs.getDouble("avg_daily_vol"));
        rec.setInception(rs.getDate("inception"));
        rec.setMarketCap(rs.getDouble("market_cap"));
        rec.setExpenseRatio(rs.getDouble("expense_ratio"));
        rec.setDividendYield(rs.getDouble("dividend_yield"));

        rec.setMorningRating(rs.getString("morning_rating"));
        rec.setMorningStars(rs.getInt("morning_stars"));
        rec.setLipperTotal(rs.getInt("lipper_total"));
        rec.setLipperConsistent(rs.getInt("lipper_consistent"));
        rec.setLipperPreservation(rs.getInt("lipper_preservation"));
        rec.setLipperTax(rs.getInt("lipper_tax"));
        rec.setLipperExpense(rs.getInt("lipper_expense"));

        rec.setYtd(rs.getDouble("ytd"));
        rec.setOneDay(rs.getDouble("one_day"));
        rec.setOneWeek(rs.getDouble("one_week"));
        rec.setTwoWeeks(rs.getDouble("two_weeks"));
        rec.setFourWeeks(rs.getDouble("four_weeks"));
        rec.setThreeMonths(rs.getDouble("three_months"));
        rec.setOneYear(rs.getDouble("one_year"));
        rec.setThreeYears(rs.getDouble("three_years"));

        rec.setBasicMaterials(rs.getDouble("basic_materials"));
        rec.setConsumerCyclical(rs.getDouble("consumer_cyclical"));
        rec.setFinancialServices(rs.getDouble("financial_services"));
        rec.setRealestate(rs.getDouble("realestate"));
        rec.setConsumerDefensive(rs.getDouble("consumer_defensive"));
        rec.setHealthcare(rs.getDouble("healthcare"));
        rec.setUtilities(rs.getDouble("utilities"));
        rec.setCommunicationServices(rs.getDouble("communication_services"));
        rec.setEnergy(rs.getDouble("energy"));
        rec.setIndustrials(rs.getDouble("industrials"));
        rec.setTechnology(rs.getDouble("technology"));

        rec.setAlpha(rs.getDouble("alpha"));
        rec.setBeta(rs.getDouble("beta"));
        rec.setMeanAnnualReturn(rs.getDouble("mean_annual_return"));
        rec.setRSquared(rs.getDouble("r_squared"));
        rec.setDeviation(rs.getDouble("deviation"));
        rec.setSharpeRatio(rs.getDouble("sharpe_ratio"));
        rec.setTreynorRatio(rs.getDouble("treynor_ratio"));

        rec.setPe(rs.getDouble("pe"));
        rec.setPb(rs.getDouble("pb"));
        rec.setPs(rs.getDouble("ps"));
        rec.setPc(rs.getDouble("pc"));
        rec.setEarningsGrowth(rs.getDouble("earnings_growth"));
        rec.setMedianMarketCap(rs.getDouble("median_market_cap"));

        rec.setBondMaturity(rs.getDouble("bond_maturity"));
        rec.setBondDuration(rs.getDouble("bond_duration"));
        rec.setBondCreditQuality(rs.getDouble("bond_credit_quality"));
        rec.setBondAaaPercent(rs.getDouble("bond_aaa_percent"));
        rec.setBondAaPercent(rs.getDouble("bond_aa_percent"));
        rec.setBondAPercent(rs.getDouble("bond_a_percent"));
        rec.setBondBbbPercent(rs.getDouble("bond_bbb_percent"));
        rec.setBondBbPercent(rs.getDouble("bond_bb_percent"));
        rec.setBondBPercent(rs.getDouble("bond_b_percent"));
        rec.setBondBelowbPercent(rs.getDouble("bond_belowb_percent"));
        rec.setBondUsPercent(rs.getDouble("bond_us_percent"));
        rec.setBondOtherPercent(rs.getDouble("bond_other_percent"));

        rec.setBondPosition(rs.getDouble("bond_position"));
        rec.setCashPosition(rs.getDouble("cash_position"));
        rec.setConvertiblePosition(rs.getDouble("convertible_Position"));
        rec.setOtherPosition(rs.getDouble("other_position"));
        rec.setPreferredPosition(rs.getDouble("preferred_position"));
        rec.setStockPosition(rs.getDouble("stock_position"));

        rec.setTopHoldings(rs.getString("top_holdings"));
        rec.setOverview(rs.getString("overview"));

        return rec;
    }
  

}
