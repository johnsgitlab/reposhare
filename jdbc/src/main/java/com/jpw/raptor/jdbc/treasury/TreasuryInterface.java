package com.jpw.raptor.jdbc.treasury;

import com.jpw.raptor.model.Treasury;

import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface TreasuryInterface {

    public int sqlScript(String script);

    public void delete(Date date);

    public void upsert(Treasury rec);

    public Treasury get(Date date);

    public Treasury getLast();

    public List<Treasury> getAll();

}
