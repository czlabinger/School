package at.czlabinger.lagerstandort.warehouse;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Destination;
import javax.jms.ConnectionFactory;
import javax.jms.ObjectMessage;
import javax.jms.JMSException;
import javax.jms.DeliveryMode;

import java.io.Serializable;


public class WarehouseSender {

    private static final String USER = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    Session session = null;
    Connection connection = null;
    MessageProducer producer = null;
    Destination destination = null;

    public WarehouseSender(String subject) {

        System.out.println("Sender started.");

        // Create connection
        try {

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, URL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(subject);

            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } catch (Exception e) {
            System.err.println("MessageProducer Caught: " + e);
        }
    }

    public void stop() {
        try {
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            System.err.println("Failed while closing " + e);
        }
    }

    public void sendMessage(Serializable obj) {
        try {
            ObjectMessage message = session.createObjectMessage(obj);
            producer.send(message);
        } catch (JMSException e) {
            System.err.println("Error while sending Message: " + e);
        }
    }
}
