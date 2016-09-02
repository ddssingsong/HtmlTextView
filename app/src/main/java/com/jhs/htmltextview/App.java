package com.jhs.htmltextview;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;

/**
 * Created by dds on 2016/9/2.
 *
 * @TODO
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
    }


    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPoolSize(4);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(10 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        // config.writeDebugLogs();
        L.disableLogging();
        ImageLoader.getInstance().init(config.build());
    }
}
