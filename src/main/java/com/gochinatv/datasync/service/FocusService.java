package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.FocusPo;
import com.gochinatv.datasync.dao.mongoDao.FocusDao;
import com.gochinatv.datasync.dao.sqlDao.FocusSqlDao;
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
public class FocusService extends HanderDataService {

    @Resource
    FocusSqlDao focusSqlDao;
    @Resource
    FocusDao focusDao;

    public void syncData(Long focusId) {
        logger.info("FocusService  syncData focusId={}", focusId);
        try {
            if (focusId == -1) {
                resetFocus();
                return;
            }
            FocusPo focusSQL = focusSqlDao.getFocusPo(focusId);
            FocusPo focusMO = focusDao.findOne("focusId", focusId);
            if (focusSQL == null) {
                if (focusMO != null) focusDao.delete(focusMO);
            } else {
                if (focusMO != null) focusSQL.setId(focusMO.getId());
                focusDao.save(focusSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetFocus() {
        List<FocusPo> list = focusSqlDao.getFocusList();
        for (FocusPo focusPo : list) {
            FocusPo focusMO = focusDao.findOne("focusId", focusPo.getFocusId());
            if (focusMO != null) focusPo.setId(focusMO.getId());
            focusDao.save(focusPo);
        }
    }
}
