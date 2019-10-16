package com.jpw.raptor.cmdline.web.controller;

import com.jpw.raptor.jdbc.event.EventDAO;
import com.jpw.raptor.model.Event;
import com.jpw.raptor.model.FinanceKnowledge;
import com.jpw.raptor.search.finance.ElasticSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 5/11/18.
 */
@Controller
public class SearchRequestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    EventDAO eventTbl;

    @Autowired
    ElasticSearch fd;


    @RequestMapping("/event-req")
    public String eventReq(
            @RequestParam(value="start", required=true) String start,
            @RequestParam(value="end", required=true) String end,
            Model model) {

        Date startDate  = null;
        Date endDate    = null;

        // validate date
        try {
            // Convert string to date object
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            startDate = format.parse(start);
            endDate   = format.parse(end);
        }
        catch(Exception e) {
            System.out.println("Invalid event date " + start + " " + end);
            return "error";
        }

        java.sql.Date startSqlDate = new java.sql.Date(startDate.getTime());
        java.sql.Date endSqlDate   = new java.sql.Date(endDate.getTime());

        List<Event> recs = eventTbl.getByDate(startDate, endDate);

        model.addAttribute("event_model", recs);
        model.addAttribute("pagetitle", "Events from " + start + " to " + end);

        System.out.println( "***********");
        System.out.println( start);
        System.out.println( end);
        System.out.println( "Records fount " + recs.size());
        System.out.println( "***********");
        return "event-list";
    }


    @RequestMapping("/all-document-req")
    public String AllDocumentReq(Model model) {

        System.out.println( "***********");
        System.out.println( "alldocumentreq");
        System.out.println( "***********");
        model.addAttribute("pagetitle", "All Documents");

        try {
            List<FinanceKnowledge> docs = fd.getAll();
            model.addAttribute("doc_list", docs);

        } catch (IOException ex) {
            model.addAttribute("doc_list", null);
        }

        return "document-list";
    }


    @RequestMapping("/document-req")
    public String documentReq(
            @RequestParam(value="type", required=true) String type,
            @RequestParam(value="text", required=true) String text,
            Model model) {

        String textClean     = null;

        try {
            textClean  = URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException ex) {};

        System.out.println( "***********");
        System.out.println( "documentreq " + type + " " + text);
        System.out.println( "***********");


        model.addAttribute("pagetitle", "Search for: " + text);

        List<FinanceKnowledge> docs = null;

        try {
            if ( type.equalsIgnoreCase("phrase") ) {
                docs = fd.phraseQuery(text);
            } else if ( type.equalsIgnoreCase("allwords") ) {
                docs = fd.allWordsQuery(text);
            } else if ( type.equalsIgnoreCase("anyword") ) {
                docs = fd.wordsQuery(text);
            } else {

            }

            model.addAttribute("doc_list", docs);
        } catch (IOException ex) {
            model.addAttribute("doc_list", null);
        }

        return "document-list";
    }


    @RequestMapping("/doc-detail-req")
    public String knowledgeDoc(
            @RequestParam(value="id", required=true) String id,
            Model model) {

        System.out.println( "***********");
        System.out.println( "docdetailreq " + id);
        System.out.println( "***********");

        try {
            FinanceKnowledge rec = fd.getDocument(id);
            rec.setBody(rec.getBody().replaceAll("\n","<br/>"));
            model.addAttribute("knowdoc", rec);

        } catch (IOException ex) {
            model.addAttribute("knowdoc", null);
        }

        return "doc-detail";
    }
}
