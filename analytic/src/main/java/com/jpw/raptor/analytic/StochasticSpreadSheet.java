package com.jpw.raptor.analytic;

import com.jpw.raptor.model.Stochastic;
import com.jpw.raptor.model.StochasticSignal;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 6/22/18.
 */
public class StochasticSpreadSheet {

    protected Sheet           tab;
    protected CreationHelper  helper;
    protected CellStyle       center;
    protected short           rowNo;
    protected Workbook        wb;

    public StochasticSpreadSheet() {

        wb      = new XSSFWorkbook();
        helper  = wb.getCreationHelper();
        tab     = wb.createSheet("Stochastic");
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


        // KFast column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("KFast"));
        cell.setCellStyle(center);

        // DFast column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("DFast"));
        cell.setCellStyle(center);

        // KSlow column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("KSlow"));
        cell.setCellStyle(center);

        // DSlow column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("DSlow"));
        cell.setCellStyle(center);

        // Strength column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Strength"));
        cell.setCellStyle(center);

        // Crossover column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Trend"));
        cell.setCellStyle(center);

        // Divergence column header
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString("Divergence"));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // update for next row entry
        rowNo++;
    }


    public void addRow(Stochastic rec, StochasticSignal signal ) {
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


        // KFast
        fmt  = Double.valueOf(df.format(rec.getKFast()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // DFast
        fmt  = Double.valueOf(df.format(rec.getDFast()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // KSlow
        fmt  = Double.valueOf(df.format(rec.getKSlow()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // DSlow
        fmt  = Double.valueOf(df.format(rec.getDSlow()));
        cell = row.createCell(col++);
        cell.setCellValue(fmt);
        cell.setCellStyle(center);

        // Strength
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(signal.getStrength()));
        cell.setCellStyle(center);

        // Trend
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(signal.getTrend()));
        cell.setCellStyle(center);

        // Divergence
        cell = row.createCell(col++);
        cell.setCellValue(helper.createRichTextString(signal.getDivergence()));
        cell.setCellStyle(center);

        // Skip a column
        col++;

        // Increment for next row
        rowNo++;
    }

}
