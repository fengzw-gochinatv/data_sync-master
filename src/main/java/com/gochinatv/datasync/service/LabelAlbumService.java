package com.gochinatv.datasync.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gochinatv.datasync.bean.AlbumPo;
import com.gochinatv.datasync.bean.LabelAlbumPo;
import com.gochinatv.datasync.dao.mongoDao.LabelAlbumDao;
import com.gochinatv.datasync.dao.sqlDao.LabelAlbumSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:23
 */
@Service
public class LabelAlbumService extends HanderDataService {

    @Resource
    LabelAlbumSqlDao labelAlbumSqlDao;
    @Resource
    LabelAlbumDao labelAlbumDao;

    public void syncData(Long labelId) {
        logger.info("LabelAlbumService  syncData labelId={}", labelId);
        try {
            if (labelId == -1) {
                resetLabelAlbum();
                return;
            }
            List<LabelAlbumPo> list = labelAlbumSqlDao.getLabelAlumPoList(labelId);
            for (LabelAlbumPo lb : list) {
                LabelAlbumPo labelAlbumMO = labelAlbumDao.findOne(labelAlbumDao.createQuery().field("lid").equal(lb.getLid()));
                if (labelAlbumMO != null) lb.setId(labelAlbumMO.getId());
                labelAlbumDao.save(lb);
            }
            delLabelAlbum(list, labelId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetLabelAlbum() {
        try {
            List<LabelAlbumPo> list = labelAlbumSqlDao.getAllList();
            for (LabelAlbumPo lb : list) {
                LabelAlbumPo labelAlbumMO = labelAlbumDao.findOne(labelAlbumDao.createQuery().field("lid").equal(lb.getLid()));
                if (labelAlbumMO != null) lb.setId(labelAlbumMO.getId());
                labelAlbumDao.save(lb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }


    public void delLabelAlbum(List<LabelAlbumPo> list, long labelId) {
        List lids = new ArrayList();
        List moids = new ArrayList();
        for (LabelAlbumPo labelAlbumPo : list) lids.add(labelAlbumPo.getLid());
        DBCursor cursor = labelAlbumDao.getCollection().find(new BasicDBObject("labelId", labelId));
        for (DBObject object : cursor) moids.add(object.get("lid"));
        moids.removeAll(lids);
        if (!moids.isEmpty()) {
            logger.info("delLabelAlbum  lid={}", moids);
            labelAlbumDao.deleteByQuery(labelAlbumDao.createQuery().field("lid").in(moids));
        }
    }

    public void delLabelAlbum(long albumId) {
           labelAlbumDao.deleteByQuery(labelAlbumDao.createQuery().field("albumId").equal(albumId));
    }

}
