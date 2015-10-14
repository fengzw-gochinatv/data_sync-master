package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.FocusPo;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:24
 */
public interface FocusSqlDao {
    public FocusPo getFocusPo(Long focusId);
    public List<FocusPo> getFocusList();
}
