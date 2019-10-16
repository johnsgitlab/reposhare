package com.jpw.raptor.cmdline.ingest;

import com.beust.jcommander.Parameter;

/**
 * Created by john on 5/3/18.
 */
public class AppParameters {

    @Parameter(names = "-knowledge",
            description = "Knowledge file ingest",
            required = false)
    private String knowledge;

    public String getKnowledge() {
        return knowledge;
    }
}
