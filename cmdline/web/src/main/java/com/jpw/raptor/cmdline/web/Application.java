package com.jpw.raptor.cmdline.web;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class Application 
{
    private final static Logger logger = Logger.getLogger(Application.class);

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SpringApplication application = new SpringApplication(Application.class);

        logger.info("Starting Web App");
        application.run(args);

        System.out.println( "Goodbye World!" );
    }
}
