package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/12/2017.
 */
public class BondRating {

    private Bb              bb;
    private Aa              aa;
    private Aaa             aaa;
    private A               a;
    private Other           other;
    private B               b;
    private Bbb             bbb;
    private BelowB          belowB;
    private UsGovernment    usGovernment;

    public Bb getBb() {return bb;}
    public void setBb(Bb bb) {this.bb = bb;}

    public Aa getAa() {return aa;}
    public void setAa(Aa aa) {this.aa = aa;}

    public Aaa getAaa() {return aaa;}
    public void setAaa(Aaa aaa) {this.aaa = aaa;}

    public A getA() {return a;}
    public void setA(A a) {this.a = a;}

    public Other getOther() {return other;}
    public void setOther(Other other) {this.other = other;}

    public B getB() {return b;}
    public void setB(B b) {this.b = b;}

    public Bbb getBbb() {return bbb;}
    public void setBbb(Bbb bbb) {this.bbb = bbb;}

    public BelowB getBelowB() {return belowB;}
    public void setBelowB(BelowB belowB) {this.belowB = belowB;}

    public UsGovernment getUsGovernment() {return usGovernment;}
    public void setUsGovernment(UsGovernment usGovernment) {this.usGovernment = usGovernment;}

}
