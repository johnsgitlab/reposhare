package com.jpw.raptor.model;

/**
 * Created by John on 10/25/2017.
 */
public class EquityToScrape {

    private String  type;
    private String  symbol;

    public EquityToScrape() {
        type    = null;
        symbol  = null;
    }

    public EquityToScrape(String t, String s) {
        type    = t;
        symbol  = s;
    }

    public String   getType()               { return type;}
    public void     setType(String v)       { type = v;}

    public String   getSymbol()             { return symbol;}
    public void     setSymbol(String v)     { symbol = v;}

    public String   toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"");
        sb.append(type);
        sb.append("\",\"symbol\":\"");
        sb.append(symbol);
        sb.append("\"}");
        return sb.toString();
    }
}
