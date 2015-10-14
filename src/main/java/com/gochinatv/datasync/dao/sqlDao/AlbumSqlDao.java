package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.AlbumPo;

import java.util.List;
import java.util.Set;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface AlbumSqlDao {

    public AlbumPo getAlbumPo(long albumId);

    public List<AlbumPo> getAlbumList(long channelId);

    public List<Long> getAlbumIds();
}
