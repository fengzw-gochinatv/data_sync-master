package com.gochinatv.datasync.dao.mongoDao;
import com.gochinatv.datasync.bean.FocusPo;
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
public class FocusDao extends BaseDao<FocusPo, String>{
    @Autowired
    public FocusDao(@Qualifier("dataStore") Datastore ds) {
        super(FocusPo.class, ds);
    }
}
