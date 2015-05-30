package com.ktr.utils.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Interface for registered database tables.
 * 
 * @author alexander.ivanov
 * 
 */
public interface DatabaseTable {

	/**
	 * Called on create database.
	 * 
	 * @param db
	 */
	void create(SQLiteDatabase db);

	/**
	 * Called on database migration.
	 * 
	 * @param db
	 * @param toVersion
	 */
	void migrate(SQLiteDatabase db, int toVersion);

	/**
	 * Called on clear database request.
	 */
	void clear();

}
