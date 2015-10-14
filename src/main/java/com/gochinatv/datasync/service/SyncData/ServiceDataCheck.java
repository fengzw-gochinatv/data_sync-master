package com.gochinatv.datasync.service.SyncData;

import com.gochinatv.datasync.bean.LabelAlbumPo;
import com.gochinatv.datasync.dao.mongoDao.AlbumDao;
import com.gochinatv.datasync.dao.mongoDao.LabelAlbumDao;
import com.gochinatv.datasync.dao.mongoDao.VideoDao;
import com.gochinatv.datasync.dao.sqlDao.AlbumSqlDao;
import com.gochinatv.datasync.dao.sqlDao.LabelAlbumSqlDao;
import com.gochinatv.datasync.dao.sqlDao.VideoSqlDao;
import com.gochinatv.datasync.service.AlbumService;
import com.gochinatv.datasync.service.LabelAlbumService;
import com.gochinatv.datasync.service.VideoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;


/**
 * Created by shhao.
 * Date: 15-4-13
 * Time:下午2:50
 */
@Service
public class ServiceDataCheck {

    @Resource
    AlbumSqlDao albumSqlDao;
    @Resource
    AlbumDao albumDao;
    @Resource
    AlbumService albumService;
    @Resource
    VideoSqlDao videoSqlDao;
    @Resource
    VideoDao videoDao;
    @Resource
    VideoService videoService;
    @Resource
    LabelAlbumService labelAlbumService;
    @Resource
    LabelAlbumDao labelAlbumDao;
    @Resource
    LabelAlbumSqlDao labelAlbumSqlDao;


    Logger logger = LoggerFactory.getLogger(ServiceDataCheck.class);

    public void dataCheck() {
        logger.info("dataCheck====================start=================");
        albumCheck();
        videoCheck();
        labelAlbumCheck(null);
    }

    //检查albumID
    public void albumCheck() {
        try {
            logger.info("albumCheck====================start=================");
            List<Long> addAid = new ArrayList();
            List<Long> delAid = new ArrayList();
            List<Long> albumMOList = new ArrayList();
            List<Long> albumSQLList = albumSqlDao.getAlbumIds();
            DBCursor cursor = albumDao.getCollection().find(null, new BasicDBObject("aid", 1)).sort(new BasicDBObject("aid", 1));
            for (DBObject o : cursor) albumMOList.add((Long) o.get("aid"));
            int moSize = albumMOList.size();
            int sqlSize = albumSQLList.size();
            int i = 0, j = 0;
            while (i < moSize && j < sqlSize) {

                long aidMO = albumMOList.get(i);
                long aidSQL = albumSQLList.get(j);
                if (aidMO == aidSQL) {
                    i++;
                    j++;
                } else if (aidMO < aidSQL) {
                    i++;
                    if (!delAid.contains(aidMO)) delAid.add(aidMO);
                } else if (aidMO > aidSQL) {
                    j++;
                    if (!addAid.contains(aidSQL)) addAid.add(aidSQL);
                }
            }
            for (; i < moSize; i++) delAid.add(albumMOList.get(i));
            for (; j < sqlSize; j++) addAid.add(albumSQLList.get(j));
            for (Long id : addAid) {
                logger.info("================ addAlbumId=" + id);
                albumService.syncData(id);
            }
            for (Long id : delAid)  {
                logger.info("================ delAlbumId=" + id);
                albumService.syncData(id);
            }
            // albumDao.deleteByQuery(albumDao.createQuery().field("aid").in(delAid));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    //检查videoID
    public void videoCheck() {
        try {
            logger.info("videoCheck====================start=================");
            List<Long> addVideoId = new ArrayList();
            List<Long> delVideoId = new ArrayList();
            List<Long> videoMOList = new ArrayList();
            List<Long> videoSQLList = videoSqlDao.getVideoIds();
            DBCursor cursor = videoDao.getCollection().find(null, new BasicDBObject("vid", 1)).sort(new BasicDBObject("vid", 1));
            for (DBObject o : cursor) videoMOList.add((Long) o.get("vid"));
            int moSize = videoMOList.size();
            int sqlSize = videoSQLList.size();
            int i = 0, j = 0;
            while (i < moSize && j < sqlSize) {
                long aidMO = videoMOList.get(i);
                long aidSQL = videoSQLList.get(j);
                if (aidMO == aidSQL) {
                    i++;
                    j++;
                } else if (aidMO < aidSQL) {
                    i++;
                    if (!delVideoId.contains(aidMO)) delVideoId.add(aidMO);
                } else {
                    j++;
                    if (!addVideoId.contains(aidSQL)) addVideoId.add(aidSQL);
                }
            }
            for (; i < moSize; i++) delVideoId.add(videoMOList.get(i));
            for (; j < sqlSize; j++) addVideoId.add(videoSQLList.get(j));
            for (Long id : addVideoId) {
                logger.info("================ addVideoId=" + id);
                videoService.syncData(id);
            }
            for (Long id : delVideoId){
                logger.info("================ delVideoId=" + id);
                videoService.syncData(id);
            }
           // if (!delVideoId.isEmpty()) videoDao.deleteByQuery(videoDao.createQuery().field("vid").in(delVideoId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("videoCheck===" + e.getMessage(), e);
        }

    }

    //检查label_album
    public void labelAlbumCheck(Map query) {
        try {
            logger.info("labelAlbumCheck====================start=================");
            List<Long> addLabelAlbum = new ArrayList();
            List<Long> delLabelAlbum = new ArrayList();
            List<Long> LabelAlbumMOList = new ArrayList();
            List<Long> LabelAlbumSQLList = labelAlbumSqlDao.getIds(query);
            DBCursor cursor = labelAlbumDao.getCollection().find(new BasicDBObject("lid", new BasicDBObject("$gte",1)), new BasicDBObject("lid", 1)).sort(new BasicDBObject("lid", 1));
            for (DBObject o : cursor) LabelAlbumMOList.add((Long) o.get("lid"));
            int moSize = LabelAlbumMOList.size();
            int sqlSize = LabelAlbumSQLList.size();
            int i = 0, j = 0;
            while (i < moSize && j < sqlSize) {
                long aidMO = LabelAlbumMOList.get(i);
                long aidSQL = LabelAlbumSQLList.get(j);
                if (aidMO == aidSQL) {
                    i++;
                    j++;
                } else if (aidMO < aidSQL) {
                    i++;
                    if (!delLabelAlbum.contains(aidMO)) delLabelAlbum.add(aidMO);
                } else {
                    j++;
                    if (!addLabelAlbum.contains(aidSQL)) addLabelAlbum.add(aidSQL);
                }
            }
            for (; i < moSize; i++) delLabelAlbum.add(LabelAlbumMOList.get(i));
            for (; j < sqlSize; j++) addLabelAlbum.add(LabelAlbumSQLList.get(j));
            for (Long lid : addLabelAlbum) {
                logger.info("================ addlid=" + lid + "==================");
                LabelAlbumPo labelAlbumPo = labelAlbumSqlDao.getOne(lid);
                LabelAlbumPo labelAlbumPoMO = labelAlbumDao.findOne(labelAlbumDao.createQuery().field("lid").equal(lid));
                if (labelAlbumPoMO != null) labelAlbumPo.setId(labelAlbumPoMO.getId());
                labelAlbumDao.save(labelAlbumPo);
            }
            if (!delLabelAlbum.isEmpty())
                labelAlbumDao.deleteByQuery(labelAlbumDao.createQuery().field("lid").in(delLabelAlbum));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("videoCheck===" + e.getMessage(), e);
        }
    }


}
