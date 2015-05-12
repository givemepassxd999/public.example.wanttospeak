package com.wanttospeak.items;

import com.wanttospeak.util.IdGenerator;

public class Item {
    private String id;
    private String photoPath;
    private String recordPath;

    public Item() {
        id = IdGenerator.createId();
    }

    public void setPhotoPath(String path) {
        this.photoPath = path;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setRecordPath(String path) {
        this.recordPath = path;
    }

    public String getRecordPath() {
        return recordPath;
    }
}
