package com.wanttospeak.cache;

import com.wanttospeak.dao.DatabaseHelper;
import com.wanttospeak.dao.MultipleChoiceDao;
import com.wanttospeak.util.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by givemepass on 2015/5/10.
 */
public class MyCombination {

    private HashMap<String, ArrayList<MultipleChoiceDao>> twoOptionCombinationList;


    private HashMap<String, ArrayList<MultipleChoiceDao>> threeOptionCombinationList;


    private HashMap<String, ArrayList<MultipleChoiceDao>> fourOptionCombinationList;

    private static MyCombination mInstance;

    private MyCombination(){
        twoOptionCombinationList = new HashMap<String, ArrayList<MultipleChoiceDao>>();
        threeOptionCombinationList = new HashMap<String, ArrayList<MultipleChoiceDao>>();
        fourOptionCombinationList = new HashMap<String, ArrayList<MultipleChoiceDao>>();
    }

    public static MyCombination getInstance(){
        if(mInstance == null){
            mInstance = new MyCombination();
        }
        return mInstance;
    }


    public static ArrayList<MultipleChoiceDao> getItemsCombinationList(String personId, int type) {
        ArrayList<MultipleChoiceDao> multipleChoicesList;
        switch(type){
            case Constant.TWO_OPTIONS:
                multipleChoicesList = getInstance().twoOptionCombinationList.get(personId);
                if(multipleChoicesList == null){
                    try {
                        multipleChoicesList = (ArrayList<MultipleChoiceDao>) DatabaseHelper.getInstance().getMultipleChoiceDao().queryForAll();
                        Logger.e("query success");
                    } catch (SQLException e) {
                        multipleChoicesList = null;
                        Logger.e("query fail");
                    }
                }
                break;
            case Constant.THREE_OPTIONS:
                multipleChoicesList = getInstance().twoOptionCombinationList.get(personId);
                break;
            case Constant.FOUR_OPTIONS:
                multipleChoicesList = getInstance().twoOptionCombinationList.get(personId);
                break;
            default:
                multipleChoicesList = null;
                break;
        }
        return multipleChoicesList;
    }


    public static MultipleChoiceDao getItemsCombination(String personId, int index, int type) {
        ArrayList<MultipleChoiceDao> choiceList = getItemsCombinationList(personId, type);
        if(choiceList != null) {
            return choiceList.get(index);
        } else{
            return null;
        }
    }

    public static void putItemCombination(String personId, MultipleChoiceDao choice){
        ArrayList<MultipleChoiceDao> mulitipleChoiceList = getInstance().getItemsCombinationList(personId, choice.getType());
        if(mulitipleChoiceList == null){
            mulitipleChoiceList = new ArrayList<MultipleChoiceDao>();
        }
        mulitipleChoiceList.add(choice);

        switch(choice.getType()) {
            case Constant.TWO_OPTIONS:
                Logger.e("two option");
                getInstance().twoOptionCombinationList.put(personId, mulitipleChoiceList);
                break;
            case Constant.THREE_OPTIONS:
                getInstance().threeOptionCombinationList.put(personId, mulitipleChoiceList);
                break;
            case Constant.FOUR_OPTIONS:
                getInstance().fourOptionCombinationList.put(personId, mulitipleChoiceList);
                break;
        }

    }
}
