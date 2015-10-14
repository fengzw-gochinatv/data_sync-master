package com.gochinatv.datasync.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import com.gochinatv.datasync.service.SyncData.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午3:21
 */
@Component
public class ApiConsumer {

    @Resource
    ServiceFactory serviceFactory;
    Logger logger = LoggerFactory.getLogger(ApiConsumer.class);

    ThreadPoolExecutor exec = new ThreadPoolExecutor(100, 100, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(100));

    @PostConstruct
    public void handMessage() {
        exec.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
        t.start();
    }

    public void start() {
        logger.info("handMessage will start ==============================");
        MQManager mqManager = MQManager.getInstance();
        String content;
        while ((content = mqManager.doReceive()) != null && content != "") {
            logger.info("handMessage  receive MQ content={} ", content);
            try {
                JSONObject data = JSONObject.parseObject(content);
                exec.execute(new HandMessage(data));
            } catch (Exception e) {
                logger.error("content={} " + e.getMessage(), e);
            }
        }
    }

    class HandMessage implements Runnable {
        private JSONObject data;

        HandMessage(JSONObject data) {
            this.data = data;
        }

        public void run() {
            try {
                logger.info("HandMessage  start sleep 5 second and handler data={}",data);
                Thread.sleep(10 * 1000);
                HanderDataService handerDataService = serviceFactory.handObject(data);
                handerDataService.syncData(data.getLong("id"));
            } catch (Exception e) {
                logger.error(e.toString(), e);
                e.printStackTrace();
            }
        }
    }

}
