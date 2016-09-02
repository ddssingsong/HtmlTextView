package com.jhs.htmltextview.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.jhs.htmltextview.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * @author jiao on 2016/7/11 10:27
 * @E-mail: jiaopeirong@iruiyou.com
 * 类说明:Imageloader工具类
 */
public class ImageLoaderUtil {
    public static DisplayImageOptions options;
    //圆形头像
    public static DisplayImageOptions roundOptions;
    public static ImageLoaderUtil imageLoaderUtil;

    static {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.img_default_bg)
                .showImageOnFail(R.drawable.img_default_bg)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer()).build();

        roundOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.img_default_avatar)
                .showImageOnFail(R.drawable.img_default_avatar)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(360)).build();
    }

    public static ImageLoaderUtil getInstance() {
        if (imageLoaderUtil == null) {
            synchronized (ImageLoaderUtil.class) {
                if (imageLoaderUtil == null) {
                    imageLoaderUtil = new ImageLoaderUtil();
                    return imageLoaderUtil;
                }
            }
        }
        return imageLoaderUtil;
    }

    /**
     * 私有化构造
     */
    private ImageLoaderUtil() {

    }

    /**
     * 从网络获取图片
     *
     * @param url
     * @param imageView
     */
    public void displayFromWeb(String url, ImageView imageView, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 从SD卡获取图片
     *
     * @param uri
     * @param imageView
     */
    public void displayFromSDCard(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage("file://" + uri, imageView);
    }

    /**
     * 从assets获取图片
     *
     * @param uri
     * @param imageView
     */
    public void dispalyFromAssets(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage("assets://" + uri, imageView);
    }

    /**
     * 从内容提供者获取图片
     *
     * @param uri
     * @param imageView
     */
    public void displayFromContent(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage("content://" + uri, imageView);
    }


}
