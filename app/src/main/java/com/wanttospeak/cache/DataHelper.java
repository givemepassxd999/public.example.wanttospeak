package com.wanttospeak.cache;

/**
 * Created by givemepass on 2015/5/10.
 */
public class DataHelper {

    private String currentPersonId;

    private static DataHelper mInstance;

    private DataHelper(){

    }

    public static DataHelper getInstance(){
        if(mInstance == null){
            mInstance = new DataHelper();
        }
        return mInstance;
    }

    //取得目前的person id
    public static String getCurrentPersonId() {
        return getInstance().currentPersonId;
    }

    public static void setCurrentPersonId(String currentPersonId) {
        getInstance().currentPersonId = currentPersonId;
    }
}
