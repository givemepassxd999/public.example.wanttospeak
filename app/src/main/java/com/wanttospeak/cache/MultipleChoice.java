package com.wanttospeak.cache;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wanttospeak.util.IdGenerator;

/**
 * Created by givemepass on 2015/5/10.
 */
@DatabaseTable(tableName = "multi_choice")
public abstract class MultipleChoice {

    @DatabaseField(columnName = "type")
    protected int type;

    @DatabaseField(dataType = DataType.SERIALIZABLE,  columnName = "item_list_id")
    public String[] itemList;

    @DatabaseField(columnName = "choice_name")
    protected String choiceName;

    @DatabaseField(id = true, columnName = "item_combitnation_id")
    protected String combitnationId;

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public MultipleChoice(){
        combitnationId = IdGenerator.createId();
        type = Constant.TWO_OPTIONS;
        initArray();
    }

    protected abstract void initArray();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCombitnationId(String combitnationId){
        this.combitnationId = combitnationId;
    }
}
