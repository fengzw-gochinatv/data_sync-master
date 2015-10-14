package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.ChannelPo;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface ChannelSqlDao {

    public ChannelPo getChannelPo(Long channelId);

    public List<ChannelPo> getChannelList();
}
