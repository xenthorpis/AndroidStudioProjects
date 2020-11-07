package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    // create hashtable (name -> url domain)
    // hashtable structure, key : value
    // method, get(key) = value;
    static Hashtable<String, String> myHashTable = new Hashtable<>();
    // display

    static ArrayList<String> myList = new ArrayList<>();


    static ArrayAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainList = findViewById(R.id.listView);

        myHashTable.put("Google", "http://www.google.com");
        myHashTable.put("百度", "http://www.baidu.com");
        myHashTable.put("淘宝", "http://www.taobao.com");
        myHashTable.put("Amazon", "http://www.amazon.com");



        Set<String> websiteKeys = myHashTable.keySet();

        for (String key : websiteKeys) {
            myList.add(key);
        }

        myListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);

        mainList.setAdapter(myListAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent website = new Intent(getApplicationContext(), Web.class);
                website.putExtra("webIndex", position);
                startActivity(website);
            }
        });

    }

}