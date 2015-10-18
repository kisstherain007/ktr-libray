package com.ktr.utils.db.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ktr.utils.db.AbstractAccountTable;
import com.ktr.utils.db.DataBaseManager;

/**
 * Created by kisstherain on 2015/10/18.
 */
public class UserTable extends AbstractAccountTable {

    public static final String TAG = "UserTable";

    private static UserTable instance;

    static {
        instance = new UserTable();
    }

    public static UserTable getInstance() {
        return instance;
    }

    public static interface UserTableFields extends Fields{

        public static final String user_id = "user_id";

        public static final String user_name = "user_name";
    }

    public static final String user_info_table = "CREATE TABLE " + TAG + "("
            + UserTableFields.user_id  		+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UserTableFields.user_name		+ " TEXT,"
            + ")";

    @Override
    protected String getTableName() {
        return TAG;
    }

    @Override
    protected String[] getProjection() {
        return new String[]{
                UserTableFields.user_id,
                UserTableFields.user_name
        };
    }

    @Override
    public void create(SQLiteDatabase db) {
        db.execSQL(user_info_table);
    }

    public synchronized long writeUser(String name){

        SQLiteDatabase wdb = DataBaseManager.getInstance().getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long rowId  = wdb.insert(getTableName(), null, contentValues);
        wdb.close();
        return rowId ;
    }
}
