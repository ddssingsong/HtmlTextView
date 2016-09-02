package com.jhs.htmltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        String tableHtml = getIntent().getStringExtra("EXTRA_TABLE_HTML");


        webview = (WebView) findViewById(R.id.webview);


        webview.loadData(tableHtml, null, "UTF-8");


    }
}
