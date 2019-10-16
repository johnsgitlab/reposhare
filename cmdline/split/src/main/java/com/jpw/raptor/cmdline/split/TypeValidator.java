package com.jpw.raptor.cmdline.split;


import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 8/14/18.
 */
public class TypeValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {

        if ( value.equalsIgnoreCase("etf") || value.equalsIgnoreCase("fund") ) {
            // good to go
        } else {
            throw new ParameterException("Type must be etf or fund");
        }

    }

}
