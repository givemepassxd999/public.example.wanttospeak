package com.wanttospeak.dao;

import com.j256.ormlite.table.DatabaseTable;
import com.wanttospeak.cache.Constant;
import com.wanttospeak.cache.MultipleChoice;

/**
 * Created by givemepass on 2015/5/25.
 */

@DatabaseTable(tableName = "two_choice")
public class TwoChoiceDao extends MultipleChoice{

    public TwoChoiceDao() {
        setType(Constant.TWO_OPTIONS);
    }
}
