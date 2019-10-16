package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.hiyieldspread.HiYieldSpreadDAO;
import com.jpw.raptor.model.FileQualified;
import com.jpw.raptor.model.HiYieldSpread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HiYieldTest {


    public void processFile(HiYieldSpreadDAO tbl) {

        FileQualified fileQualified = new FileQualified("", "/home/repogit/testfiles/hiyield1.csv");
        //FileQualified fileQualified = new FileQualified("", "/home/repogit/testfiles/hiyield2.csv");

        // list to hold records which will be added to the database
        List<HiYieldSpread> hiYieldList = new ArrayList<>();

        // Used to read Eod data file
        BufferedReader br = null;
        String         line;

        // Get last date processed
        HiYieldSpread lastProcessed = tbl.getLast();

        try
        {
            br  = new BufferedReader(new FileReader(fileQualified.getName()));

            // Ignore the firs line
            line = br.readLine();

            // Read each record in the file
            while ((line = br.readLine()) != null)
            {
                // Parse the string into an array of fields
                String[] str = line.split(",");

                // Create an object
                HiYieldSpread rec = new HiYieldSpread (str);

                // parse data entry
                if ( rec != null && rec.getSpread() == -9999.0 ) {
                    // do not process invalid data
                } else {

                    // Only new dates
                    if (rec.getDate().after(lastProcessed.getDate()) ){
                        hiYieldList.add(rec);
                    } else {
                        System.out.println("date not processed " + rec.getDate() + "  " + lastProcessed.getDate());
                    }
                }
            }

            // update the database with new entries
            for (HiYieldSpread rec : hiYieldList) {
                tbl.upsert(rec);
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
                if ( br != null ) {
                    br.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
