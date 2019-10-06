package com.uit.unit3_sqlite.action;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    private SQLiteDatabase mSQLite;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        mSQLite = db;
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_ID, contact.getId());
        inititalValues.put(KEY_NAME, contact.getName());
        inititalValues.put(KEY_PH_NO, contact.getPhoneNumber());
        mSQLite.insert(TABLE_CONTACTS, null, inititalValues);
    }

//    // Getting single contact
//    public Contact getContact(int id) {
//    }

    // Getting All Contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Cursor cursor = mSQLite.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME,KEY_PH_NO}, null, null, null, null, null);
        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex(KEY_ID))!=null){
                Contact contact=new Contact(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),cursor.getString(cursor.getColumnIndex(KEY_NAME)),cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
                contacts.add(contact);
            }
        }
        return contacts;
    }
// return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NAME}, null, null, null, null, null);

    // Updating single contact
    public int updateContact(Contact contact) {
        return 0;
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {}


}
