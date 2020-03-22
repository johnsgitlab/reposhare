package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

/**
 * Created by john on 5/13/18.
 */
@Data
@Entity
@Table(name = "category_tbl")
public class Category {

    @Id
    @Column(name = "category", columnDefinition="character varying(126) NOT NULL")
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
