package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 5/13/18.
 */
@Data
public class Category {

    private String     category;

    protected void init() {
        // Provide default values
        category    = null;
    }

    // Constructor
    public Category () {
        init();
    }

    public Category (String cat) {
        category    = cat;
    }

}
