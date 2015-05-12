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

    //�Y�H��item list
    private HashMap<String, ArrayList<ItemObject>> personalItemList;

    //���o�Y�H��item list
    public static ArrayList<ItemObject> getItemListByPersonId(String personId){
        return getInstance().personalItemList.get(personId);
    }

    //���o�Y�H��item list�����Y��m��item����
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

    //���o�Y�Hitem list���ƶq
    public static int getItemCountByPersonId(String personId){
        return getInstance().personalItemList.get(personId).size();
    }

    public static ArrayList<ItemObject> getItemList(String personId) {
        return getInstance().personalItemList.get(personId);
    }

    //�Nitem�[�J��Y�H��item list
    public static void addPersonalItem(String personId, ItemObject item){
        ArrayList<ItemObject> itemList = getItemListByPersonId(personId);
        if(itemList == null){
            itemList = new ArrayList<ItemObject>();
        }
        itemList.add(item);
        getInstance().personalItemList.put(personId, itemList);
    }
}
