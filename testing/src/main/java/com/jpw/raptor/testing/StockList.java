package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.Stock;
import org.apache.commons.text.WordUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by john on 11/16/18.
 */
public class StockList {

    public void doit(StockDAO tbl) {

        String file1 = "/home/john/stocks.txt";

        BufferedReader br      = null;

        try
        {
            String val = null;

            br  = new BufferedReader(new FileReader(file1));

            // Read each record in the file
            while ((val = br.readLine()) != null)
            {
                String symbol  = val.trim().toUpperCase();

                Stock s = tbl.get(symbol);

                if ( s != null ) {
                    System.out.println(s.getSymbol() + " " + s.getName() + " " + s.getSpIndex() + " " +
                            s.getDowIndex() + " " + s.getRussellIndex());
                } else {
                    System.out.println(symbol + " NOT FOUND " );
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

    }

}
