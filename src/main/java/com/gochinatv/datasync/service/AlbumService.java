package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.AlbumPo;
import com.gochinatv.datasync.bean.ChannelPo;
import com.gochinatv.datasync.dao.mongoDao.AlbumDao;
import com.gochinatv.datasync.dao.mongoDao.ChannelDao;
import com.gochinatv.datasync.dao.mongoDao.VideoDao;
import com.gochinatv.datasync.dao.sqlDao.AlbumSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import com.google.code.morphia.query.QueryResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;


/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:23
 */
@Service
public class AlbumService extends HanderDataService {
    @Resource
    LabelAlbumService labelAlbumService;
    @Resource
    ChannelService channelService;
    @Resource
    ChannelDao channelDao;
    @Resource
    AlbumDao albumDao;
    @Resource
    AlbumSqlDao albumSqlDao;
    @Resource
    VideoDao videoDao;


    public void syncData(Long albumId) {
        logger.info(" AlbumService  syncData albumId={}", albumId);
        try {
            AlbumPo albumSQL = albumSqlDao.getAlbumPo(albumId);
            AlbumPo albumMO = albumDao.findOne("aid", albumId);
            if (albumSQL == null) {
                if (albumMO != null) albumDao.delete(albumMO);
                labelAlbumService.delLabelAlbum(albumId);//删除标签
                //videoDao.deleteByQuery(videoDao.createQuery().field("aid").equal(albumId));
            } else {
                if (albumMO != null) albumSQL.setId(albumMO.getId());
                albumDao.save(albumSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetAlbum(Long channelId) {
        logger.info("resetAlbum  start======================channelId={}", channelId);
        long start = System.currentTimeMillis();
        List<ChannelPo> list = new ArrayList<ChannelPo>();
        if (channelId > 0) {
            ChannelPo channelPo = new ChannelPo();
            channelPo.setChannelId(channelId);
            list.add(channelPo);
        } else {
            list = channelDao.find().asList();
        }
        for (ChannelPo channelPo : list) {
            List<AlbumPo> albumList = albumSqlDao.getAlbumList(channelPo.getChannelId());
            if (albumList != null && albumList.size() > 0) {
                for (AlbumPo albumPo : albumList) {
                    AlbumPo albumMO = albumDao.findOne("aid", albumPo.getAid());
                    if (albumMO != null) albumPo.setId(albumMO.getId());
                    albumDao.save(albumPo);
                }
            }
        }
        logger.info("resetAlbum  end costTime={}===================", (System.currentTimeMillis() - start));
    }
}
