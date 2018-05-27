package org.harmony.test.paho;

import static org.harmony.test.paho.Constraints.*;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author wuxii@foxmail.com
 */
public class MqttPublisher {

    private static final String clientId = "wuxii_foo";

    private MqttClient client;

    public void connect() throws MqttException {
        client = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
    }

    public void publish(String message) throws MqttException {
        MqttMessage msg = new MqttMessage(message.getBytes());
        msg.setQos(qos);
        client.publish(topic, msg);
    }

    public void close() throws MqttException {
        client.disconnectForcibly();
    }

    public static void main(String[] args) throws MqttException {
        MqttPublisher publisher = new MqttPublisher();

        publisher.connect();

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("mqtt publish>");
            String line = in.nextLine();
            if (".q".equals(line)) {
                break;
            }
            publisher.publish(line);
        }

        publisher.close();
        in.close();

        System.exit(0);
    }

}
