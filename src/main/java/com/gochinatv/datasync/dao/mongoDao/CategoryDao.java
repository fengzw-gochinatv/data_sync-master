package com.gochinatv.datasync.dao.mongoDao;

import com.gochinatv.datasync.bean.AlbumPo;
import com.gochinatv.datasync.bean.CategoryPo;
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
public class CategoryDao extends BaseDao<CategoryPo, String>{
    @Autowired
    public CategoryDao(@Qualifier("dataStore") Datastore ds) {
        super(CategoryPo.class, ds);
    }
}
