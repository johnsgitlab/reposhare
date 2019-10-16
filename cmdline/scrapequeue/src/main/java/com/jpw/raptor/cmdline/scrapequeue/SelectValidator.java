package com.jpw.raptor.cmdline.scrapequeue;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Created by John on 10/25/2017.
 */
public class SelectValidator implements IParameterValidator {

    public void validate(String name, String value) throws ParameterException {

        if ( value.equalsIgnoreCase("all") || value.equalsIgnoreCase("relevant") ||
                value.equalsIgnoreCase("tracked") || value.equalsIgnoreCase("own") ) {
            // passed validation
        } else {
            throw new ParameterException("Type must be ALL, RELEVANT or TRACKED");
        }
    }
}
