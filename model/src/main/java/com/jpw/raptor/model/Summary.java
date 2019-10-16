package com.jpw.raptor.model;

import java.util.Date;

/**
 * Created by john on 4/3/17.
 */
public class Summary extends EtfFundBase {

    private String     name;
    private String     equity;

    private int        count;
    
    protected void init() {

        // Provide default values
        name = null;
        equity = null;
        count = 0;
    }

    // Constructor
    public Summary () {
        super();
        init();
    }

    
    public String   getName()                          {return name;}
    public void     setName(String v)                  {name = v;}
    
    public String   getEquity()                        {return equity;}
    public void     setEquity(String v)                {equity = v;}

    public int      getCount()                         {return count;}
    public void     setCount(int v)                    {count = v;}
}