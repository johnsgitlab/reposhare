package com.jpw.raptor.lib.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Created by John on 10/24/2017.
 */
public class ActiveMq {

    // javax.jms objects
    private Connection  	cConnection;
    private String			cDestination;
    private Session     	cSession;
    private Destination 	cQueue;
    private MessageProducer cProducer;
    private MessageConsumer cConsumer;

    // Constructor
    public ActiveMq (String destination) {

        cConnection		= null;
        cDestination	= null;
        cSession		= null;
        cQueue			= null;
        cProducer		= null;
        cConsumer		= null;

        // Get a JMS connection to the server
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            cConnection = connectionFactory.createConnection();

            // connect to the JMS server
            cConnection.start();

            // create session for sending messages
            cSession = cConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create a destination from the queue name
            cQueue = cSession.createQueue(destination);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String get() throws JMSException {
        String result = null;
        long   timeOutInMilli = 500;

        if ( cConsumer == null) {
            // create a message consumer for receiving messages
            cConsumer = cSession.createConsumer(cQueue);
        }

        // read a message
        Message message = cConsumer.receive(timeOutInMilli);
        if ( message != null ) {
            result = ((TextMessage) message).getText();
        } else {
            System.out.println("No message on queue to read");
        }

        // return the message
        return result;
    }

    public void put(String text) throws JMSException {

        if ( cProducer == null ) {
            // create a message producer for sending messages
            cProducer = cSession.createProducer(cQueue);
        }

        // Format the message
        TextMessage message = cSession.createTextMessage();
        message.setText(text);

        // Send the message
        cProducer.send(message);

    }

    public void close() throws JMSException {

        if ( cProducer != null )
            cProducer.close();

        if ( cConsumer != null ) {
            cConsumer.close();
        }

        cSession.close();
        cConnection.close();
    }
}
