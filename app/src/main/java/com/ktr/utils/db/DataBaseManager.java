package com.ktr.utils.db;

import java.util.LinkedList;
import java.util.List;

import com.ktr.app.KtrApp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * 
 */
public class DataBaseManager extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ktr.db";
	
	private static final int DATABASE_VERSION = 1;

	private static final SQLiteException DOWNGRAD_EXCEPTION = new SQLiteException("Database file was deleted");

	private final List<DatabaseTable> registeredTables;

	private final static DataBaseManager instance;
	static {
		instance = new DataBaseManager();
	}

	public static DataBaseManager getInstance() {
		return instance;
	}

	public DataBaseManager() {
		super(KtrApp.getInstance(), DATABASE_NAME, null, DATABASE_VERSION);
		registeredTables = new LinkedList<DatabaseTable>();
	}

	public void addTable(DatabaseTable table) {
		registeredTables.add(table);
	}

	public void removeAccount(String account) {
		for (DatabaseTable table : registeredTables)
			if (table instanceof AbstractAccountTable)
				((AbstractAccountTable) table).removeAccount(account);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (DatabaseTable table : registeredTables) {
			table.create(db);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			for (DatabaseTable table : registeredTables) {
				table.migrate(db, oldVersion);
			}
			onCreate(db);
		}
	}

	public static void execSQL(SQLiteDatabase db, String sql) {
		db.execSQL(sql);
	}

	public static void dropTable(SQLiteDatabase db, String table) {
		execSQL(db, "DROP TABLE IF EXISTS " + table + ";");
	}

	public static void renameTable(SQLiteDatabase db, String table, String newTable) {
		execSQL(db, "ALTER TABLE " + table + " RENAME TO " + newTable + ";");
	}

	public void onClear() {
		for (DatabaseTable table : registeredTables)
			table.clear();
	}

	public void onLoad() {
		try {
			getWritableDatabase();
		} catch (SQLiteException e) {
			if (e == DOWNGRAD_EXCEPTION) {
			} else {
				throw e;
			}
		}
	}

}
