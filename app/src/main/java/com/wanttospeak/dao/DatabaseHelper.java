package com.wanttospeak.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MultipleChoice;

import java.sql.SQLException;

/**
 * Created by givemepass on 2015/5/19.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "wanttospeak.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<ItemDao, String> itemObjectDao = null;

    private Dao<MultipleChoice, String> twoChoiceObjectDao = null;

    private volatile boolean isOpen = true;

    public static DatabaseHelper mDatabaseHelper;

    public static void init(Context context){
        if(mDatabaseHelper == null){
            mDatabaseHelper = new DatabaseHelper(context);
        }
    }

    public static DatabaseHelper getInstance(){
        return mDatabaseHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ItemDao.class);
            TableUtils.createTable(connectionSource, MultipleChoice.class);
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(), "create table fail.", e);
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ItemDao.class, true);
            TableUtils.dropTable(connectionSource, MultipleChoice.class, true);
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

    public Dao<ItemDao, String> getItemDao() throws SQLException {
        if (mDatabaseHelper.itemObjectDao == null) {
            mDatabaseHelper.itemObjectDao = getDao(ItemDao.class);
        }
        return mDatabaseHelper.itemObjectDao;
    }

    public Dao<MultipleChoice, String> getMultipleChoiceDao() throws SQLException {
        if(mDatabaseHelper.twoChoiceObjectDao == null){
            mDatabaseHelper.twoChoiceObjectDao = getDao(MultipleChoice.class);
        }
        return mDatabaseHelper.twoChoiceObjectDao;
    }
}
