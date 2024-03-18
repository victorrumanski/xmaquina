package com.xmaquina.api;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Random;


public class MqttSample {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(5000);
            String broker = "tcp://172.235.141.8:1883";
            String topic = "sensores";
            String content = "leituras,sensor_id=1 temperature=" + new Random().nextDouble(15, 19)
                    + " ";// + System.nanoTime();
            int qos = 2;
            System.out.println(content);
            String clientId = MqttClient.generateClientId();
            MemoryPersistence persistence = new MemoryPersistence();
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("pubclient");
            connOpts.setPassword("x".toCharArray());
            try {
                MqttClient client = new MqttClient(broker, clientId, persistence);
                client.setCallback(new SampleCallback());

                System.out.println("Connecting to broker: " + broker);
                client.connect(connOpts);
                System.out.println("Connected to broker: " + broker);
                client.subscribe(topic, qos);
                System.out.println("Subscribed to topic: " + topic);
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                client.publish(topic, message);
                System.out.println("Message published");
                client.disconnect();
                System.out.println("Disconnected");
                client.close();
//                System.exit(0);
            } catch (MqttException me) {
                System.out.println("reason " + me.getReasonCode());
                System.out.println("msg " + me.getMessage());
                System.out.println("loc " + me.getLocalizedMessage());
                System.out.println("cause " + me.getCause());
                System.out.println("excep " + me);
                me.printStackTrace();
            }
        }
    }

}