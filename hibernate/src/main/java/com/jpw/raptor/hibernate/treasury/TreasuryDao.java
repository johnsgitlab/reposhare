package com.jpw.raptor.hibernate.treasury;

import com.jpw.raptor.model.Treasury;

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
public interface TreasuryDao extends CrudRepository<Treasury, Date> {

    //public int sqlScript(String script);


    /*
     to Add or Update
     TreasuryRepository.save(Treasury)
     */

    /*
     to Delete
     TreasuryRepository.delete(Treasury)
     */

    /*
     to get all
     List<Treasury> = TreasuryRepository.findAll();
     */

    /*
     to get one
     Optional<Treasury> = TreasuryRepository.findOne(Date date);
     */

    @Query(
            value = "SELECT * FROM treasury_tbl WHERE date+tx=?",
            nativeQuery = true)
    public Treasury get(String date);

    @Query(
            value = "SELECT * FROM treasury_tbl ORDER BY date_tx DESC LIMIT 1",
            nativeQuery = true)
    public Treasury getLast();

    @Query(
            value = "SELECT * FROM treasury_tbl ORDER BY date_tx DESC",
            nativeQuery = true)
    public Treasury getAll();

}

