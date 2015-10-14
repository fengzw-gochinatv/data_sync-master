package com.gochinatv.datasync.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: haoshihai
 * Date: 12-12-24
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */

public class MQManager {
    private static final String routing_api = "routing_api";
    private static final String ueue_apii = "queue_api";
    private static final String exchange_api = "exchange_api";
    private static final String exchangeType_direct = "direct";
    private Connection conn;
    private Channel channel;
    private static MQManager mqManager;
    private QueueingConsumer consumer = null;
    Logger logger = LoggerFactory.getLogger(MQManager.class);

    public static MQManager getInstance() {
        if (mqManager == null) {
            mqManager = new MQManager();
        }
        return mqManager;
    }

    private MQManager() {
        init();
    }

    public boolean init() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("rabbitMQ");
            String HOST = bundle.getString("mq.hostname");
            String USER = bundle.getString("mq.username");
            String PASSWORD = bundle.getString("mq.password");
            ConnectionFactory factory = new ConnectionFactory();
            logger.info("MQ_host={},MQ_user={},MQ_password={}", new Object[]{HOST, USER, PASSWORD});
            factory.setHost(HOST);
            factory.setPort(5672);
            factory.setUsername(USER);
            factory.setPassword(PASSWORD);
            conn = factory.newConnection();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Channel getChannel() throws Exception {
        if (channel == null || !channel.isOpen()) {
            logger.info("channel断了，重新建立一个 ");
            channel = conn.createChannel();
            channel.basicQos(0);
            channel.exchangeDeclare(exchange_api, exchangeType_direct, true);
            channel.queueDeclare(ueue_apii, true, false, false, null);
            channel.queueBind(ueue_apii, exchange_api, routing_api);
        }
        return channel;
    }

    //获取队列数量
    public int getQueueCount(String queue_name) {
        int c = 0;
        try {
            AMQP.Queue.DeclareOk a = channel.queueDeclarePassive(queue_name);
            //获取队列中的消息个数
            c = a.getMessageCount();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            return c;
        }
    }

    //生成者放入消息
    public boolean send(String exchangeName, String routing, String content) {
        try {
            Channel channel = getChannel();
            if (channel == null || !channel.isOpen()) {
                System.out.println("发个消息失败！！！连接断开。 exchangeName= routing={} content={}");
                return false;
            }
            byte[] msg = content.getBytes();
            channel.basicPublish(exchangeName, routing, MessageProperties.PERSISTENT_TEXT_PLAIN, msg);
            System.out.println("发送消息。 exchangeName=" + exchangeName + " routing=" + routing + " content=" + content);
            return true;
        } catch (Exception e) {
            e.toString();
            return false;
        }
    }

    //生成者放入消息
    public boolean send(String content) {
        try {
            Channel channel = getChannel();
            if (channel == null || !channel.isOpen()) {
                logger.info("发个消息失败！！！连接断开。 exchangeName= routing={} content={}");
                return false;
            }
            byte[] msg = content.getBytes();
            channel.basicPublish(exchange_api, routing_api, MessageProperties.PERSISTENT_TEXT_PLAIN, msg);
            logger.info("发送消息。 exchangeName=" + exchange_api + " routing=" + routing_api + " content=" + content);
            return true;
        } catch (Exception e) {
            e.toString();
            return false;
        }
    }

    public QueueingConsumer getConsumer() {
        while (consumer == null) {
            try {
                if (consumer == null) {
                    consumer = new QueueingConsumer(getChannel());
                    channel.basicConsume(ueue_apii, consumer);
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(5000);
                    logger.error("MQ getConsumer 正在尝试重新连接。。。。。。。。", e);
                } catch (InterruptedException ep) {
                    e.printStackTrace();
                }
            }
        }
        return consumer;
    }

    //从消费者每次取一条消息
    public synchronized String doReceive() {
        String message = "";
        try {
            QueueingConsumer.Delivery delivery = getConsumer().nextDelivery();
            message = new String(delivery.getBody());
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("get MQ message=" + message);
            return message;
        }
    }


    public static void main(String args[]) {
        JSONObject json = new JSONObject();
        json.put("videoId", "224053");
        MQManager mqManager1=MQManager.getInstance();
        //for (int i = 0; i < 20; i++) {json.put("videoId", i);
            mqManager1.send(json.toJSONString());
      //  }
        System.out.println(mqManager1.getQueueCount("-----------count="+ueue_apii));
/*        while (true) {
            MQManager.getInstance().doReceive();
        }*/

    }
}
