package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

/**
 * Created by john on 4/3/17.
 */
@Data
@Entity
@Table(name = "index_tbl")
public class Index {

    @Id
    @Column(name = "symbol", columnDefinition="")
    private String     symbol;

    @Column(name = "name", columnDefinition="")
    private String     name;

    @Column(name = "date_tx", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date       date;

    @Column(name = "last_update", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date       lastUpdate;

    @Column(name = "overview", columnDefinition="")
    private String     overview;

    @Column(name = "ytd", columnDefinition="")
    private double     ytd;

    @Column(name = "one_day", columnDefinition="")
    private double     oneDay;          // 1

    @Column(name = "one_week", columnDefinition="")
    private double     oneWeek;         // 5

    @Column(name = "two_weeks", columnDefinition="")
    private double     twoWeeks;        // 10

    @Column(name = "four_weeks", columnDefinition="")
    private double     fourWeeks;       // 20

    @Column(name = "three_months", columnDefinition="")
    private double     threeMonths;     // 62

    @Column(name = "one_year", columnDefinition="")
    private double     oneYear;         // 250

    @Column(name = "three_years", columnDefinition="")
    private double     threeYears;      // 750

    protected void init() {
        // Provide default values
        symbol      = null;
        name        = null;

        date        = null;
        lastUpdate  = null;

        ytd         = -9999.0;
        oneDay      = -9999.0;
        oneWeek     = -9999.0;
        twoWeeks    = -9999.0;
        fourWeeks   = -9999.0;
        threeMonths = -9999.0;
        oneYear     = -9999.0;
        threeYears  = -9999.0;

        overview    = null;
    }

    // Constructor
    public Index () {
        init();
    }

}
