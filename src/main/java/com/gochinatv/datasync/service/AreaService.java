package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.AreaPo;
import com.gochinatv.datasync.dao.mongoDao.AreaDao;
import com.gochinatv.datasync.dao.sqlDao.AreaSqlDao;
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
public class AreaService extends HanderDataService {
    @Resource
    AreaSqlDao areaSqlDao;
    @Resource
    AreaDao areaDao;

    public void syncData(Long areaId) {
        logger.info(" AreaService  syncData areaId={}", areaId);
        try {
            if (areaId == -1) {
                resetArea();
                return;
            }
            AreaPo areaSQL = areaSqlDao.getAreaPo(areaId);
            AreaPo areaMO = areaDao.findOne("areaId", areaId);
            if (areaSQL == null) {
                if (areaMO != null) areaDao.delete(areaMO);
            } else {
                if (areaMO != null) areaSQL.setId(areaMO.getId());
                areaDao.save(areaSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetArea() {
        List<AreaPo> list = areaSqlDao.getAreaList();
        for (AreaPo areaPo : list) {
            AreaPo areaMO = areaDao.findOne("areaId", areaPo.getAreaId());
            if (areaMO != null) areaPo.setId(areaMO.getId());
            areaDao.save(areaPo);
        }
    }
}