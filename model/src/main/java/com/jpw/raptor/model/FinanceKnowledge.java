package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jpw.raptor.model.RaptorConstants;

/**
 * Created by john on 5/4/18.
 */

public class FinanceKnowledge {

    private String          id;
    private String          md5;
    private double          score;
    private String          title;
    private Date            date;
    private String          body;
    private List<String>    tag;
    private String          url;
    private String          loc;

    // constructor
    public FinanceKnowledge() {
        id    = null;
        md5   = null;
        score = 0.0;
        title = null;
        date  = null;
        url   = null;
        loc   = null;
        tag   = null;
        body  = null;
    }

    public FinanceKnowledge(Map<String, Object> sourceAsMap) {
        id    = null;
        md5   = null;
        score = 0.0;

        // Title
        title = (String) sourceAsMap.get(RaptorConstants.FIELD_TITLE);

        // URL to retrieve HTML view
        url = (String) sourceAsMap.get(RaptorConstants.FIELD_URL);

        // Filesystem location to retrieve document
        loc = (String) sourceAsMap.get(RaptorConstants.FIELD_LOC);

        // Date
        date = null;

        // Tags
        List<Object> textLines = (List<Object>) sourceAsMap.get(RaptorConstants.FIELD_TAG);
        if ( textLines != null ) {
            tag = new ArrayList<String>(textLines.size());
            for (Object line : textLines) {
                tag.add((String) line);
            }
        }

        // Body
        body = (String) sourceAsMap.get(RaptorConstants.FIELD_BODY);
    }

    public void setId(String v)         {id = v;}
    public void setMd5(String v)        {md5 = v;}
    public void setScore(double v) {
        DecimalFormat df = new DecimalFormat("#.###");
        score = Double.valueOf(df.format(v));
    }
    public void setTitle(String v)      {title = v;}
    public void setUrl(String v)        {url = v;}
    public void setLoc(String v)        {loc = v;}
    public void setDate(Date v)         {date = v;}
    public void setTag(List<String> v)  {tag = v;}
    public void setBody(String v)       {body = v;}

    public String getId()               {return id;}
    public String getMd5()              {return md5;}
    public double getScore()            {return score;}
    public String getTitle()            {return title;}
    public String getUrl()              {return url;}
    public String getLoc()              {return loc;}
    public Date getDate()               {return date;}
    public List<String> getTag()        {return tag;}
    public String getBody()             {return body;}

}
