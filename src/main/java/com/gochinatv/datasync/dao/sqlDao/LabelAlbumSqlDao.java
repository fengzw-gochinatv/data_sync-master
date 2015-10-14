package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.LabelAlbumPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface LabelAlbumSqlDao {

    public List<LabelAlbumPo> getLabelAlumPoList(Long labelAlbumId);
    public List<LabelAlbumPo> getListByAlbumId(Long albumId);
    public List<LabelAlbumPo> getAllList();
    public List<Long> getIds(Map map);
    public LabelAlbumPo getOne(Long lid);
}
