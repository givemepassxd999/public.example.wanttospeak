package com.wanttospeak.cache;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by givemepass on 2015/5/10.
 */
public class MyCombination {

    private HashMap<String, ArrayList<MulitipleChoice>> twoOptionCombinationList;


    private HashMap<String, ArrayList<MulitipleChoice>> threeOptionCombinationList;


    private HashMap<String, ArrayList<MulitipleChoice>> fourOptionCombinationList;

    private static MyCombination mInstance;

    private MyCombination(){
        twoOptionCombinationList = new HashMap<String, ArrayList<MulitipleChoice>>();
        threeOptionCombinationList = new HashMap<String, ArrayList<MulitipleChoice>>();
        fourOptionCombinationList = new HashMap<String, ArrayList<MulitipleChoice>>();
    }

    public static MyCombination getInstance(){
        if(mInstance == null){
            mInstance = new MyCombination();
        }
        return mInstance;
    }


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


    public static MulitipleChoice getItemsCombination(String personId, int index, int type) {
        ArrayList<MulitipleChoice> choiceList = getItemsCombinationList(personId, type);
        if(choiceList != null) {
            return choiceList.get(index);
        } else{
            return null;
        }
    }

    //�]�w�Y�H���զX
    public static void addItemCombination(String personId, MulitipleChoice choice){
        ArrayList<MulitipleChoice> mulitipleChoiceList = getInstance().getItemsCombinationList(personId, choice.getType());
        if(mulitipleChoiceList == null){
            mulitipleChoiceList = new ArrayList<MulitipleChoice>();
        }
        mulitipleChoiceList.add(choice);

        switch(choice.getType()) {
            case Constant.TWO_OPTIONS:
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
