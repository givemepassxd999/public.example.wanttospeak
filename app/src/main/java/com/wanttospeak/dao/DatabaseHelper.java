package com.wanttospeak.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.items.ItemObject;

import java.sql.SQLException;

/**
 * Created by givemepass on 2015/5/19.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "wanttospeak.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<ItemObject, String> itemObjectDao = null;

    private volatile boolean isOpen = true;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ItemObject.class);
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(), "create table fail.", e);
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ItemObject.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(), "create table fail.", e);
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        itemObjectDao = null;
    }

    public Dao<ItemObject, String> getItemDao() throws SQLException {
        if (itemObjectDao == null) {
            itemObjectDao = getDao(ItemObject.class);
        }
        return itemObjectDao;
    }
}
