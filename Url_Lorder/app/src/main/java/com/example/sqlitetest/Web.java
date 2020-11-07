package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends AppCompatActivity {

    int webIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        webIndex = intent.getIntExtra("webIndex", -1);

        WebView website = findViewById(R.id.website);

        website.getSettings().setJavaScriptEnabled(true);

        website.setWebViewClient(new WebViewClient());

        String domain = MainActivity.myList.get(webIndex);
        String websiteLink = MainActivity.myHashTable.get(domain);

        website.loadUrl(websiteLink);
    }
}