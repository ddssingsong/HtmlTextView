package com.jhs.htmltextview.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import com.jhs.htmltextview.R;

import java.io.File;
import java.net.URL;

/**
 * Created by dds on 2016/8/30.
 *
 * @TODO
 */
public class MyImageGetter implements Html.ImageGetter {

    private Context context;
    private TextView textView;

    public MyImageGetter(TextView textView, Context context) {
        this.context = context;
        this.textView = textView;
    }


    @Override
    public Drawable getDrawable(String source) {
        Log.w("MyImagesource", "1111-source" + source);
        String str3;
        String str4;
        if (source.contains("http")) {
            String str1 = MD5Util.MD5(source);
            String str2 = Environment.getExternalStorageDirectory().toString();
            String[] arrayOfString = source.split("\\.");
            str3 = arrayOfString[(-1 + arrayOfString.length)];
            Log.w("MyImageext", "1111-ext" + str3);
            str4 = str2 + "/" + this.context.getPackageName() + "/" + str1 + "." + str3;
            if (!new File(str4).exists()) {
                //从网络获取并存到内存
                return null;
            }
        } else {
            // /Scripts/ckfinder/userfiles/images/823ouoi.jpg
            source = "http://218.245.0.52:8081" + source;
            URLDrawable urlDrawable = new URLDrawable(context);
            ImageGetterAsyncTask getterTask = new ImageGetterAsyncTask(urlDrawable);
            getterTask.execute(source);
            return urlDrawable;
        }
        return null;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable drawable) {
            this.urlDrawable = drawable;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                urlDrawable.drawable = result;
                MyImageGetter.this.textView.requestLayout();
            }
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        public Drawable fetchDrawable(String url) {
            Drawable drawable = null;
            URL Url;
            try {
                Url = new URL(url);
                drawable = Drawable.createFromStream(Url.openStream(), "src");
            } catch (Exception e) {
                return null;
            }
            //按比例缩放图片
            Rect bounds = getDefaultImageBounds(context);
            int newwidth = bounds.width();
            int newheight = bounds.height();
            double factor = 1;
            double fx = (double) drawable.getIntrinsicWidth() / (double) newwidth;
            double fy = (double) drawable.getIntrinsicHeight() / (double) newheight;
            factor = fx > fy ? fx : fy;
            if (factor < 1) factor =0.5 ;
            newwidth = (int) (drawable.getIntrinsicWidth() / factor);
            newheight = (int) (drawable.getIntrinsicHeight() / factor);
            drawable.setBounds(0, 0, newwidth, newheight);
            return drawable;
        }
    }

    //预定图片宽高比例为 4:3
    @SuppressWarnings("deprecation")
    public Rect getDefaultImageBounds(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = (int) (width * 3 / 4);
        Rect bounds = new Rect(0, 0, width, height);
        return bounds;
    }


    public class URLDrawable extends BitmapDrawable {
        protected Drawable drawable;

        @SuppressWarnings("deprecation")
        public URLDrawable(Context context) {
            this.setBounds(getDefaultImageBounds(context));
            drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
            drawable.setBounds(getDefaultImageBounds(context));
        }

        @Override
        public void draw(Canvas canvas) {
            try {
                this.drawable.draw(canvas);
                return;
            } catch (Throwable localThrowable) {
            }

        }

        public Rect getDefaultImageBounds(Context context) {
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int height = (int) (width * 3 / 4);
            Rect bounds = new Rect(0, 0, width, height);
            return bounds;
        }

    }
}