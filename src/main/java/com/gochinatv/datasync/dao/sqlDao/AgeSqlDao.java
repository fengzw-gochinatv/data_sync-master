package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.AgePo;
import com.gochinatv.datasync.bean.AlbumPo;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface AgeSqlDao {

    public AgePo getAgePo(long ageId);
}
