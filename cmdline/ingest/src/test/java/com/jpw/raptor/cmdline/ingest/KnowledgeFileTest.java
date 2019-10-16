package com.jpw.raptor.cmdline.ingest;

import java.io.File;

import com.jpw.raptor.model.FinanceKnowledge;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by john on 5/3/18.
 */
public class KnowledgeFileTest {

    @Test
    public void test01() {

        String line1 = "";
        String line2 = "***title some title";

        KnowledgeFile kf = new KnowledgeFile();
        System.out.println(kf.generateTitleFromLine(line2));


    }


    @Test
    public void test02() {

        String line1 = "";
        String line2 = "***tag  some tag ";
        String line3 = "***tag  tag one ; tag two;tag three ";

        KnowledgeFile kf = new KnowledgeFile();
        List<String> list;

        list = kf.generateTagFromLine(line1);
        assertEquals(null, list);

        list = kf.generateTagFromLine(line2);
        assertEquals(1, list.size());
        assertTrue(list.get(0).equalsIgnoreCase("some tag"));

        list = kf.generateTagFromLine(line3);
        assertEquals(3, list.size());
        assertTrue(list.get(0).equalsIgnoreCase("tag one"));
        assertTrue(list.get(1).equalsIgnoreCase("tag two"));
        assertTrue(list.get(2).equalsIgnoreCase("tag three"));

    }


    @Test
    public void test03() {

        String hash = "35454B055CC325EA1AF2126E27707052";
        String password = "ILoveJava";

        KnowledgeFile kf = new KnowledgeFile();

        String md5Hex = kf.generateMd5(password);

        assertTrue(md5Hex.equalsIgnoreCase(hash));
    }


    @Test
    public void test04() {

        //Get file from resources folder
        ClassLoader         classLoader = getClass().getClassLoader();
        File                file        = new File(classLoader.getResource("knowledge.txt").getFile());
        KnowledgeFile       kf          = new KnowledgeFile();
        FinanceKnowledge    k           = kf.generateFinancialKnowledge(file);
        String              eol         = System.getProperty("line.separator");

        System.out.println("Md5   " + k.getMd5());
        System.out.println("Loc   " + k.getLoc());
        System.out.println("Url   " + k.getUrl());

        assertTrue(k.getTitle().equalsIgnoreCase("document title"));
        assertTrue(k.getTag().get(0).equalsIgnoreCase("Earnings Per Share"));
        assertTrue(k.getTag().get(1).equalsIgnoreCase("EPS"));

        StringBuilder sb = new StringBuilder();
        sb.append("Primary earnings per share calculated as: ").append(eol);
        sb.append("EPS = (").append(eol);
        sb.append("").append(eol);
        sb.append("When the capital structure of a company").append(eol);
        //assertTrue(k.getBody().equalsIgnoreCase(sb.toString()));
        //System.out.println(sb.toString());
    }
}

