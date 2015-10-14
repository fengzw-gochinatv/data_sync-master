package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.ChannelPo;
import com.gochinatv.datasync.dao.mongoDao.ChannelDao;
import com.gochinatv.datasync.dao.sqlDao.ChannelSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:23
 */
@Service
public class ChannelService extends HanderDataService {

    @Resource
    ChannelDao channelDao;
    @Resource
    ChannelSqlDao channelSqlDao;

    public void syncData(Long channelId) {
        logger.info("  syncData channelId={}", channelId);
        try {
            if (channelId == -1) {
                resetChannel();
                return;
            }
            ChannelPo channelSQL = channelSqlDao.getChannelPo(channelId);
            ChannelPo channelMO = channelDao.findOne("channelId", channelId);

            if (channelSQL == null) {
                if (channelMO != null) channelDao.delete(channelMO);
            } else {
                if (channelMO != null) channelSQL.setId(channelMO.getId());
                channelDao.save(channelSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetChannel() {
        try {
            List<ChannelPo> list = channelSqlDao.getChannelList();
            for (ChannelPo channelPo : list) {
                ChannelPo channelMO = channelDao.findOne("channelId", channelPo.getChannelId());
                if (channelMO != null) channelPo.setId(channelMO.getId());
                channelDao.save(channelPo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
