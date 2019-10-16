package com.jpw.raptor.jdbc.hiyieldspread;

import com.jpw.raptor.model.HiYieldSpread;

import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/1/2017.
 */
public interface HiYieldSpreadInterface {

    public int sqlScript(String script);

    public void delete(Date date);

    public void upsert(HiYieldSpread rec);

    public HiYieldSpread get(Date date);

    public HiYieldSpread getLast();

    public List<HiYieldSpread> getAll();

    public List<HiYieldSpread> getAllDesc();

}
