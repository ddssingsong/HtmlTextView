package com.jhs.htmltextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

/**
 * 1、BackgroundColorSpan 背景色
 * 2、ClickableSpan 文本可点击，有点击事件
 * 3、ForegroundColorSpan 文本颜色（前景色）
 * 4、MaskFilterSpan 修饰效果，如模糊(BlurMaskFilter)、浮雕(EmbossMaskFilter)
 * 5、MetricAffectingSpan 父类，一般不用
 * 6、RasterizerSpan 光栅效果
 * 7、StrikethroughSpan 删除线（中划线）
 * 8、SuggestionSpan 相当于占位符
 * 9、UnderlineSpan 下划线
 * 10、AbsoluteSizeSpan 绝对大小（文本字体）
 * 11、DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
 * 12、ImageSpan 图片
 * 13、RelativeSizeSpan 相对大小（文本字体）
 * 14、ReplacementSpan 父类，一般不用
 * 15、ScaleXSpan 基于x轴缩放
 * 16、StyleSpan 字体样式：粗体、斜体等
 * 17、SubscriptSpan 下标（数学公式会用到）
 * 18、SuperscriptSpan 上标（数学公式会用到）
 * 19、TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
 * 20、TypefaceSpan 文本字体
 * 21、URLSpan 文本超链接
 */
public class SpanActivity extends AppCompatActivity {
    private TextView text3;

    //private SpannableStringBuilder stringBuilder;
    //private SpannableString spannableString;
    // private Spannable spannable;

//    private SpannedString spannedString;
//    private Spanned spanned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        text3 = (TextView) findViewById(R.id.text3);

        SpannableString str = new SpannableString("添加背景色");
        str.setSpan(new StrikethroughSpan(), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text3.setText(str);
    }

}
