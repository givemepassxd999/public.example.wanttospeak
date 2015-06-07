package com.wanttospeak.cache;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.wanttospeak.dao.DatabaseHelper;
import com.wanttospeak.dao.ItemDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by givemepass on 2015/5/9.
 */
public class MyItemList {

    private static MyItemList mInstance = new MyItemList();

    private HashMap<String, ArrayList<ItemDao>> personalItemList;

    public static MyItemList getInstance(){
        return mInstance;
    }

    private MyItemList(){
        personalItemList = new HashMap<String, ArrayList<ItemDao>>();
    }

    public static ArrayList<ItemDao> getItemListByPersonId(String personId){
        ArrayList<ItemDao> itemListTmp = getInstance().personalItemList.get(personId);
        //if table not exist, get data from db.
        if(itemListTmp == null){
            try {
                itemListTmp = (ArrayList<ItemDao>) DatabaseHelper.getInstance().getItemDao().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return itemListTmp;
    }

    public static ItemDao getItemObjByPersonIdAndIndex(String personId, int itemListIndex){
        ArrayList<ItemDao> list = getInstance().personalItemList.get(personId);
        if(list == null){
            return new ItemDao();
        } else{
            ItemDao item = list.get(itemListIndex);
            if(item == null) {
                return new ItemDao();
            } else{
                return item;
            }
        }

    }

    public static ItemDao getItemObjByPersonIdAndItemId(String personId, String itemId){
        ItemDao item = null;
        ArrayList<ItemDao> itemList = getInstance().personalItemList.get(personId);
        item = getInstance().getItemDaoFromList(itemList, itemId);
        if(item == null){
            try {
                Dao<ItemDao, String> itemDao = DatabaseHelper.mDatabaseHelper.getItemDao();
                QueryBuilder<ItemDao, String> builder = itemDao.queryBuilder();
                builder.where().eq("item_id", itemId);
                itemList = (ArrayList<ItemDao>) itemDao.query(builder.prepare());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        item = getInstance().getItemDaoFromList(itemList, itemId);
        return item;
    }

    private ItemDao getItemDaoFromList(ArrayList<ItemDao> itemList, String itemId){
        if(itemList != null) {
            for (ItemDao i : itemList) {
                if (i.getItemId().equals(itemId)) {
                    return i;
                }
            }
        }
        return null;
    }

    public static int getItemCountByPersonId(String personId) {
        return getInstance().personalItemList.get(personId).size();
    }

    public static void addPersonalItem(String personId, ItemDao item){
        ArrayList<ItemDao> itemList = getItemListByPersonId(personId);
        if(itemList == null){
            itemList = new ArrayList<ItemDao>();
        }
        itemList.add(item);
        getInstance().personalItemList.put(personId, itemList);
    }
}
