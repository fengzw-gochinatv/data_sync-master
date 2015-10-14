package com.gochinatv.datasync.service.SyncData;

import com.alibaba.fastjson.JSONObject;
import com.gochinatv.datasync.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午5:09
 */
@Component
public class ServiceFactory {

    @Resource
    AlbumService albumService;
    @Resource
    AreaService areaService;
    @Resource
    CategoryService categoryService;
    @Resource
    ChannelService channelService;
    @Resource
    FocusService focusService;
    @Resource
    LabelAlbumService labelAlbumService;
    @Resource
    AgeService ageService;
    @Resource
    VideoService videoService;
    @Resource
    MenuService menuService;
    @Resource
    CarouseService carouseService;
    @Resource
    LiveService liveService;
    @Resource
    ServiceDataCheck serviceDataCheck;

    Logger logger = LoggerFactory.getLogger(ServiceFactory.class);

    public HanderDataService handObject(JSONObject object) {

        String clazzType = object.getString("type");
        if ("album".equals(clazzType)) {
            return albumService;
        } else if ("area".equals(clazzType)) {
            return areaService;
        } else if ("category".equals(clazzType)) {
            return categoryService;
        } else if ("channel".equals(clazzType)) {
            return channelService;
        } else if ("focus".equals(clazzType)) {
            return focusService;
        } else if ("label".equals(clazzType)) {
            return labelAlbumService;
        } else if ("video".equals(clazzType)) {
            return videoService;
        } else if ("menu".equals(clazzType)) {
            return menuService;
        } else if ("carousel".equals(clazzType)) {
            return carouseService;
        } else if ("livetype".equals(clazzType)) {
            return liveService;
        }

        return null;
    }


    public void resetMongoDB(String clazzType, Long channelId, Long albumId) {
        if ("album".equals(clazzType)) {
            albumService.resetAlbum(channelId);
        } else if ("video".equals(clazzType)) {
            videoService.resetVideo(albumId);
        } else if ("area".equals(clazzType)) {
            areaService.resetArea();
        } else if ("category".equals(clazzType)) {
            categoryService.resetCategory();
        } else if ("channel".equals(clazzType)) {
            channelService.resetChannel();
        } else if ("focus".equals(clazzType)) {
            focusService.resetFocus();
        } else if ("label".equals(clazzType)) {
            labelAlbumService.resetLabelAlbum();
        } else if ("menu".equals(clazzType)) {
            menuService.syncData(-1l);
        } else if ("carousel".equals(clazzType)) {
            carouseService.syncData(-1l);
        } else if ("live".equals(clazzType)) {
            liveService.syncData(-1l);
        } else if ("all".equals(clazzType)) {
            resetAll();
        }
    }


    private void resetAll() {
        long start = System.currentTimeMillis();
        try {
            logger.info("resetAll mongodb data start ==========================");
            menuService.syncData(null);
            channelService.resetChannel();
            focusService.resetFocus();
            areaService.resetArea();
            albumService.resetAlbum(0l);
            categoryService.resetCategory();
            labelAlbumService.resetLabelAlbum();
            videoService.resetVideo(0l);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            e.printStackTrace();
        }
        logger.info("resetAll mongodb data end costTime={}====================", (System.currentTimeMillis() - start) / 1000);
    }


    public void checkData(String type) {
        if ("album".equals(type)) {
            serviceDataCheck.albumCheck();
        } else if ("video".equals(type)) {
            serviceDataCheck.videoCheck();
        }
    }
}
