package com.uit.unit3_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.uit.unit3_sqlite.action.ContactManagerActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbAdapter dbAdapter;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getToContactManager();

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        createDatabase();

        users = getData();
        showData();
    }

    private void getToContactManager() {
        Intent intent = new Intent(this, ContactManagerActivity.class);
        startActivity(intent);
        finish();
    }

    private void createDatabase() {
        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Nguyễn Văn An " + i);
        }
    }

    private List<String> getData() {
        List<String> users = new ArrayList<>();

        Cursor cursor = dbAdapter.getAllUsers();
        while (cursor.moveToNext()) {
            users.add(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NAME)));
            //Toast.makeText(getApplicationContext(), cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ID)), Toast.LENGTH_SHORT).show();
            Log.i("Ahihi", cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ID)));
        }
        return users;
    }

    private void showData() {
        ListView lvUser = findViewById(R.id.lv_user);
        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.item_user, users);
        lvUser.setAdapter(userAdapter);
    }


}
