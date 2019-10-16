package com.jpw.raptor.cmdline.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class Application 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SpringApplication application = new SpringApplication(Application.class);

        System.out.println("Starting API");
        application.run(args);

        System.out.println( "Goodbye World!" );
    }
}
