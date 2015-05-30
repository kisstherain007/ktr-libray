package com.ktr.utils.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Table with account related information.
 * 
 * @author alexander.ivanov
 * 
 */
public abstract class AbstractAccountTable extends AbstractTable {

	public static interface Fields extends BaseColumns {

		public static final String ACCOUNT = "account";

	}

	/**
	 * Remove records with specified account.
	 * 
	 * @param account
	 */
	public void removeAccount(String account) {
		SQLiteDatabase db = DataBaseManager.getInstance().getWritableDatabase();
		db.delete(getTableName(), Fields.ACCOUNT + " = ?", new String[] { account });
	}

	public static String getAccount(Cursor cursor) {
		return cursor.getString(cursor.getColumnIndex(Fields.ACCOUNT));
	}

}
