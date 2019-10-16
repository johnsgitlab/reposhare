package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.algorithm.RelativePerformance;
import com.jpw.raptor.algorithm.SimpleMovingAverage;
import com.jpw.raptor.model.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsburyRiskService {


    public List<AsburyListModel> getHiYieldSpreadList(List<HiYieldSpread> recs, int days) {

        SimpleMovingAverage         sma     = new SimpleMovingAverage();
        SimpleDateFormat            sdf     = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<AsburyListModel>  result  = new ArrayList<>(recs.size());

        // create the list
        for ( HiYieldSpread rec : recs ) {
            AsburyListModel alm = new AsburyListModel();
            alm.setSymbol("Spread");
            alm.setDate(rec.getDate());
            alm.setDatestr( sdf.format(rec.getDate()) );
            alm.setVal(rec.getSpread());
            result.add(alm);
        }

        // populate the moving average
        for ( int i=0; i<result.size(); i++ ) {
            result.get(i).setSma( sma.asburyAvg(result,i,days) );
        }

        return result;
    }


    public List<AsburyListModel> getQuoteList(String symbol, List<Quote> recs, int days) {

        SimpleMovingAverage         sma     = new SimpleMovingAverage();
        SimpleDateFormat            sdf     = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<AsburyListModel>  result  = new ArrayList<>(recs.size());

        // create the list
        for ( Quote rec : recs ) {
            AsburyListModel alm = new AsburyListModel();
            alm.setSymbol(symbol);
            alm.setDate(rec.getDate());
            alm.setDatestr( sdf.format(rec.getDate()) );
            alm.setVal(rec.getClose());
            result.add(alm);
        }

        // populate the moving average
        for ( int i=0; i<result.size(); i++ ) {
            result.get(i).setSma( sma.asburyAvg(result,i,days) );
        }

        return result;
    }

    public List<AsburyListModel> getRelativePerformanceList(List<Quote> spy, List<Quote> jnk, int days) {

        SimpleMovingAverage         sma     = new SimpleMovingAverage();
        SimpleDateFormat            sdf     = new SimpleDateFormat("yyyy-MM-dd");

        // Get the relative performance list
        RelativePerformance         rp      = new RelativePerformance();
        List<Rp>                    rpList  = rp.computeRelativePerformance(jnk, spy);

        // convert relative performace to asbury list model
        ArrayList<AsburyListModel>  result  = new ArrayList<>(rpList.size());

        for ( Rp rec : rpList ) {
            AsburyListModel alm = new AsburyListModel();
            alm.setSymbol(rec.getSymbol());
            alm.setDate(rec.getDate());
            alm.setDatestr( sdf.format(rec.getDate()) );
            alm.setVal(rec.getVal());
            result.add(alm);
        }

        // populate the moving average
        for ( int i=0; i<result.size(); i++ ) {
            result.get(i).setSma( sma.asburyAvg(result,i,days) );
        }

        return result;
    }


}
