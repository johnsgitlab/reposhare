package com.jpw.raptor.hibernate.quote;

import com.jpw.raptor.model.QuoteId;
import com.jpw.raptor.model.Quote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface QuoteDao extends CrudRepository<Quote, QuoteId> {

    /*
     to Add or Update
     QuoteRepository.save(Quote)
     */

    /*
     to Delete
     QuoteRepository.delete(Quote)
     */

    /*
     to get all
     List<Quote> = QuoteRepository.findAll();
     */

    /*
     to get one
     Optional<Quote> = QuoteRepository.findOne(QuoteId id);
     */

    /*
        String date (yyyy-MM-dd)
    */
    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM quote_tbl where symbol=?1 and date_tx=?2",
            nativeQuery = true)
    public void delete(String symbol, Date date);

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM quote_tbl where symbol=?1",
            nativeQuery = true)
    public void deleteAll(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl where symbol=?1 and date_tx=?2",
            nativeQuery = true)
    public Quote get(String symbol, Date date);

    /*
        String startDate = String.valueOf(year - 1) + "-12-31";
        String endDate   = String.valueOf(year + 1) + "-01-01";
    */
    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 and date_tx>?2 and date_tx<?3 ORDER BY date_tx ASC",
            nativeQuery = true)
    public List<Quote> getByYear(String symbol, Date startDate, Date endDate);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 ORDER BY date_tx DESC",
            nativeQuery = true)
    public List<Quote> getAll(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 ORDER BY date_tx DESC LIMIT 2",
            nativeQuery = true)
    public List<Quote> getLast(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 ORDER BY date_tx DESC LIMIT 255",
            nativeQuery = true)
    public List<Quote> getYearsWorth(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 ORDER BY date_tx DESC LIMIT 510",
            nativeQuery = true)
    public List<Quote> getTwoYearsWorth(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 ORDER BY date_tx ASC",
            nativeQuery = true)
    public List<Quote> getAllAsc(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 and date_tx<?2 ORDER by date_tx ASC",
            nativeQuery = true)
    public List<Quote> getForSplit(String symbol, Date date);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 ORDER BY date_tx DESC LIMIT 460",
            nativeQuery = true)
    public List<Quote> getForAnalytics(String symbol);

    @Query(
            value = "SELECT * FROM quote_tbl WHERE symbol=?1 and date_tx>=?2 ORDER BY date_tx ASC",
            nativeQuery = true)
    public List<Quote> getForPerformance(String symbol, Date date);



}

