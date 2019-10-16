package com.jpw.raptor.lib.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by john on 4/3/17.
 */
public class FinanceProperties
{

    Properties cProperties = null;

    public FinanceProperties()
    {
        String file = new String("/home/finance/runtime/finance.properties");
        InputStream input = null;

        try
        {
            cProperties = new Properties();
            input       = new FileInputStream(file);

            // load a properties file
            cProperties.load(input);

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            cProperties = null;
        }

        if (input != null)
        {
            try
            {
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Properties get()
    {
        return cProperties;
    }

}
