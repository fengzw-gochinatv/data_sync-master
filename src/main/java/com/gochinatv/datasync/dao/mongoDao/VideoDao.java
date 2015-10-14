package com.gochinatv.datasync.dao.mongoDao;


import com.gochinatv.datasync.bean.VideoPo;
import com.google.code.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:19
 */
@Component
public class VideoDao extends BaseDao<VideoPo,String>{
    @Autowired
    public VideoDao(@Qualifier("dataStore") Datastore ds) {
        super(VideoPo.class, ds);
    }
}
