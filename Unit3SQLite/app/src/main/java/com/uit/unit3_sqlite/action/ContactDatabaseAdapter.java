package com.uit.unit3_sqlite.action;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


class ContactDatabaseAdapter {
    static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_PHONE_NUMBER = "phone_number";

    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_Contact";
    private static final String DATABASE_TABLE = "contacts";
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    ContactDatabaseAdapter(Context context) {
        this.context = context;
    }

    void open() {
        ContactDatabaseHelper dbHelper = new ContactDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    void deleteAllUsers() {
        sqLiteDatabase.delete(DATABASE_TABLE, null, null);
    }

    void addContact(String name, String phoneNumber) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        inititalValues.put(KEY_PHONE_NUMBER, phoneNumber);
        sqLiteDatabase.insert(DATABASE_TABLE, null, inititalValues);
    }

    public Cursor getAllContacts() {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER}, null, null, null, null, null);
    }

    public void deleteUser(int rowId) {
        sqLiteDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null);
    }
}
