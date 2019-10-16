package com.jpw.raptor.analytic;

import com.jpw.raptor.model.Adx;
import com.jpw.raptor.model.AdxSignal;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 6/23/18.
 */
public class AdxSpreadSheet {


    protected Sheet           tab;
    protected CreationHelper  helper;
    protected CellStyle       center;
    protected short           rowNo;
    protected Workbook        wb;

    public AdxSpreadSheet() {

        wb      = new XSSFWorkbook();
        helper  = wb.getCreationHelper();
        tab     = wb.createSheet("Adx");
        center  = wb.createCellStyle();
        center.setAlignment(CellStyle.ALIGN_CENTER);

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

        // +DI column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("+DI"));
        cell.setCellStyle(center);

        // -DI column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("-DI"));
        cell.setCellStyle(center);

        // average dx column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("avg dx"));
        cell.setCellStyle(center);

        // Trend column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Trend"));
        cell.setCellStyle(center);

        // Direction column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Direction"));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // update for next row entry
        rowNo++;
    }



    public void addRow(Adx rec, AdxSignal signal ) {
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
        cell.setCellValue(helper.createRichTextString(rec.getSymbol()));
        cell.setCellStyle(center);

        // date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fromatedDate = formatter.format(rec.getDate());
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(fromatedDate));
        cell.setCellStyle(center);

        // Close
        fmt  = Double.valueOf(df.format(rec.getClose()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // +DI column header
        fmt  = Double.valueOf(df.format(rec.getDiPlus()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // -DI column header
        fmt  = Double.valueOf(df.format(rec.getDiMinus()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // average dx column header
        fmt  = Double.valueOf(df.format(rec.getAverageDx()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // Trend
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(signal.getTrend()));
        cell.setCellStyle(center);

        // Direction
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(signal.getDirection()));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // Increment for next row
        rowNo++;
    }

}
