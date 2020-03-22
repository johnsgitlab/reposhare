package com.jpw.raptor.hibernate.event;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface EventDao extends CrudRepository<Event, Long> {

    /*
     to Add or Update
     EtfRepository.save(Event)
     */

    /*
     to Delete
     EventRepository.delete(Event)
     */

    /*
     to get all
     List<Event> = EventRepository.findAll();
     */

    /*
     to get one
     Optional<Event> = EventRepository.findOne(long id);
     */

    @Query(
            value = "SELECT * FROM event_tbl WHERE row_id=?1",
            nativeQuery = true)
    public Event get(long rowNo);


    @Query(
            value = "SELECT * FROM event_tbl ORDER BY date_tx ASC",
            nativeQuery = true)
    public List<Event> getAll();


    @Query(
            value = "SELECT * FROM event_tbl WHERE date_tx >= ?1 and date_tx <= ?2 ORDER BY date_tx ASC",
            nativeQuery = true)
    public List<Event> getByDate(Date start, Date end);

}
