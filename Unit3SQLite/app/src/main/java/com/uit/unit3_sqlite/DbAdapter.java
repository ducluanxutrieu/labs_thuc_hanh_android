package com.uit.unit3_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
    static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_Demo";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    DbAdapter(Context ctx) {
        this.context = ctx;
    }

    void open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    void createUser(String name) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        sqLiteDatabase.insert(DATABASE_TABLE, null, inititalValues);
    }

    public boolean deleteUser(long rowId) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }

    void deleteAllUsers() {
        sqLiteDatabase.delete(DATABASE_TABLE, null, null);
    }

    Cursor getAllUsers() {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NAME}, null, null, null, null, null);
    }
}
