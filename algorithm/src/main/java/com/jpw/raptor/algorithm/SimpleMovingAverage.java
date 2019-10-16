package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 3/2/18.
 */
public class SimpleMovingAverage {

    // Quotes are in descending order by date
    public long smaAdl(List<Adl> data, int offset, int count) {

        long  end         = offset + count;
        long  arraySize   = data.size();
        long  divisor     = (long) count;
        long  result      = 0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (long) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getVal();
        }

        // Compute the moving average
        result = result / divisor;

        // return it
        return result;
    }


    // Data is in descending order by date
    public double asburyAvg(List<AsburyListModel> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (double) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getVal();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }


    // Data is in descending order by date
    public double smaKeltnerAvgTrueRange(List<Keltner> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (double) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getTrueRange();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }


    // Data is in descending order by date
    public double smaDSlow(List<Stochastic> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (double) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getKSlow();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }


    // Data is in descending order by date
    public double smaDfast(List<Stochastic> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (double) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getKFast();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }


    // Quotes are in descending order by date
    public long smaObv(List<Obv> data, int offset, int count) {

        long  end         = offset + count;
        long  arraySize   = data.size();
        long  divisor     = (long) count;
        long  result      = 0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (long) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getVal();
        }

        // Compute the moving average
        result = result / divisor;

        // return it
        return result;
    }


    // Quotes are in descending order by date
    public double smaQuote(List<Quote> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (double) (arraySize - offset);
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            result += data.get(i).getClose();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }

    //
    // THis needs to be ported
    public List<AveragesModel> generateAverages(List<Quote> data) {

        // allocate space for averages object list
        List<AveragesModel> avgList = new ArrayList<>(data.size());

        // create averages for each quote
        for ( int i=0; i<data.size(); i++ ) {

            // Init Averages record
            AveragesModel avg = new AveragesModel();
            avg.setSymbol(data.get(i).getSymbol());
            avg.setDate(data.get(i).getDate());
            avg.setOpen(data.get(i).getOpen());
            avg.setHigh(data.get(i).getHigh());
            avg.setLow(data.get(i).getLow());
            avg.setClose(data.get(i).getClose());
            avg.setVolume(data.get(i).getVolume());

            // Generate Simple moving averages
            avg.setSimple5(simpleMovingAverageDesc(data, i, 5));
            avg.setSimple10(simpleMovingAverageDesc(data, i, 10));
            avg.setSimple20(simpleMovingAverageDesc(data, i, 20));
            avg.setSimple50(simpleMovingAverageDesc(data, i, 50));
            avg.setSimple100(simpleMovingAverageDesc(data, i, 100));
            avg.setSimple200(simpleMovingAverageDesc(data, i, 200));

            avgList.add(avg);
        }

        return avgList;
    }

    /*************** cut line *****************************/


    public double simpleMovingAverageDesc(List<Quote> data, int offset, int count) {

        int     start       = offset;
        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            System.out.println("Invalid moving average request " + count);
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
            divisor = (double) (arraySize - offset);
            //System.out.println("Not enough data for moving average request " + data.get(0).getSymbol() + " " + count + " " + data.size());
            //return result;
        }


        // loop through array and sum the closes
        for ( int i=start; i<end; i++ ) {
            result += data.get(i).getClose();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        //System.out.println("Moving average request " + count + " " + result);

        // return it
        return result;
    }

    public double simpleMovingAverageAsc(List<Quote> data, int offset, int count) {

        int     start       = offset;
        int     end         = offset - (count - 1);
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  result      = 0.0;

        // The moving average includes the current day
        // Note data is in ascending order by date

        // count must be at least 1
        if ( count < 1 ) {
            System.out.println("Invalid moving average request "  + count);
            return result;
        }

        // validate offset
        if ( offset < 0 || offset >= arraySize )
            return result;

        // validate there is enough data to compute the moving average
        if ( end < 0 ) {
            // amount of data is invalid adjust
            divisor = (double) (count + end);
            end = 0;
            //return result;
        }


        // loop through array and sum the closes
        for ( int i=start; i>=end; i-- ) {
            result += data.get(i).getClose();
        }

        // Compute the moving average
        result = result / divisor;

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        //System.out.println("Moving average request " + count + " start " + start + " end " + end + " divisor " + divisor);

        // return it
        return result;
    }


}
