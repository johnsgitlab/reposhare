package com.jpw.raptor.scrape.yahoostock.fields;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 10/21/18.
 */
@Getter
@Setter
public class UpgradeDowngradeObj {

    private String epochGradeDate;
    private String firm;
    private String toGrade;
    private String fromGrade;
    private String action;

}
