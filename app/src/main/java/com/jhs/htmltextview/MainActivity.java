package com.jhs.htmltextview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.jhs.htmltextview.utils.JavaHttpUtil;
import com.jhs.htmltextview.utils.MTagHandler;
import com.jhs.htmltextview.utils.MyImageGetter;
import com.jhs.htmltextview.utils.StreamUtil;

import java.io.InputStream;


/**
 * TextView支持 HTML 标签列表：
 * {<br>,
 * < p>,
 * < div align=>,
 * < strong>,
 * <b>,
 * <em>,
 * <cite>,用于参考文献《富士山春居图》
 * <dfn>, 特殊术语和短语
 * <i>, 斜体文本
 * <big>,
 * <small>,
 * <font size=>,
 * <font color=>,
 * <blockquote>,标记长的引用
 * <tt>, 打字机文本
 * <a href=>,
 * <u>,下划线
 * <sup>,<sub>,  上标和下标
 * <h1>,<h2>,<h3>,<h4>,<h5>,<h6>,
 * <img src=>,    虽然这个是支持的，但现在很多网站的图片链接都不是对外绝对路径，需要重写ImageGetter方法加上ip地址
 * <strike>} 删除线
 */
public class MainActivity extends AppCompatActivity {
    TextView text;
    String url = "http://218.245.0.52:8081/news/Index?infoId=72";

    private Spanned spanned;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                text.setText(spanned);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
    }

    private void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = JavaHttpUtil.httpGet(url, null);
                String content = StreamUtil.stream2String(is);
                spanned = Html.fromHtml(content, new MyImageGetter(text, MainActivity.this), new MTagHandler());
                handler.sendEmptyMessage(0);

            }
        }).start();


    }
}
