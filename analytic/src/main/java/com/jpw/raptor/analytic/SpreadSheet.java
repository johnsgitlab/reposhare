package com.jpw.raptor.analytic;

import com.jpw.raptor.model.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by john on 6/26/18.
 */
public class SpreadSheet {


    protected Sheet           tab;
    protected CreationHelper  helper;
    protected CellStyle       center;
    protected CellStyle       left;
    protected short           rowNo;
    protected Workbook        wb;

    public SpreadSheet() {

        wb      = new XSSFWorkbook();
        helper  = wb.getCreationHelper();
        tab     = wb.createSheet("Adx");

        center  = wb.createCellStyle();
        center.setAlignment(CellStyle.ALIGN_CENTER);

        left    = wb.createCellStyle();
        left.setAlignment(CellStyle.ALIGN_LEFT);


        rowNo   = 0;

        addColumnHeaders();
    }


    public void writeToFile(String dir, String name) throws FileNotFoundException, IOException {

        FileOutputStream fileOut = new FileOutputStream(new String(dir + "/" + name + ".xlsx"));
        wb.write(fileOut);
        fileOut.close();
    }


    protected void addColumnHeaders() {

        // column number in the row
        int col = 0;

        // Cell object
        Cell cell;

        // Create row for the column headers
        Row row = tab.createRow(rowNo);

        // Symbol column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Symbol"));
        cell.setCellStyle(center);

        // Date column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Date"));
        cell.setCellStyle(center);

        // Close column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Close"));
        cell.setCellStyle(center);

        // Sto Strength
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Sto Strength"));
        cell.setCellStyle(center);

        // Sto trend
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Sto Trend"));
        cell.setCellStyle(center);

        // Sto Days
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Sto Days"));
        cell.setCellStyle(center);

        // Sto Divergence
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Sto Div"));
        cell.setCellStyle(center);

        // Bollinger Width
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Boll Upr"));
        cell.setCellStyle(center);

        // Bollinger Width
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Boll Mid"));
        cell.setCellStyle(center);

        // Bollinger Width
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Boll low"));
        cell.setCellStyle(center);

        // Bollinger Width
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Boll Wid"));
        cell.setCellStyle(center);

        // Bollinger Percent B
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Boll %b"));
        cell.setCellStyle(center);

        // Bollinger Squeeze
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Boll Sqz"));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // ATR Signal
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("ATR Signal"));
        cell.setCellStyle(center);

        // average true range header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("ATR"));
        cell.setCellStyle(center);

        // Trend column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Trend"));
        cell.setCellStyle(center);

        // Direction column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Trend Dir"));
        cell.setCellStyle(center);

        // Direction column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Trend Days"));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // SMA column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Moving Avg"));
        cell.setCellStyle(left);

        // update for next row entry
        rowNo++;
    }



    public void addRow(Adx adx, AdxSignal adxSignal, StochasticSignal stoSignal,
                       BollingerSignal bollSignal, String smaSignal, TradeSignal tradeSignal) {

        // column number in the row
        int col = 0;

        // Cell object
        Cell cell;

        // format percentage change
        DecimalFormat df = new DecimalFormat("#.##");

        double val = 0.0;
        double fmt = 0.0;
        String str;

        // Create row
        Row row = tab.createRow(rowNo);

        // symbol
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(adx.getSymbol()));
        cell.setCellStyle(center);

        // date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fromatedDate = formatter.format(adx.getDate());
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(fromatedDate));
        cell.setCellStyle(center);

        // Close
        fmt  = Double.valueOf(df.format(adx.getClose()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // Sto Strength
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(stoSignal.getStrength()));
        cell.setCellStyle(center);

        // Sto Trend
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(stoSignal.getTrend()));
        cell.setCellStyle(center);

        // Sto Days
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(stoSignal.getDays()));
        cell.setCellStyle(center);

        // Sto Divergence
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(stoSignal.getDivergence()));
        cell.setCellStyle(center);

        // Bollinger Upper
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(bollSignal.getUpper()));
        cell.setCellStyle(center);

        // Bollinger Middle
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(bollSignal.getMiddle()));
        cell.setCellStyle(center);

        // Bollinger Lower
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(bollSignal.getLower()));
        cell.setCellStyle(center);

        // Bollinger Width
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(bollSignal.getWidth()));
        cell.setCellStyle(center);

        // Bollinger %b
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(bollSignal.getPercentB()));
        cell.setCellStyle(center);

        // Bollinger Squeeze
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(bollSignal.getSqueeze()));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // ATR Signal
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(adxSignal.getSignal()));
        cell.setCellStyle(center);

        // ATR
        fmt  = Double.valueOf(df.format(adx.getAverageTrueRange()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // Trend
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(adxSignal.getTrend()));
        cell.setCellStyle(center);

        // Direction
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(adxSignal.getDirection()));
        cell.setCellStyle(center);

        // Days
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(adxSignal.getDays()));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // Days
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(smaSignal));
        cell.setCellStyle(left);

        // Increment for next row
        rowNo++;
    }

}
