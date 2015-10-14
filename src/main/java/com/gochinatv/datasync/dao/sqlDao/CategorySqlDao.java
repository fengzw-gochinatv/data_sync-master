package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.CategoryPo;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface CategorySqlDao {

    public CategoryPo getCategoryPo(Long categoryId);
    public List<CategoryPo> getCategoryList();
}
