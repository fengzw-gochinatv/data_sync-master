package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.VideoPo;

import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface VideoSqlDao {

    public VideoPo getVideoPo(Long videoId);

    public List<VideoPo> getVideoList(Long albumId);
    public List<Long> getVideoIds();
    public String getInstallmens(Long vid);
}
