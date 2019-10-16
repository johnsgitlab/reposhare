package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DualMomentumService {

    static final int ONE_MONTH_IDX      = 21;   // 21 days
    static final int TWO_MONTHS_IDX     = 42;   // 42
    static final int THREE_MONTHS_IDX   = 63;   // 63
    static final int SIX_MONTHS_IDX     = 126;  // 126 days
    static final int ONE_YEAR_IDX       = 253;  // 253 days


    public List<DualMomentumModel> getSymbolsFromFile(String filePath) {

        //
        // Read symbols and names from file
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
        }

        //
        // Process each line read from the file
        List<DualMomentumModel> list = new ArrayList<>();
        for ( String line : lines ) {
            String[] elements = line.split(",");
            if ( elements.length >= 2 ) {
                // Valid number of columns
                DualMomentumModel entity = new DualMomentumModel();
                entity.setSymbol(elements[0].trim().toUpperCase());
                entity.setName(elements[1].trim());
                list.add(entity);
            }
        }

        return list;
    }


    public void  computeRelativeReturn(DualMomentumModel equity, DualMomentumModel ref) {

        DecimalFormat   df    = new DecimalFormat("#.##");

        equity.setOneMonth( Double.parseDouble(df.format( equity.getOneMonth() - ref.getOneMonth())) );
        equity.setTwoMonths( Double.parseDouble(df.format( equity.getTwoMonths() - ref.getTwoMonths())) );
        equity.setThreeMonths( Double.parseDouble(df.format( equity.getThreeMonths() - ref.getThreeMonths())) );
        equity.setSixMonths( Double.parseDouble(df.format( equity.getSixMonths() - ref.getSixMonths())) );
        equity.setOneYear( Double.parseDouble(df.format( equity.getOneYear() - ref.getOneYear())) );
    }


    public void  computeAbsoluteReturn(DualMomentumModel model, List<Quote> quotes) {

        double          val;
        double          first = quotes.get(0).getClose();
        DecimalFormat   df    = new DecimalFormat("#.##");

        val = (first - quotes.get(ONE_MONTH_IDX).getClose()) / quotes.get(ONE_MONTH_IDX).getClose();
        model.setOneMonth( Double.parseDouble(df.format(val*100.0)) );

        val = (first - quotes.get(TWO_MONTHS_IDX).getClose()) / quotes.get(TWO_MONTHS_IDX).getClose();
        model.setTwoMonths( Double.parseDouble(df.format(val*100.0)) );

        val = (first - quotes.get(THREE_MONTHS_IDX).getClose()) / quotes.get(THREE_MONTHS_IDX).getClose();
        model.setThreeMonths( Double.parseDouble(df.format(val*100.0)) );

        val = (first - quotes.get(SIX_MONTHS_IDX).getClose()) / quotes.get(SIX_MONTHS_IDX).getClose();
        model.setSixMonths( Double.parseDouble(df.format(val*100.0)) );

        val = (first - quotes.get(ONE_YEAR_IDX).getClose()) / quotes.get(ONE_YEAR_IDX).getClose();
        model.setOneYear( Double.parseDouble(df.format(val*100.0)) );
    }

}
