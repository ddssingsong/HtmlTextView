package com.jhs.htmltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jhs.htmltextview.htmltextview.HtmlTextView;

public class HtmlTextViewActivity extends AppCompatActivity {
    private HtmlTextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text_view);
        textView= (HtmlTextView) findViewById(R.id.text);
    }
}
