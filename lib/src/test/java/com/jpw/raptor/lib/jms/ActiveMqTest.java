package com.jpw.raptor.lib.jms;

import org.junit.Test;

import javax.jms.JMSException;

/**
 * Created by John on 10/25/2017.
 */
public class ActiveMqTest {

    @Test
    public void test() throws JMSException
    {
        System.out.println("Start");

        ActiveMq mq = new ActiveMq("test");
        mq.put("test message 98");
        String text = mq.get();
        while ( text != null) {
            System.out.println( text);
            text = mq.get();
        }
        mq.close();
        System.out.println("Finish");
    }
}
