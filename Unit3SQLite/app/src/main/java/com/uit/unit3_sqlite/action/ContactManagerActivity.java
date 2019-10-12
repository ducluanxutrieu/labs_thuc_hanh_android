package com.uit.unit3_sqlite.action;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uit.unit3_sqlite.R;
import java.util.ArrayList;

public class ContactManagerActivity extends AppCompatActivity implements ContactItemClickedListener {
    ContactDatabaseAdapter databaseAdapter;
    ContactRecyclerAdapter recyclerAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);

        databaseAdapter = new ContactDatabaseAdapter(this);
        databaseAdapter.open();
        databaseAdapter.deleteAllUsers();

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");

        createFirstContactsData();
        setupRecyclerView();

        // Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
//        for (Contact cn : getAllContacts()) {
//            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//            // Writing Contacts to log
//            Log.e("Name: ", log);
//        }


    }

    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_contacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new ContactRecyclerAdapter(new ArrayList<Contact>(), this);
        getAllContacts();
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    private void createFirstContactsData() {
        databaseAdapter.addContact("Ravi", "9100000000");
        databaseAdapter.addContact("Srinivas", "9199999999");
        databaseAdapter.addContact("Tommy", "9522222222");
        databaseAdapter.addContact("Karthik", "9533333333");
    }

    private void getAllContacts() {
        Cursor cursor = databaseAdapter.getAllContacts();
        ArrayList<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ContactDatabaseAdapter.KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactDatabaseAdapter.KEY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactDatabaseAdapter.KEY_PHONE_NUMBER));
            contacts.add(new Contact(id, name, phoneNumber));
        }
        recyclerAdapter.addData(contacts);
    }

    @Override
    public void onItemClicked(int id, int position) {
        databaseAdapter.deleteUser(id);
    }
}
