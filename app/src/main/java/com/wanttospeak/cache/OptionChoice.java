package com.wanttospeak.cache;

/**
 * Created by givemepass on 2015/5/10.
 */
public class OptionChoice extends MulitipleChoice{
    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void addItemId(String itemId) {
        if(itemList.size() < type){
            itemList.add(itemId);
        }
    }
}