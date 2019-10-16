package com.jpw.raptor.jdbc.index;

import com.jpw.raptor.model.Index;

import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface IndexInterface {

    public int sqlScript(String script);

    public void addEmpty(String symbol);

    public void update(Index rec);
    
    public void delete(String symbol);
    
    public Index get(String symbol);

    public List<Index> getAll();
	 
}
