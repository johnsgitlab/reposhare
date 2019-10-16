package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import org.apache.commons.text.WordUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by john on 11/4/18.
 */
public class StockCreator {


    public void doit(StockDAO stockTbl) {

        String fileName = "/home/finance/stocks/stocks.csv";

        BufferedReader br      = null;

        try
        {
            String val = null;

            br  = new BufferedReader(new FileReader(fileName));

            // Read each record in the file
            String cvsSplitBy = ",";
            while ((val = br.readLine()) != null)
            {
                String[] line = val.split(cvsSplitBy);

                String symbol   = line[0].trim();
                String sp       = line[1].trim();
                String russell  = line[2].trim();
                String dow      = line[3].trim();
                String name     = line[4].trim();
                System.out.println(symbol + " | " + sp + " | " + russell + " | " + dow + " | " + name );
                stockTbl.addEmpty(symbol, name, sp, dow, russell);
            }
            br.close();
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
