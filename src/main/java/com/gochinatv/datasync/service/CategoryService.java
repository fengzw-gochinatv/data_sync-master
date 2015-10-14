package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.CategoryPo;
import com.gochinatv.datasync.dao.mongoDao.CategoryDao;
import com.gochinatv.datasync.dao.sqlDao.CategorySqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:23
 */
@Service
public class CategoryService extends HanderDataService {

    @Resource
    CategoryDao categoryDao;
    @Resource
    CategorySqlDao categorySqlDao;

    public void syncData(Long categoryId) {
        logger.info(" CategoryService  syncData categoryId={}", categoryId);
        try {
            if(categoryId==-1){
                resetCategory();
                return;
            }
            CategoryPo categorySQL = categorySqlDao.getCategoryPo(categoryId);
            CategoryPo categoryMO = categoryDao.findOne("categoryId", categoryId);
            if (categorySQL == null) {
                if (categoryMO != null) categoryDao.delete(categoryMO);
            } else {
                if (categoryMO != null) categorySQL.setId(categoryMO.getId());
                categoryDao.save(categorySQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetCategory() {
        List<CategoryPo> list = categorySqlDao.getCategoryList();
        for (CategoryPo category : list) {
            CategoryPo categoryMO = categoryDao.findOne("categoryId", category.getCategoryId());
            if (categoryMO != null) category.setId(categoryMO.getId());
            categoryDao.save(category);
        }

    }
}
