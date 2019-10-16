package com.jpw.raptor.cmdline.queuedump;



import com.google.gson.Gson;

import com.jpw.raptor.lib.jms.ActiveMq;


import javax.jms.JMSException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QueueDumpApplication {

    public static void main(String[] args) throws Exception {

        System.out.println("Start");

        ActiveMq mq = new ActiveMq("scrape");

        String text = mq.get();
        while ( text != null) {
            System.out.println( text);
            text = mq.get();
        }
        mq.close();
        System.out.println("Finish");

    }

}
