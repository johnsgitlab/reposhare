package com.jpw.raptor.cmdline.ingest;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.jpw.raptor.lib.properties.FinanceProperties;
import com.jpw.raptor.model.FileQualified;
import com.jpw.raptor.model.FinanceKnowledge;
import com.jpw.raptor.model.Index;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.search.finance.ElasticSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.jms.JMSException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class IngestApplication implements CommandLineRunner {

    @Autowired
    public EodDataFile      eodFile;

    @Autowired
    public KnowledgeFile    knowFile;

    @Autowired
    public HiYieldFile      hiYieldFile;

    @Autowired
    ElasticSearch elasticSearch;

    public void knowledgeIngest() throws IOException {

        System.out.println();
        System.out.println("***************  Knowledge data ingest  *******************");


        // import directory
        FinanceProperties fp   = new FinanceProperties();
        Properties prop = fp.get();

        String dir  = prop.getProperty("knowledge_dir");
        if ( dir == null )
        {
            System.out.println("Knowledge storage directory not specified");
            return;
        }

        com.jpw.raptor.cmdline.ingest.KnowledgeFileUtil fileUtil = new com.jpw.raptor.cmdline.ingest.KnowledgeFileUtil();

        // Get a list of files to process
        List<File> filesToProcess = fileUtil.listFilesInDirAndSubDir();

        // Process each file
        for ( File file : filesToProcess ) {

            // do not process html files
            if ( !file.getCanonicalPath().endsWith(".html") ) {

                System.out.println("Processs " + file.getCanonicalPath());

                // Extract quotes and write them to the database
                FinanceKnowledge knowObj = knowFile.indexFile(elasticSearch, file);

                if (knowObj != null) {
                    String sourceDoc    = file.getCanonicalPath();
                    String sourceHtml   =
                            new String (file.getCanonicalPath().substring(0,file.getCanonicalPath().lastIndexOf('.')) + ".html");

                    String destDoc      = new String (dir + "/" + knowObj.getLoc());
                    String destHtml     = new String (dir + "/" + knowObj.getUrl());

                    // need to move doc file
                    fileUtil.moveFile(sourceDoc, destDoc);

                    // need to move html file
                    fileUtil.moveFile(sourceHtml, destHtml);
                } else {
                    // Delete the file
                    System.out.println("Processs failed for " + file.getCanonicalPath());
                    fileUtil.delFile(file);
                }
            }
        }

    }

    public void eodIngest() {

        System.out.println();
        System.out.println("***************  EOD data ingest  *******************");

        EodFileUtil fileUtil = new EodFileUtil();

        // Get a list of files to process
        List<FileQualified> filesToProcess = fileUtil.getFileList();

        // Process each file
        for ( FileQualified fileQualified : filesToProcess ) {
            System.out.println("Processs " + fileQualified.getName());

            // Extract quotes and write them to the database
            eodFile.processFile(fileQualified);

            // Delete the file
            fileUtil.deleteFile(fileQualified.getName());
        }

        // Update market returns
        System.out.println("Update performance");
        eodFile.updatePerformance();

        // Update treasuries
        eodFile.updateTreasuries();

    }


    public void hiYieldIngest() {

        System.out.println();
        System.out.println("***************  Hi Yield  data ingest  *******************");

        EodFileUtil fileUtil = new EodFileUtil();

        // Get a list of files to process
        List<FileQualified> filesToProcess = fileUtil.getHiYieldFileList();

        // Process each file
        for ( FileQualified fileQualified : filesToProcess ) {
            System.out.println("Processs " + fileQualified.getName());

            // Extract quotes and write them to the database
            hiYieldFile.processFile(fileQualified);

            // Delete the file
            fileUtil.deleteFile(fileQualified.getName());
        }

    }

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        // define the run time parameters
        AppParameters params = new AppParameters();

        // create parameter parser
        JCommander cmd = new JCommander(params);

        try {
            cmd.parse(args);

            if ( params.getKnowledge() != null ) {
                knowledgeIngest();
            } else {
                eodIngest();
                hiYieldIngest();
            }

        } catch (ParameterException ex) {
            System.out.println(ex.getMessage());
            cmd.usage();
        }

        System.out.println("***************  Good bye  *******************");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        //SpringApplication app = new SpringApplication(IngestApplication.class);
        //app.setBannerMode(Banner.Mode.OFF);
        //app.run(args);
        System.exit(SpringApplication
                .exit(SpringApplication.run(IngestApplication.class, args)));
    }

}
