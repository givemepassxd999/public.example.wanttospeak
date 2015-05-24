package com.wanttospeak.cache;

import com.wanttospeak.dao.DatabaseHelper;
import com.wanttospeak.items.ItemObject;

import java.sql.SQLException;
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

    private HashMap<String, ArrayList<ItemObject>> personalItemList;

    public static ArrayList<ItemObject> getItemListByPersonId(String personId){
        ArrayList<ItemObject> itemListTmp = getInstance().personalItemList.get(personId);
        //if table not exist, get data from db.
        if(itemListTmp == null){
            try {
                itemListTmp = (ArrayList<ItemObject>) DatabaseHelper.getInstance().getItemDao().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return itemListTmp;
    }

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

    public static int getItemCountByPersonId(String personId){
        return getInstance().personalItemList.get(personId).size();
    }

    public static ArrayList<ItemObject> getItemList(String personId) {
        return getInstance().personalItemList.get(personId);
    }

    public static void addPersonalItem(String personId, ItemObject item){
        ArrayList<ItemObject> itemList = getItemListByPersonId(personId);
        if(itemList == null){
            itemList = new ArrayList<ItemObject>();
        }
        itemList.add(item);
        getInstance().personalItemList.put(personId, itemList);
    }
}
