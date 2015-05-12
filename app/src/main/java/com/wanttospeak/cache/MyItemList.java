package com.wanttospeak.cache;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by givemepass on 2015/5/9.
 */
public class MyItemList {

    private static MyItemList mInstance;

    public static MyItemList getInstance(){
        if(null == mInstance){
            mInstance = new MyItemList();
        }
        return mInstance;
    }

    private MyItemList(){
        personalItemList = new HashMap<String, ArrayList<ItemObject>>();
    }

    //某人的item list
    private HashMap<String, ArrayList<ItemObject>> personalItemList;

    //取得某人的item list
    public static ArrayList<ItemObject> getItemListByPersonId(String personId){
        return getInstance().personalItemList.get(personId);
    }

    //取得某人的item list內的某位置的item物件
    public static ItemObject getItemObjByPersonIdAndIndex(String personId, int itemListIndex){
        return getInstance().personalItemList.get(personId).get(itemListIndex);
    }

    public static ItemObject getItemObjByPersonIdAndItemId(String personId, String itemId){
        ArrayList<ItemObject> itemList = getInstance().personalItemList.get(personId);
        for(ItemObject item : itemList){
            if(item.getItemId().equals(itemId)){
                return item;
            }
        }
        return null;
    }

    //取得某人item list的數量
    public static int getItemCountByPersonId(String personId){
        return getInstance().personalItemList.get(personId).size();
    }

    public static ArrayList<ItemObject> getItemList(String personId) {
        return getInstance().personalItemList.get(personId);
    }

    //將item加入到某人的item list
    public static void addPersonalItem(String personId, ItemObject item){
        ArrayList<ItemObject> itemList = getItemListByPersonId(personId);
        if(itemList == null){
            itemList = new ArrayList<ItemObject>();
        }
        itemList.add(item);
        getInstance().personalItemList.put(personId, itemList);
    }
}
