package com.gochinatv.datasync.dao.sqlDao;


import com.gochinatv.datasync.bean.AreaPo;
import java.util.List;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface AreaSqlDao {

    public AreaPo getAreaPo(Long areaId);
    public List<AreaPo> getAreaList();
}
