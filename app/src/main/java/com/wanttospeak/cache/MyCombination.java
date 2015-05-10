package com.wanttospeak.cache;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by givemepass on 2015/5/10.
 */
public class MyCombination {

    private ArrayList<MulitipleChoice> itemsCombination;

    private HashMap<String, ArrayList<MulitipleChoice>> personalItemsCombinationList;

    public ArrayList<MulitipleChoice> getItemsCombination() {
        return itemsCombination;
    }

    public void setItemsCombination(ArrayList<MulitipleChoice> itemsCombination) {
        this.itemsCombination = itemsCombination;
    }

    public HashMap<String, ArrayList<MulitipleChoice>> getPersonalItemsCombinationList() {
        return personalItemsCombinationList;
    }

    public void setPersonalItemsCombinationList(HashMap<String, ArrayList<MulitipleChoice>> personalItemsCombinationList) {
        this.personalItemsCombinationList = personalItemsCombinationList;
    }
}
