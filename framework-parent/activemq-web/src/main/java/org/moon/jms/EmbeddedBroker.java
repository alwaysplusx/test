package org.moon.jms;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.store.memory.MemoryPersistenceAdapter;

public final class EmbeddedBroker {

	static BrokerService broker;

	private EmbeddedBroker() {
	}

	public static void start() throws Exception {
		broker = new BrokerService();
		broker.setPersistenceAdapter(new MemoryPersistenceAdapter());
		broker.setDataDirectory("target/activemq-data");
		broker.addConnector("tcp://localhost:61616");
		broker.start();
	}

	public static void stop() throws Exception {
		broker.stop();
	}

	public static void main(String[] args) throws Exception {
		EmbeddedBroker.start();
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		ActiveMQQueue queue = new ActiveMQQueue("moon.inbondMessage");

		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(queue);
		TextMessage textMessage = session.createTextMessage("simple text message");
		producer.send(textMessage);
		producer.close();
		session.close();
		connection.close();

		Connection connection2 = connectionFactory.createConnection();
		connection2.start();
		Session session2 = connection2.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session2.createConsumer(queue);
		Message message = consumer.receive();
		TextMessage msg = (TextMessage) message;
		System.err.println(msg.getText());
		consumer.close();
		session2.close();
		connection2.close();
		EmbeddedBroker.stop();
	}

}
