package org.moon.jms;

import org.apache.activemq.broker.BrokerService;
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

}
