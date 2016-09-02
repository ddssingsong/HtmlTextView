package com.jhs.htmltextview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jhs.htmltextview.htmltextview.ClickableTableSpan;
import com.jhs.htmltextview.htmltextview.DrawTableLinkSpan;
import com.jhs.htmltextview.htmltextview.HtmlHttpImageGetter;
import com.jhs.htmltextview.htmltextview.HtmlTextView;
import com.jhs.htmltextview.utils.JavaHttpUtil;
import com.jhs.htmltextview.utils.StreamUtil;

import java.io.InputStream;

public class HtmlTextViewActivity extends AppCompatActivity {
    private HtmlTextView textView;

    String url = "https://m.baidu.com/";

    String content;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                textView.setRemoveFromHtmlSpace(true);

                textView.setClickableTableSpan(new ClickableTableSpanImpl());
                DrawTableLinkSpan drawTableLinkSpan = new DrawTableLinkSpan();
                drawTableLinkSpan.setTableLinkText("[tap for table]");
                textView.setDrawTableLinkSpan(drawTableLinkSpan);



                textView.setHtml(content, new HtmlHttpImageGetter(textView));



            }
        }
    };


    class ClickableTableSpanImpl extends ClickableTableSpan {
        @Override
        public ClickableTableSpan newInstance() {
            return new ClickableTableSpanImpl();
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(HtmlTextViewActivity.this, WebActivity.class);

            String str = getTableHtml();
            intent.putExtra("EXTRA_TABLE_HTML", str);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text_view);
        textView = (HtmlTextView) findViewById(R.id.text);
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = JavaHttpUtil.httpGet(url, null);
                content = StreamUtil.stream2String(is);
                handler.sendEmptyMessage(0);

            }
        }).start();

    }
}
