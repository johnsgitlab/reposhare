package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import org.apache.commons.lang3.StringUtils;


import javax.persistence.*;
import javax.persistence.Index;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by john on 12/6/18.
 */
@Data
@Entity
@Table(
        name = "treasury_tbl",
        indexes = {@Index(name = "idx_treasury_date", columnList = "date_tx")}
)
public class Treasury {

    @Id
    @Column(name = "date_tx", unique=true, nullable = false,  columnDefinition="")
    protected Date   date;

    @Column(name = "one_month", columnDefinition="")
    protected double oneMonth;

    @Column(name = "two_months", columnDefinition="")
    protected double twoMonths;

    @Column(name = "three_months", columnDefinition="")
    protected double threeMonths;

    @Column(name = "six_months", columnDefinition="")
    protected double sixMonths;

    @Column(name = "one_year", columnDefinition="")
    protected double oneYear;

    @Column(name = "two_years", columnDefinition="")
    protected double twoYears;

    @Column(name = "three_years", columnDefinition="")
    protected double threeYears;

    @Column(name = "five_years", columnDefinition="")
    protected double fiveYears;

    @Column(name = "seven_years", columnDefinition="")
    protected double sevenYears;

    @Column(name = "ten_years", columnDefinition="")
    protected double tenYears;

    @Column(name = "twenty_years", columnDefinition="")
    protected double twentyYears;

    @Column(name = "thirty_years", columnDefinition="")
    protected double thirtyYears;

    // Constructor
    public Treasury () {
        init();
    }

    // Constructor
    public Treasury(String[] a) {
        init();
        parseArray(a);
    }

    protected void init() {
        date        = new Date(-1);
        oneMonth    = 0.0;
        twoMonths   = 0.0;
        threeMonths = 0.0;
        sixMonths   = 0.0;
        oneYear     = 0.0;
        twoYears    = 0.0;
        threeYears  = 0.0;
        fiveYears   = 0.0;
        sevenYears  = 0.0;
        tenYears    = 0.0;
        twentyYears = 0.0;
        thirtyYears = 0.0;
    }

    protected void parseArray(String[] a) {

        int dateIdx        = 0;
        int oneMonthIdx    = 1;
        int twoMonthIdx    = 2;
        int threeMonthIdx  = 3;
        int sixMonthIdx    = 4;
        int oneYearIdx     = 5;
        int twoYearIdx     = 6;
        int threeYearIdx   = 7;
        int fiveYearIdx    = 8;
        int sevenYearIdx   = 9;
        int tenYearIdx     = 10;
        int twentyYearIdx  = 11;
        int thirtyYearIdx  = 12;

        if ( a == null || a.length < 13 )
            return;

        if ( !StringUtils.isEmpty(a[dateIdx]) ) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse(a[dateIdx]);
            } catch (ParseException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[oneMonthIdx]) ) {
            try {
                oneMonth = Double.parseDouble(a[oneMonthIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[twoMonthIdx]) ) {
            try {
                twoMonths = Double.parseDouble(a[twoMonthIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[threeMonthIdx]) ) {
            try {
                threeMonths = Double.parseDouble(a[threeMonthIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[sixMonthIdx]) ) {
            try {
                sixMonths = Double.parseDouble(a[sixMonthIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[oneYearIdx]) ) {
            try {
                oneYear = Double.parseDouble(a[oneYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[twoYearIdx]) ) {
            try {
                twoYears = Double.parseDouble(a[twoYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[threeYearIdx]) ) {
            try {
                threeYears = Double.parseDouble(a[threeYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[fiveYearIdx]) ) {
            try {
                fiveYears = Double.parseDouble(a[fiveYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[sevenYearIdx]) ) {
            try {
                sevenYears = Double.parseDouble(a[sevenYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[tenYearIdx]) ) {
            try {
                tenYears = Double.parseDouble(a[tenYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[twentyYearIdx]) ) {
            try {
                twentyYears = Double.parseDouble(a[twentyYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[thirtyYearIdx]) ) {
            try {
                thirtyYears = Double.parseDouble(a[thirtyYearIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }
    }

}
