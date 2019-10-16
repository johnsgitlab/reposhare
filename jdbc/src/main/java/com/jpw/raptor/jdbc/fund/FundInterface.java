package com.jpw.raptor.jdbc.fund;

import com.jpw.raptor.model.Fund;
import com.jpw.raptor.model.ListModel;

import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface FundInterface {

    public int sqlScript(String script);

    public int addEmpty(String symbol);

    public int update(Fund rec);
    
    public int delete(String symbol);

    public boolean validFund(String symbol);

    public Fund get(String symbol);

    public List<Fund> getAll();

    public List<Fund> getOwned();

    public List<Fund> getTracked();
    
    public List<Fund> getRelevant();

	public List<Fund> getByAssetClass(String assetClass);
	 
	public List<Fund> getByAssetClassFundType(String assetClass, String fundType);
	 
	public List<Fund> getByAssetClassFundTypeSubType(String assetClass, String fundType, String subType);
	 
	public List<Fund> getByAssetClassFundTypeSubTypeFactor(String assetClass, String fundType, String subType, String factor);
	 
	public List<Fund> getFactors();

    public List<Fund> getFactorsAll();

	public List<Fund> getFactorsByAssetClass(String assetClass);

}
