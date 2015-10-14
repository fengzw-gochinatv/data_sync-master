package com.gochinatv.datasync.dao.mongoDao;

import com.gochinatv.datasync.bean.LabelAlbumPo;
import com.google.code.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:25
 */
@Component
public class LabelAlbumDao extends BaseDao<LabelAlbumPo,String> {
    @Autowired
    public LabelAlbumDao(@Qualifier("dataStore") Datastore ds) {
        super(LabelAlbumPo.class, ds);
    }
}
