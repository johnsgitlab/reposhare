package com.jpw.raptor.jdbc.etf;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.ListModel;

import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface EtfInterface {

    public int sqlScript(String script);

    public void addEmpty(String symbol);

    public int update(Etf rec);
    
    public int delete(String symbol);
    
    public Etf get(String symbol);

    public List<Etf> getAll();

    public List<Etf> getOwned();

    public List<Etf> getTracked();
    
    public List<Etf> getRelevant();

	public List<Etf> getByAssetClass(String assetClass);
	 
	public List<Etf> getByAssetClassFundType(String assetClass, String fundType);
	 
	public List<Etf> getByAssetClassFundTypeSubType(String assetClass, String fundType, String subType);

	public List<Etf> getByAssetClassFundTypeSubTypeFactor(String assetClass, String fundType, String subType, String factor);
	 
	public List<Etf> getFactors();

    public List<Etf> getFactorsAll();

	public List<Etf> getFactorsByAssetClass(String assetClass);

    public List<Etf> getFactorAndAssetClass(String assetClass, String assetFactor);

}
