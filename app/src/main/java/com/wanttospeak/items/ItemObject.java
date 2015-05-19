package com.wanttospeak.items;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wanttospeak.util.IdGenerator;

/**
 * Created by givemepass on 2015/5/7.
 */
@DatabaseTable(tableName = "item_obj")
public class ItemObject {

    @DatabaseField(id = true, columnName = "item_id")
    private String itemId;

    @DatabaseField(columnName = "photo_path")
    private String photoPath;

    @DatabaseField(columnName = "record_path")
    private String recordPath;

    public ItemObject() {
        itemId = IdGenerator.createId();
    }

    public String getPhotoPath() {

        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
