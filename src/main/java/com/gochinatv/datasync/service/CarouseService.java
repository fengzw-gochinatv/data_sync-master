package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.CarouselPo;
import com.gochinatv.datasync.dao.mongoDao.CarouseDao;
import com.gochinatv.datasync.dao.sqlDao.CarouseSqlDao;
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
public class CarouseService extends HanderDataService {

    @Resource
    CarouseDao carouseDao;
    @Resource
    CarouseSqlDao carouseSqlDao;

    public void syncData(Long carouseId) {
        logger.info("syncData carouseId={}", carouseId);
        try {
            if (carouseId == -1) {
                resetCarouse();
                return;
            }
            CarouselPo carouselSQL = carouseSqlDao.getCarouse(carouseId);
            CarouselPo carouseMO = carouseDao.findOne("carouseId", carouseId);
            if (carouselSQL == null) {
                if (carouseMO != null) carouseDao.delete(carouseMO);
            } else {
                if (carouseMO != null) carouselSQL.setId(carouseMO.getId());
                carouseDao.save(carouselSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetCarouse() {
        List<CarouselPo> list = carouseSqlDao.getList();
        for (CarouselPo carouselPo : list) {
            CarouselPo carouselMO = carouseDao.findOne("carouseId", carouselPo.getCarouseId());
            if (carouselMO != null) carouselPo.setId(carouselMO.getId());
            carouseDao.save(carouselPo);
        }
    }

}
