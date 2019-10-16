package com.jpw.raptor.jdbc.stock;

import com.jpw.raptor.model.Stock;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by John on 10/1/2017.
 */
public class StockRowMapper implements RowMapper<Stock>  {

    private static final Logger logger = LoggerFactory.getLogger(StockRowMapper.class);

    @Override
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
        Stock rec = new Stock();
        rec.setSymbol(rs.getString("symbol"));
        rec.setName(rs.getString("name"));
        
        rec.setLastUpdate(rs.getDate("last_update")); // scrape

        rec.setSpIndex(rs.getString("sp_index"));
        rec.setDowIndex(rs.getString("dow_index"));
        rec.setRussellIndex(rs.getString("russell_index"));
        rec.setSector(rs.getString("sector"));
        rec.setIndustry(rs.getString("industry"));
        rec.setTracks(rs.getString("tracks"));
        rec.setExchange(rs.getString("exchange"));


        rec.setRecommendations(rs.getInt("recommendations"));
        rec.setStrongBuy(rs.getInt("strong_buy"));
        rec.setBuy(rs.getInt("buy"));
        rec.setHold(rs.getInt("hold"));
        rec.setSell(rs.getInt("sell"));
        rec.setStrongSell(rs.getInt("strong_sell"));

        rec.setMarketCap(rs.getLong("market_cap"));
        rec.setEnterpriseValue(rs.getLong("enterprise_value"));
        rec.setOperatingCashflow(rs.getLong("operating_cashflow"));
        rec.setEbitda(rs.getLong("ebitda"));
        rec.setFreeCashflow(rs.getLong("free_cashflow"));
        rec.setTotalCash(rs.getLong("total_cash"));
        rec.setTotalDebt(rs.getLong("total_debt"));
        rec.setTotalRevenue(rs.getLong("total_revenue"));
        rec.setAvgDailyVol(rs.getLong("avg_daily_vol"));

        rec.setDividendRate(rs.getDouble("dividend_rate"));
        rec.setDividendYield(rs.getDouble("dividend_yield"));
        rec.setBeta(rs.getDouble("beta"));
        rec.setPe(rs.getDouble("pe"));
        rec.setPeForward(rs.getDouble("pe_forward"));
        rec.setPs(rs.getDouble("ps"));
        rec.setPb(rs.getDouble("pb"));
        rec.setTrailingEps(rs.getDouble("trailing_eps"));
        rec.setPegRatio(rs.getDouble("peg_ratio"));
        rec.setEnterpriseToRevenue(rs.getDouble("enterprise_to_revenue"));
        rec.setEnterpriseToEbitda(rs.getDouble("enterprise_to_ebitda"));
        rec.setEbitdaMargins(rs.getDouble("ebitda_margins"));
        rec.setProfitMargins(rs.getDouble("profit_margins"));
        rec.setGrossMargins(rs.getDouble("gross_margins"));
        rec.setRevenueGrowth(rs.getDouble("revenue_growth"));
        rec.setOperatingMargins(rs.getDouble("operating_margins"));
        rec.setEarningsGrowth(rs.getDouble("earnings_growth"));
        rec.setCurrentRatio(rs.getDouble("current_ratio"));
        rec.setReturnOnAssets(rs.getDouble("return_on_assets"));
        rec.setDebtToEquity(rs.getDouble("debt_to_equity"));
        rec.setReturnOnEquity(rs.getDouble("return_on_equity"));
        rec.setTotalCashPerShare(rs.getDouble("total_cash_per_share"));

        rec.setOverview(rs.getString("overview"));

		rec.setDate(rs.getDate("date_tx")); // Performance
        rec.setYtd(rs.getDouble("ytd"));
        rec.setOneDay(rs.getDouble("one_day"));
        rec.setOneWeek(rs.getDouble("one_week"));
        rec.setTwoWeeks(rs.getDouble("two_weeks"));
        rec.setFourWeeks(rs.getDouble("four_weeks"));
        rec.setThreeMonths(rs.getDouble("three_months"));
        rec.setOneYear(rs.getDouble("one_year"));
        rec.setThreeYears(rs.getDouble("three_years"));

        return rec;
    }
  

}
