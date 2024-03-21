package com.xmaquina.api.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;


@Component
public class MqttSample {
    private boolean run = false;

    @Value("${mqtt.url}")
    String url;

    @Value("${mqtt.user}")
    String user;

    @Value("${mqtt.password}")
    String password;

    @Async
    public CompletableFuture<String> start(Long s) throws InterruptedException {
        run = true;
        while (run) {
            Thread.sleep(1000);
            String broker = url;//"tcp://mosquitto:1883";
            String topic = "sensores";
            String content = "leituras,sensor_id=" + s + " temperature=" + new Random().nextDouble(15, 20)
                    + " ";
            int qos = 2;
            System.out.println(content);
            String clientId = MqttClient.generateClientId();
            MemoryPersistence persistence = new MemoryPersistence();
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(user);
            connOpts.setPassword(password.toCharArray());
            try {
                MqttClient client = new MqttClient(broker, clientId, persistence);
                client.setCallback(new SampleCallback());

//                System.out.println("Connecting to broker: " + broker);
                client.connect(connOpts);
//                System.out.println("Connected to broker: " + broker);
                //client.subscribe(topic, qos);
                //System.out.println("Subscribed to topic: " + topic);
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                client.publish(topic, message);
//                System.out.println("Message published sensor="+s);
                client.disconnect();
//                System.out.println("Disconnected");
                client.close();
            } catch (MqttException me) {
                System.out.println("reason " + me.getReasonCode());
                System.out.println("msg " + me.getMessage());
                System.out.println("loc " + me.getLocalizedMessage());
                System.out.println("cause " + me.getCause());
                System.out.println("excep " + me);
                me.printStackTrace();
                return CompletableFuture.completedFuture("Async task failed!");
            }
        }
        return CompletableFuture.completedFuture("Async task completed!");
    }

    public void stop() {
        run = false;
    }

}