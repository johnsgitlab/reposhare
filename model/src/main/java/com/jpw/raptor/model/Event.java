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
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

/**
 * Created by john on 5/13/18.
 */
@Data
@Entity
@Table(
        name = "event_tbl",
        indexes = {@Index(name = "idx_event_date", columnList = "date_tx")}
)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_id", columnDefinition="")
    private long       rowId;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_tx", columnDefinition="")
    private Date       dateTx;

    @Column(name = "category", columnDefinition="")
    private String     category;

    @Column(name = "sub_category", columnDefinition="")
    private String     subCategory;

    @Column(name = "effect", columnDefinition="")
    private String     effect;

    @Column(name = "description", columnDefinition="")
    private String     description;

    protected void init() {
        // Provide default values
        rowId       = 0l;
        dateTx      = null;
        category    = null;
        subCategory = null;
        effect      = null;
        description = null;
    }

    // Constructor
    public Event () {
        init();
    }

    public Event (long row, Date date, String cat, String subCat, String eff, String desc) {
        rowId       = row;
        dateTx      = date;
        category    = cat;
        subCategory = subCat;
        effect      = eff;
        description = desc;
    }

}
