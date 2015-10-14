package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.Live;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface LiveSqlDao {

    public Live getLive(long liveId);
    public List<Live> getLiveList();
}
