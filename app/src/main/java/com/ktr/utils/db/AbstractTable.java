package com.ktr.utils.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Abstract database table.
 * 
 * @author alexander.ivanov
 * 
 */
public abstract class AbstractTable implements DatabaseTable {

	protected abstract String getTableName();

	protected abstract String[] getProjection();

	protected String getListOrder() {
		return null;
	}

	@Override
	public void migrate(SQLiteDatabase db, int toVersion) {
		DataBaseManager.dropTable(db, getTableName());
	}

	/**
	 * Query table.
	 * 
	 * @return Result set with defined projection and in defined order.
	 */
	public Cursor list() {
		SQLiteDatabase db = DataBaseManager.getInstance().getWritableDatabase();
		return db.query(getTableName(), getProjection(), null, null, null, null, getListOrder());
	}

	public Cursor list(String select, String[] selectionArgs) {
		SQLiteDatabase db = DataBaseManager.getInstance().getWritableDatabase();
		return db.query(getTableName(), getProjection(), select, selectionArgs, null, null, getListOrder());
	}

	@Override
	public void clear() {
		SQLiteDatabase db = DataBaseManager.getInstance().getWritableDatabase();
		db.delete(getTableName(), null, null);
	}

	protected boolean hasData(Cursor c) {
		if (c != null && c.getCount() > 0) {
			return true;
		}
		return false;
	}

	protected void closeCurosr(Cursor c) {
		if (c != null && !c.isClosed()) {
			c.close();
			c = null;
		}
	}

}
