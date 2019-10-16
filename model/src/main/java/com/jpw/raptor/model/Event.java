package com.jpw.raptor.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 5/13/18.
 */
@Data
public class Event {

    private long       rowId;
    private Date       dateTx;
    private String     category;
    private String     subCategory;
    private String     effect;
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
