package com.jpw.raptor.jdbc.stock;

import com.jpw.raptor.model.Stock;


import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface StockInterface {

    public int sqlScript(String script);

    public int addEmpty(String symbol, String name, String sp, String dow, String russell);

    public int update(Stock rec);
    
    public int delete(String symbol);
    
    public Stock get(String symbol);

    public List<Stock> getAll();

    public List<Stock> getOwned();

    public List<Stock> getTracked();
    
    public List<Stock> getRelevant();
	
    public List<Stock> getBySpIndex(String index);

    public List<Stock> getByDowIndex(String index);

    public List<Stock> getByRussellIndex(String index);

    public List<Stock> getBySector(String sector);

    public List<Stock> getByIndustry(String industry);

}
