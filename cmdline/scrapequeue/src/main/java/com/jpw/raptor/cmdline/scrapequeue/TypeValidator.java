package com.jpw.raptor.cmdline.scrapequeue;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.text.ParseException;
/**
 * Created by John on 10/25/2017.
 */
public class TypeValidator implements IParameterValidator {

    public void validate(String name, String value) throws ParameterException {

        if ( value.equalsIgnoreCase("etf") || value.equalsIgnoreCase("fund") || value.equalsIgnoreCase("stock")) {
        } else {
            throw new ParameterException("Type must be STOCK, ETF or FUND");
        }
    }

}
