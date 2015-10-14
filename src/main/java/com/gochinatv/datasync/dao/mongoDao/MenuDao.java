package com.gochinatv.datasync.dao.mongoDao;

import com.gochinatv.datasync.bean.MenuPo;
import com.google.code.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by shhao.
 * Date: 15-4-7
 * Time:上午11:11
 */
@Component
public class MenuDao extends BaseDao<MenuPo,String> {
    @Autowired
    public MenuDao(@Qualifier("dataStore") Datastore ds) {
        super(MenuPo.class, ds);
    }
}
