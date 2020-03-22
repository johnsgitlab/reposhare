package com.jpw.raptor.hibernate.etf;

import com.jpw.raptor.model.Etf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface EtfDao extends CrudRepository<Etf, String> {

    @Query(
            value = "INSERT INTO etf_tbl (symbol) values (?1)",
            nativeQuery = true )
    public void addEmpty(String symbol);

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

    @Query(
            value = "SELECT * FROM etf_tbl WHERE symbol=?1",
            nativeQuery = true)
    public Etf get(String symbol);

    @Query(
            value = "SELECT * FROM etf_tbl ORDER by symbol ASC",
            nativeQuery = true)
    public List<Etf> getAll();

    @Query(
            value = "SELECT * FROM etf_tbl WHERE tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getOwned();

    @Query(
            value = "SELECT * FROM etf_tbl WHERE tracks='tracked' or tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getTracked();

    @Query(
            value = "SELECT * FROM etf_tbl WHERE tracks='relevant' or tracks='tracked' or tracks='own' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getRelevant();

    @Query(
            value = "SELECT * FROM etf_tbl WHERE asset_class=?1",
            nativeQuery = true)
    public List<Etf> getByAssetClass(String assetClass);

    @Query(
            value = "SELECT * FROM etf_tbl WHERE asset_class=?1 and fund_type=?2 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getByAssetClassFundType(String assetClass, String fundType);

    @Query(
            value = "SELECT * FROM etf_tbl WHERE asset_class=?1 and fund_type=?2 and fund_sub_type=?3 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getByAssetClassFundTypeSubType(String assetClass, String fundType, String subType);

    @Query(
            value = "SELECT * FROM etf_tbl WHERE asset_class=?1 and fund_type=?2 and fund_sub_type=?3 and factor=?4 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getByAssetClassFundTypeSubTypeFactor(String assetClass, String fundType, String subType, String factor);

    @Query(
            value = "SELECT * FROM etf_tbl WHERE factor!='' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getFactors();

    @Query(
            value = "SELECT * FROM etf_tbl WHERE factor!='' or fund_sub_type='growth' or fund_sub_type='value' ORDER BY symbol ASC ",
            nativeQuery = true)
    public List<Etf> getFactorsAll();

    @Query(
            value = "SELECT * FROM etf_tbl WHERE asset_class=?1 and factor!='' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getFactorsByAssetClass(String assetClass);

    @Query(
            value = "SELECT * FROM etf_tbl WHERE asset_class=?1 and factor=?2 ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getFactorAndAssetClass(String assetClass, String factor);

    @Query(
            value = "SELECT * FROM etf_tbl WHERE factor!='' or fund_sub_type='growth' or fund_sub_type='value' ORDER BY symbol ASC",
            nativeQuery = true)
    public List<Etf> getFactorsForFactorService();

}