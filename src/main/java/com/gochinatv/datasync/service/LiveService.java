package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.CarouselPo;
import com.gochinatv.datasync.bean.Live;
import com.gochinatv.datasync.dao.mongoDao.LiveDao;
import com.gochinatv.datasync.dao.sqlDao.LiveSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-21
 * Time:下午6:21
 */
@Service
public class LiveService extends HanderDataService {
    @Resource
    LiveDao liveDao;
    @Resource
    LiveSqlDao liveSqlDao;

    public void syncData(Long liveId) {
        logger.info("syncData liveId={}", liveId);
        try {
            if (liveId == -1) {
                resetLive();
                return;
            }
            Live liveSQL = liveSqlDao.getLive(liveId);
            Live liveMO = liveDao.findOne("liveId", liveId);
            if (liveSQL == null) {
                if (liveMO != null) liveDao.delete(liveMO);
            } else {
                if (liveMO != null) liveSQL.setId(liveMO.getId());
                liveDao.save(liveSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetLive() {
        liveDao.deleteByQuery(liveDao.createQuery());
        List<Live> list = liveSqlDao.getLiveList();
        for (Live live : list) {
            Live focusMO = liveDao.findOne("liveId", live.getLiveId());
            if (focusMO != null) live.setId(focusMO.getId());
            liveDao.save(live);
        }
    }
}
