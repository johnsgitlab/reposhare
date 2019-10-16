package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.model.Quote;
import org.apache.commons.text.WordUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by john on 10/22/18.
 */
public class Worker {

    public HashMap<String, Entry> symbols;

    public void updateEntry(Entry entry, String type) {
        if ( type.equalsIgnoreCase("1000") || type.equalsIgnoreCase("2000") ) {
            entry.setRussell(type);
        } else {
            entry.setSp(type);
        }
    }

    public void processFile(String fileName, String type) {
        BufferedReader br      = null;

        try
        {
            String val = null;

            br  = new BufferedReader(new FileReader(fileName));

            // Read each record in the file
            while ((val = br.readLine()) != null)
            {
                int firstComma = val.indexOf(',');
                String symbol  = val.substring(0, firstComma);
                symbol = symbol.toUpperCase().trim();

                String name= val.substring(firstComma+1);
                name = name.replace(",","");
                name = name.replace("\"","");
                name = name.replace("'","");
                name = name.toLowerCase();
                name = WordUtils.capitalizeFully(name);
                name = name.trim();

                Entry entry;
                if ( symbols.containsKey(symbol)) {
                    entry = symbols.get(symbol);
                } else {
                    entry = new Entry();
                    entry.setSymbol(symbol);
                    entry.setName(name);
                    entry.setRussell("");
                    entry.setSp("");
                    symbols.put(symbol, entry);
                }

                updateEntry(entry, type);
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


    public void doit(IndexDAO indexTbl, FundDAO fundTbl, EtfDAO etfTbl) {

        symbols = new HashMap<>();

        String file1 = "/home/finance/stocks/sp400.csv";
        String file2 = "/home/finance/stocks/sp500.csv";
        String file3 = "/home/finance/stocks/sp600.csv";
        String file4 = "/home/finance/stocks/russell-1000.csv";
        String file5 = "/home/finance/stocks/russell-2000.csv";

        processFile(file1, "400");
        processFile(file2, "500");
        processFile(file3, "600");
        processFile(file4, "1000");
        processFile(file5, "2000");


        String fileName = "/home/finance/stocks/stocks.csv";

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {

            // TreeMap to store values of HashMap
            TreeMap<String, Entry> sorted = new TreeMap<>();

            // Copy all data from hashMap into TreeMap
            sorted.putAll(symbols);

            for (Entry entry : sorted.values()){
                //iterate over values
                writer.write(entry.getSymbol() + "," + entry.getRussell() + "," + entry.getSp() + "," + entry.getName() + "\n");

                if (indexTbl.get(entry.getSymbol()) != null ) {
                    System.out.println(entry.getSymbol() + " ******************** Index table clash ");
                }

                if (fundTbl.get(entry.getSymbol()) != null ) {
                    System.out.println(entry.getSymbol() + " ******************** Fund table clash ");
                }

                if (etfTbl.get(entry.getSymbol()) != null ) {
                    System.out.println(entry.getSymbol() + " ******************** Etf table clash ");
                }
            }

            writer.close();
        } catch (IOException  e)
        {
            e.printStackTrace();
        }

    }


}
