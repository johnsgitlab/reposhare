package com.jpw.raptor.hibernate.index;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Index;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface IndexDao extends CrudRepository<Index, String> {

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO index_tbl (symbol) VALUES (?1)",
            nativeQuery = true )
    public void addEmpty(String symbol);

    /*
     to Add or Update
     IndexRepository.save(Index)
     */

    /*
     to Delete
     IndexRepository.delete(Index)
     */

    /*
     to get all
     List<Index> = IndexRepository.findAll();
     */

    /*
     to get one
     Optional<Index> = IndexRepository.findOne(String symbol);
     */

    @Query(
            value = "SELECT * FROM index_tbl WHERE symbol=?1",
            nativeQuery = true)
    public Index get(String symbol);

    @Query(
            value = "SELECT * FROM index_tbl ORDER by symbol ASC",
            nativeQuery = true)
    public List<Index> getAll();

}

