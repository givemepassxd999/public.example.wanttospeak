package com.wanttospeak.cache;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by givemepass on 2015/5/10.
 */
public class MyCombination {
    //二選一的陣列
    private HashMap<String, ArrayList<MulitipleChoice>> twoOptionCombinationList;

    //三選一的陣列
    private HashMap<String, ArrayList<MulitipleChoice>> threeOptionCombinationList;

    //四選一的陣列
    private HashMap<String, ArrayList<MulitipleChoice>> fourOptionCombinationList;

    private static MyCombination mInstance;

    private MyCombination(){}

    public static MyCombination getInstance(){
        if(mInstance == null){
            mInstance = new MyCombination();
        }
        return mInstance;
    }

    //取得某人的組合陣列
    public static ArrayList<MulitipleChoice> getItemsCombinationList(String personId, int type) {
        if(type == Constant.TWO_OPTIONS) {
            return getInstance().twoOptionCombinationList.get(personId);
        }
        else if(type == Constant.THREE_OPTIONS) {
            return getInstance().twoOptionCombinationList.get(personId);
        }
        else if(type == Constant.FOUR_OPTIONS){
                return getInstance().twoOptionCombinationList.get(personId);
        } else{
            return null;
        }

    }

    //取得某人的某個組合
    public static MulitipleChoice getItemsCombination(String personId, int index, int type) {
        ArrayList<MulitipleChoice> choiceList = getItemsCombinationList(personId, type);
        if(choiceList != null) {
            return choiceList.get(index);
        } else{
            return null;
        }
    }

    //設定某人的組合
    public static void setItemCombination(String personId, MulitipleChoice choice, int type){
        ArrayList<MulitipleChoice> mulitipleChoiceList = getInstance().getItemsCombinationList(personId, type);
        if(mulitipleChoiceList == null){
            mulitipleChoiceList = new ArrayList<MulitipleChoice>();
        }
        mulitipleChoiceList.add(choice);

        if(type == Constant.TWO_OPTIONS) {
            getInstance().twoOptionCombinationList.put(personId, mulitipleChoiceList);
        }
        else if(type == Constant.THREE_OPTIONS) {
            getInstance().threeOptionCombinationList.put(personId, mulitipleChoiceList);
        }
        else if(type == Constant.FOUR_OPTIONS){
            getInstance().fourOptionCombinationList.put(personId, mulitipleChoiceList);
        }

    }
}
