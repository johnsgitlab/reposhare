package com.jpw.raptor.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by john on 4/2/17.
 */
@Data
@NoArgsConstructor
public class QuoteId implements Serializable {

    private String  symbol;
    private Date    date;

}
