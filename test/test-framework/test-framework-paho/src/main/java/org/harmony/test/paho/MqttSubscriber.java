package org.harmony.test.paho;

import static org.harmony.test.paho.Constraints.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author wuxii@foxmail.com
 */
public class MqttSubscriber implements MqttCallback {

    private String clientId;

    private MqttClient client;

    public MqttSubscriber(String clientId) {
        this.clientId = clientId;
    }

    public void subscribe() throws MqttException {
        client = new MqttClient(broker, clientId);
        client.connect();
        client.setCallback(this);
        client.subscribe(topic);
        System.out.println(clientId + " subscribe topic " + topic);
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println(clientId + " lost connection");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(clientId + " get a new message " + message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println(clientId + " message delivered");
    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        String clientId = args.length > 0 ? args[0] : "wuxii_bar";
        MqttSubscriber subscriber = new MqttSubscriber(clientId);
        subscriber.subscribe();

        Thread.sleep(Long.MAX_VALUE);
    }

}
