package at.czlabinger.zentrale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import at.czlabinger.model.WarehouseData;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarehouseReceiver {

    private static HashMap<String, WarehouseReceiver> clients = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
    private Session session = null;
    private Connection connection = null;
    private MessageConsumer consumer = null;

    public WarehouseReceiver(String subject) {

        try {
            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                            ActiveMQConnection.DEFAULT_PASSWORD,
                            ActiveMQConnection.DEFAULT_BROKER_URL);
            connectionFactory.setTrustedPackages(
                    List.of("at.czlabinger.model", "java.util"));
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(subject);
            consumer = session.createConsumer(destination);
        } catch (Exception e) {
            System.out.println("Registration Caught: " + e);
            stop();
        }
    }

    public void stop() {
        try {
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WarehouseData> getMessage() {
        ArrayList<WarehouseData> msgList = new ArrayList<>();
        try {
            // Get messages from queue
            ObjectMessage message = (ObjectMessage)consumer.receiveNoWait();
            while (message != null) {
                WarehouseData value = (WarehouseData)message.getObject();
                LOGGER.info("Received Message: " + value.toString());
                msgList.add(value);
                message.acknowledge();
                message = (ObjectMessage)consumer.receiveNoWait();
            }
        } catch (JMSException e) {
            System.err.println(e);
            return null;
        }

        return msgList;
    }
}
