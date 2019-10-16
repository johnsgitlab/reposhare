package com.jpw.raptor.cmdline.add;

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
public class AddApplication implements CommandLineRunner {

    @Autowired
    public EodDataFile eodFile;


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

    }

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        // define the run time parameters
        AppParameters params = new AppParameters();

        // create parameter parser
        JCommander cmd = new JCommander(params);

        try {

            // Validate the parameters
            cmd.parse(args);
            if ( params.getEtf() != null ) {
                eodFile.postConstruct("etf", params.getEtf());
            } else if (params.getFund() != null ) {
                eodFile.postConstruct("fund", params.getFund());
            } else if ( params.getIndex() != null ) {
                eodFile.postConstruct("index", params.getIndex());
            } else if ( params.getStock() != null ) {
                eodFile.postConstruct("stock", params.getStock());
            }else {
                throw new ParameterException("Must specify etf, fund or index");
            }

            eodIngest();

        } catch (ParameterException ex) {
            System.out.println(ex.getMessage());
            cmd.usage();
        }

        System.out.println("***************  Good bye  *******************");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        //SpringApplication app = new SpringApplication(AddApplication.class);
        //app.setBannerMode(Banner.Mode.OFF);
        //app.run(args);
        System.exit(SpringApplication
                .exit(SpringApplication.run(AddApplication.class, args)));
    }

}
