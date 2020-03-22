package com.jpw.raptor.hibernate.stock;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface StockDao extends CrudRepository<Stock, String>{

    //public int sqlScript(String script);

    //public List<String> getScrapeSymbols(String select, String symbol);

    /*
     to Add or Update
     EtfRepository.save(Etf)
     */

    /*
     to Delete
     EtfRepository.delete(Etf)
     */

    /*
     to get all
     List<Etf> = EtfRepository.findAll();
     */

    /*
     to get one
     Optional<Etf> = EtfRepository.findOne(String symbol);
     */

    /*
    public List<Quote> getByYear(String symbol, int year) {
     */

    /*
        String startDate = String.valueOf(year - 1) + "-12-31";
        String endDate   = String.valueOf(year + 1) + "-01-01";
    */

    @Query(
            value = "INSERT INTO stock_tbl (symbol, name, sp_index, dow_index, russell_index) values (?1,?2,?3,?4,?5)",
            nativeQuery = true )
    public void addEmpty(String symbol, String name, String sp, String dow, String russell);

    @Query(
            value = "SELECT * FROM stock_tbl WHERE symbol=?1",
            nativeQuery = true)
    public Stock get(String symbol);

    @Query(
            value = "SELECT * FROM stock_tbl ORDER by symbol ASC",
            nativeQuery = true)
    public List<Stock> getAll();

    @Query(
            value = "SELECT * FROM stock_tbl WHERE tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getOwned();

    @Query(
            value = "SELECT * FROM stock_tbl WHERE tracks='tracked' or tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getTracked();

    @Query(
            value = "SELECT * FROM stock_tbl WHERE tracks='relevant' or tracks='tracked' or tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getRelevant();

    @Query(
            value = "SELECT * FROM stock_tbl WHERE sp_index=? ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getBySpIndex(String index);

    @Query(
            value = "SELECT * FROM stock_tbl WHERE dow_index=? ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getByDowIndex(String index);

    @Query(
            value = "SELECT * FROM stock_tbl WHERE russell_index=? ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getByRussellIndex(String index);

    @Query(
            value = "SELECT * FROM stock_tbl WHERE sector=? ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getBySector(String sector);

    @Query(
            value = "SELECT * FROM stock_tbl WHERE industry=? ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Stock> getByIndustry(String industry);

}

