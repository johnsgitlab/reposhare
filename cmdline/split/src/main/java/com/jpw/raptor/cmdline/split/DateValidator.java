package com.jpw.raptor.cmdline.split;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Created by john on 3/30/18.
 */
public class DateValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException{

        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(value);
        } catch (ParseException e) {
            throw new ParameterException("Date format YYYY-MM-DD");
        }
    }
}
