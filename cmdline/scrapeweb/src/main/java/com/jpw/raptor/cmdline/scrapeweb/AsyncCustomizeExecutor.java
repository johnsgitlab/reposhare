package com.jpw.raptor.cmdline.scrapeweb;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by john on 4/2/18.
 */
@Configuration
@EnableAsync
public class AsyncCustomizeExecutor extends AsyncConfigurerSupport{

    /*

     Take this example.
     Starting thread pool size is 1, core pool size is 5, max pool size is 10 and the queue is 100.

     As requests come in, threads will be created up to 5 and then tasks will be added to the queue until it reaches 100.
     When the queue is full new threads will be created up to maxPoolSize.
     Once all the threads are in use and the queue is full tasks will be rejected.
     As the queue reduces, so does the number of active threads.

     By setting corePoolSize and maximumPoolSize the same, you create a fixed-size thread pool.

     */

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("Scraper");
        executor.initialize();
        return executor;
    }
}
