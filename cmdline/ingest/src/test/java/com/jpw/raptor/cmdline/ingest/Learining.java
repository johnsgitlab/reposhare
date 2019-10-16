package com.jpw.raptor.cmdline.ingest;

import java.io.File;

import com.jpw.raptor.model.FinanceKnowledge;
import com.jpw.raptor.lib.properties.FinanceProperties;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by john on 5/23/18.
 */
public class Learining {

    @Test
    public void testit() throws IOException {


        // import directory
        FinanceProperties fp   = new FinanceProperties();
        Properties prop = fp.get();

        String dir  = prop.getProperty("knowledge_dir");
        if ( dir == null )
        {
            System.out.println("Import directory not specified");
            return;
        } else {
            System.out.println(dir);
        }

        // compute the current year
        SimpleDateFormat formatter    = new SimpleDateFormat("yyyy");
        String           year         = formatter.format(new Date());

        KnowledgeFileUtil kfu = new KnowledgeFileUtil();
        List<File> files = kfu.listFilesInDirAndSubDir();

        for (File file : files) {
            String fromHtml;
            String toHtml;
            String fromDoc;
            String toDoc;
            String loc;
            String altTitle;

            fromDoc = file.getCanonicalPath();
            toDoc   = dir + "/doc/" + year + "/" + file.getName();

            fromHtml = file.getCanonicalPath().substring(0, file.getCanonicalPath().lastIndexOf('.') ) + ".html";
            toHtml   = dir + "/html/" + year + "/" + file.getName().substring(0, file.getName().lastIndexOf('.') ) + ".html";
            loc      = "/html/" + year + "/" + file.getName().substring(0, file.getName().lastIndexOf('.') ) + ".html";
            altTitle = file.getName().substring(0, file.getName().lastIndexOf('.') );
            altTitle = altTitle.replaceAll("-", " ");
            altTitle = altTitle.replaceAll("_", " ");

            System.out.println("from: " + fromHtml);
            System.out.println("to  : " + toHtml);
            System.out.println("loc : " + loc);
            System.out.println("alt : " + altTitle);
            System.out.println();

            System.out.println("from: " + fromDoc);
            System.out.println("to  : " + toDoc);

            System.out.println();
            System.out.println("CanonicalPath  : " + file.getCanonicalPath());
            System.out.println("Name           : " + file.getName());
            System.out.println("AbsolutePath   : " + file.getAbsolutePath());
            System.out.println("Parent         : " + file.getParent());
            System.out.println("Path           : " + file.getPath());

            System.out.println("base           : " + file.getCanonicalPath().substring(0,file.getCanonicalPath().lastIndexOf('.')));
            System.out.println("Suffix         : " + file.getName().substring(file.getName().indexOf('.')));

        }

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
