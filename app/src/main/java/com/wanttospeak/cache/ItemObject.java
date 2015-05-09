package com.wanttospeak.cache;

/**
 * Created by givemepass on 2015/5/7.
 */
public class ItemObject {

    private String itemId;

    private String picturePath;

    private String recordPath;

    public String getPicturePath() {

        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRecordPath() {
        return recordPath;
    }

    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath;
    }
}
