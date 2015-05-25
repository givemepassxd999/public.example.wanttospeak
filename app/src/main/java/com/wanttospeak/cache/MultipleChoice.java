package com.wanttospeak.cache;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;

/**
 * Created by givemepass on 2015/5/10.
 */
public abstract class MultipleChoice {
    @DatabaseField(columnName = "type")
    protected int type;

    @DatabaseField(columnName = "item_list_json")
    public ArrayList<String> itemList;

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
        type = Constant.TWO_OPTIONS;
        itemList = new ArrayList<String>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void addItemId(String itemId){
        if(itemId != null){
            itemList.add(itemId);
        }
    }

    public void setCombitnationId(String combitnationId){
        this.combitnationId = combitnationId;
    }
}
