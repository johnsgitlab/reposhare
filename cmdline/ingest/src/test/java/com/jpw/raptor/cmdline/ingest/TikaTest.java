package com.jpw.raptor.cmdline.ingest;


import com.jpw.raptor.model.FinanceKnowledge;
import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by john on 5/22/18.
 */
public class TikaTest {


    @Test
    public void testit() throws IOException, TikaException {

        ClassLoader     classLoader = getClass().getClassLoader();

        FileInputStream textFile = new FileInputStream(
                new File(classLoader.getResource("temp.txt").getFile()));

        FileInputStream odtFile  = new FileInputStream(
                new File(classLoader.getResource("smart_beta.odt").getFile()));

        FileInputStream docFile  = new FileInputStream(
                new File(classLoader.getResource("smart_beta.docx").getFile()));

        Tika        tika        = new Tika();
        Metadata    metadata    = new Metadata();

        //System.out.println(tika.detect(textFile));  //text/plain
        //System.out.println(tika.detect(odtFile));   //application/zip
        //System.out.println(tika.detect(docFile));   //application/x-tika-ooxml

        String content = tika.parseToString(odtFile);
        //System.out.println(content);

        // Split the string
        //String          eol         = System.getProperty("line.separator");
        //String[] result = content.split(eol);
        //System.out.println("lines found " + result.length);
        //for ( String line : result ) {
        //    System.out.println(line);
        //}
        // Trim each tag in the array
        //Arrays.parallelSetAll(result, (i) -> result[i].trim());

        //tika.parse(odtFile, metadata);
        //System.out.println(metadata.toString());


/*
        //Get file from resources folder
        ClassLoader         classLoader = getClass().getClassLoader();
        KnowledgeFile       kf  = new KnowledgeFile();
        FinanceKnowledge    k   = kf.processFile(classLoader.getResource("knowledge.txt").getFile());
        String              eol = System.getProperty("line.separator");

        assertTrue(k.getTitle().equalsIgnoreCase("document title"));

        assertTrue(k.getTag().get(0).equalsIgnoreCase("Earnings Per Share"));
        assertTrue(k.getTag().get(1).equalsIgnoreCase("EPS"));

        StringBuilder sb = new StringBuilder();
        sb.append("Primary earnings per share calculated as: ").append(eol);
        sb.append("EPS = (").append(eol);
        sb.append("").append(eol);
        sb.append("When the capital structure of a company").append(eol);
        assertTrue(k.getBody().equalsIgnoreCase(sb.toString()));
*/
    }

}
