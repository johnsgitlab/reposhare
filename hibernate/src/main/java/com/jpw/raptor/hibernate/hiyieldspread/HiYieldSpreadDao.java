package com.jpw.raptor.hibernate.hiyieldspread;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.HiYieldSpread;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface HiYieldSpreadDao  extends CrudRepository<HiYieldSpread, Date> {

    /*
     to Add or Update
     Repository.save(HiYieldSpread)
    */

    /*
     to Delete
     Repository.delete(HiYieldSpread)
     */

    /*
     to get all
     List<HiYieldSpread> = Repository.findAll();
     */

    /*
     to get one
     Optional<HiYieldSpread> = Repository.findOne(Date d);
     */

    @Query(
            value = "SELECT * FROM hi_yield_spread_tbl where date_tx=?1",
            nativeQuery = true)
    public HiYieldSpread get(Date dt);

    @Query(
            value = "SELECT * FROM hi_yield_spread_tbl ORDER by date_tx ASC",
            nativeQuery = true)
    public List<HiYieldSpread> getAll();

    @Query(
            value = "SELECT * FROM hi_yield_spread_tbl ORDER BY date_tx DESC LIMIT 2",
            nativeQuery = true)
    public List<HiYieldSpread> getLast();

}
