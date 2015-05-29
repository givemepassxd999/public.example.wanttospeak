package com.wanttospeak.dao;

/**
 * Created by givemepass on 2015/5/25.
 */


public class TwoChoiceDao extends MultipleChoiceDao {

    public TwoChoiceDao() {
        super();
    }

    @Override
    protected void initArray() {
        itemList = new String[2];
    }

    public void setRightItemId(String rightItemId){
        itemList[1] = rightItemId;
    }

    public void setLeftItemId(String leftItemId){
        itemList[0] = leftItemId;
    }

    public String getRightItemId(){
        return itemList[1];
    }

    public String getLeftItemId(){
        return itemList[0];
    }
}
