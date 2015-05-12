package com.wanttospeak.cache;

import java.util.ArrayList;

/**
 * Created by givemepass on 2015/5/10.
 */
public abstract class MulitipleChoice{
    public int type;

    public ArrayList<String> itemList;

    public MulitipleChoice(){
        type = Constant.TWO_OPTIONS;
        itemList = new ArrayList<String>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected abstract void addItemId(String itemId);
}
