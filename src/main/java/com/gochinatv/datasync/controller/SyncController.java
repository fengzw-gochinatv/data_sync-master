package com.gochinatv.datasync.controller;

import com.gochinatv.datasync.service.SyncData.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class SyncController {
    Logger logger = LoggerFactory.getLogger(SyncController.class);
    @Resource
    ServiceFactory serviceFactory;

    @RequestMapping("syncData")
    public void syncData(@RequestParam(required = true) String type, HttpServletResponse response,
                         @RequestParam(required = false, defaultValue = "0") Long channelId,
                         @RequestParam(required = false, defaultValue = "0") Long albumId) throws Exception {
        try {
            long start = System.currentTimeMillis();
            logger.info("syncData start ============");
            serviceFactory.resetMongoDB(type, channelId, albumId);
            long costTime = (System.currentTimeMillis() - start) / 1000;
            logger.info("syncData end  costTime={}============", costTime);
            response.getWriter().write("syncData is success,costTime=" + costTime);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            response.getWriter().write("syncData error" + e.getMessage());
        }
    }


    @RequestMapping("checkData")
    public void checkData(@RequestParam(required = true) String type, HttpServletResponse response) throws Exception {
        try {
            long start = System.currentTimeMillis();
            logger.info("checkData start =======================");
            serviceFactory.checkData(type);
            long costTime = (System.currentTimeMillis() - start) / 1000;
            logger.info("checkData end  costTime={}=======================", costTime);
            response.getWriter().write("syncData is success,costTime=" + costTime);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            response.getWriter().write("checkData error" + e.getMessage());
        }
    }
}