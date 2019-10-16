package com.jpw.raptor.cmdline.split;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 3/30/18.
 */
public class SplitValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {

        double aDouble;

        try {
            aDouble = Double.parseDouble(value);

            if ( aDouble > 0.0 ) {
                // valid number entered
            } else {
                throw new ParameterException("Split must be poitive number");
            }

        } catch ( java.lang.NumberFormatException ex ) {
            throw new ParameterException("Split must be a number");
        }

    }
}
