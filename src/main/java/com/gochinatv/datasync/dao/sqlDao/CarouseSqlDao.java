package com.gochinatv.datasync.dao.sqlDao;


import com.gochinatv.datasync.bean.CarouselPo;

import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface CarouseSqlDao {

    public CarouselPo getCarouse(long carouseId);

    public List<CarouselPo> getList();
}
