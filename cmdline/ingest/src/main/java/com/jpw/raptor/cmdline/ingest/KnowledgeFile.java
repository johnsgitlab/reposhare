package com.jpw.raptor.cmdline.ingest;

import com.jpw.raptor.model.FinanceKnowledge;

import com.jpw.raptor.search.finance.ElasticSearch;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.stereotype.Component;

import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 5/3/18.
 */
@Component
public class KnowledgeFile {

    public String generateTitleFromFile(File file) {
        String title = null;

        title = file.getName().substring(0, file.getName().lastIndexOf('.') );
        title = title.replaceAll("-", " ");
        title = title.replaceAll("_", " ");

        return title;
    }


    public String generateTitleFromLine(String line) {

        if ( line == null ) {
            return null;
        }

        int index = line.indexOf("***title");
        if (index == -1) {
            return null;
        }

        return line.substring(index+8).trim();

    }


    public List<String> generateTagFromLine(String line) {

        if ( line == null ) {
            return null;
        }

        int index = line.indexOf("***tag");
        if (index == -1) {
            return null;
        }

        // Split the string
        String[] result = line.substring(index+6).trim().split(";");

        // Trim each tag in the array
        Arrays.parallelSetAll(result, (i) -> result[i].trim());

        // convert array to list
        return new ArrayList<String>(Arrays.asList(result));
    }


    public String generateMd5(String line) {

        if ( line == null ) {
            return null;
        }

        return DigestUtils.md5Hex(line).toUpperCase();

    }


    public FinanceKnowledge generateFinancialKnowledge(File file) {

        boolean         error       = false;
        String          eol         = System.getProperty("line.separator");
        String          title       = null;
        String          date        = null;
        List<String>    tag         = new ArrayList<String>();
        StringBuilder   sb          = new StringBuilder();

        String          fileSuffix  = file.getName().substring(file.getName().indexOf('.'));

        // compute the current year
        SimpleDateFormat formatter    = new SimpleDateFormat("yyyy");
        String           year         = formatter.format(new Date());

        try
        {
            Tika            tika        = new Tika();
            FileInputStream odtFile     = new FileInputStream(file);
            String          content     = tika.parseToString(odtFile);

            // Split the string
            String[] result = content.split(eol);

            // Read each record in the file
            for ( String val : result )
            {
                // Process each line based upon its contents
                if        ( val.contains("***title") ) {
                    title = generateTitleFromLine(val);
                } else if ( val.contains("***tag") ) {
                    tag = generateTagFromLine(val);
                } else if ( val.contains("***date") ) {
                    // ignore
                } else {
                    // default is body
                    sb.append(val).append(eol);
                }
            }
        }
        catch (IOException | TikaException e)
        {
            error = true;
            e.printStackTrace();
        }

        // if no title was found use file name
        if ( title == null ) {
            title = generateTitleFromFile(file);
        }

        //
        // Create the knowledge object
        FinanceKnowledge knowledge = null;
        if ( !error ) {
            knowledge = new FinanceKnowledge();
            knowledge.setTitle(title);
            knowledge.setTag(tag);
            knowledge.setBody(sb.toString());
            knowledge.setMd5(generateMd5(knowledge.getBody()));
            // TBD date

            // generate file and url locations
            knowledge.setLoc( new String("/doc/" + year + "/" + knowledge.getMd5() + fileSuffix) );
            knowledge.setUrl( new String("/html/" + year + "/" + knowledge.getMd5() + ".html") );
        }

        return knowledge;
    }

    public FinanceKnowledge indexFile(ElasticSearch elasticSearch, File file) {

        //
        // Create the knowledge object
        FinanceKnowledge knowledge = generateFinancialKnowledge(file);
        if ( knowledge != null ) {

            // need to move doc file
            // need to move html file

            try {
                //elasticSearch.openIndex();
                elasticSearch.indexDocument(knowledge);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally
            {
                //elasticSearch.closeIndex();
            }
        }

        return knowledge;
    }

}

