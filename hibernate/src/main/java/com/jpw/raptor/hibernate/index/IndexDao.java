package com.jpw.raptor.hibernate.index;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Index;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface IndexDao extends CrudRepository<Index, String> {

    public int sqlScript(String script);


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
            value = "INSERT INTO index_tbl (symbol) values (?1)",
            nativeQuery = true )
    public void addEmpty(String symbol);

    @Query(
            value = "SELECT * FROM index_tbl WHERE symbol=?1",
            nativeQuery = true)
    public Index get(String symbol);

    @Query(
            value = "SELECT * FROM index_tbl ORDER by symbol ASC",
            nativeQuery = true)
    public List<Index> getAll();

}

