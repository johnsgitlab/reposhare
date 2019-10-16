package com.jpw.raptor.jdbc.event;

import com.jpw.raptor.model.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by john on 5/13/18.
 */
public interface EventInterface {

    public int sqlScript(String script);

    public void add(Event rec);

    public void delete(long rowNo);

    public Event get(long rowNo);

    public List<Event> getAll();

    public List<Event> getByDate(Date start, Date end);

}
