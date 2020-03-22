package com.jpw.raptor.hibernate.fund;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface FundDao extends CrudRepository<Fund, String> {


    //public boolean validFund(String symbol);

    //public List<String> getScrapeSymbols(String select, String symbol);

    /*
     to Add or Update
     FundRepository.save(Fund)
     */

    /*
     to Delete
     FundRepository.delete(Fund)
     */

    /*
     to get all
     List<Fund> = FundRepository.findAll();
     */

    /*
     to get one
     Optional<Fund> = FundRepository.findOne(String symbol);
     */

    @Query(
            value = "INSERT INTO fund_tbl (symbol) values (?1)",
            nativeQuery = true )
    public void addEmpty(String symbol);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE symbol=?1",
            nativeQuery = true)
    public Fund get(String symbol);

    @Query(
            value = "SELECT * FROM fund_tbl",
            nativeQuery = true)
    public List<Fund> getAll();

    @Query(
            value = "SELECT * FROM fund_tbl WHERE tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getOwned();

    @Query(
            value = "SELECT * FROM fund_tbl WHERE tracks='tracked' or tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getTracked();

    @Query(
            value = "SELECT * FROM fund_tbl WHERE tracks='relevant' or tracks='tracked' or tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getRelevant();

    @Query(
            value = "SELECT * FROM fund_tbl WHERE asset_class=?1",
            nativeQuery = true)
    public List<Fund> getByAssetClass(String assetClass);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE asset_class=?1 and fund_type=?2 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getByAssetClassFundType(String assetClass, String fundType);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE asset_class=?1 and fund_type=?2 and fund_sub_type=?3 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getByAssetClassFundTypeSubType(String assetClass, String fundType, String subType);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE asset_class=?1 and fund_type=?2 and fund_sub_type=?3 and factor=?4 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getByAssetClassFundTypeSubTypeFactor(String assetClass, String fundType, String subType, String factor);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE factor!='' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getFactors();

    @Query(
            value = "SELECT * FROM fund_tbl WHERE factor!='' or fund_sub_type='growth' or fund_sub_type='value' ORDER BY symbol ASC ",
            nativeQuery = true)
    public List<Fund> getFactorsAll();

    @Query(
            value = "SELECT * FROM fund_tbl WHERE asset_class=?1 and factor!='' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getFactorsByAssetClass(String assetClass);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE asset_class=?1 and factor=?2 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getFactorsByAssetClass(String assetClass, String factor);

    @Query(
            value = "SELECT * FROM fund_tbl WHERE factor!='' or fund_sub_type='growth' or fund_sub_type='value' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Fund> getFactorsForFactorService();

}

