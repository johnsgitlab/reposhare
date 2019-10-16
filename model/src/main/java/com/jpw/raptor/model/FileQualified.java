package com.jpw.raptor.model;

/**
 * Created by john on 4/11/18.
 */
public class FileQualified {

    private String  type;
    private String  name;

    public FileQualified() {
        type    = null;
        name  = null;
    }

    public FileQualified(String t, String s) {
        type    = t;
        name  = s;
    }

    public String   getType()               { return type;}
    public void     setType(String v)       { type = v;}

    public String   getName()             { return name;}
    public void     setName(String v)     { name = v;}

}
